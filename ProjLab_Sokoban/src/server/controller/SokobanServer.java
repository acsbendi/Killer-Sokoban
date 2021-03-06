package server.controller;

import common.util.Direction;
import common.util.Hash;

import java.util.ArrayList;
import java.util.HashMap;

public class SokobanServer implements ControllerLogic {
    private NetworkHandler networkHandler;
    private DataBaseManager dataBaseManager;

    private HashMap<Integer, Room> rooms;

    public static SokobanServer Create() {
        SokobanServer sokobanServer = new SokobanServer();
        sokobanServer.networkHandler = new NetworkHandler(sokobanServer);
        sokobanServer.dataBaseManager = new DataBaseManager();
        return sokobanServer;
    }

    private SokobanServer() {
        rooms = new HashMap<>();
    }

    public void Run() {
        networkHandler.Listen();
    }

    @Override
    public void AddClient(Client client) {
        // todo
    }

    @Override
    public void Disconnected(Client client) {
        if (client.GetState() == ClientState.Connected) {

        }
        else if (client.GetState() == ClientState.LoggedIn) {
            System.out.println(client.GetName() + " disconnected");
        }
        else if (client.GetState() == ClientState.Waiting) {
            client.GetRoom().RemoveClient(client);
            System.out.println(client.GetName() + " left queue and disconnected");
        }
        else if (client.GetState() == ClientState.Loading || client.GetState() == ClientState.Ready || client.GetState() == ClientState.Playing) {
            client.GetRoom().RemoveClient(client);
            System.out.println(client.GetName() + " left game and disconnected");
        }
    }

    @Override
    public void Register(Client client, String username, String password) {
        if (client.GetState() == ClientState.Connected) {
            System.out.println("Someone tried to register.");
            if (password.length() >= 8) {
                if (dataBaseManager.Register(username, Hash.GetHashFor(password)))
                {
                    networkHandler.Registration_Success(client);
                }
                else {
                    networkHandler.Registration_UsernameAlreadyExists(client);
                }
            }
            else {
                networkHandler.Registration_InvalidPassword(client, "Password must have at least 8 characters.");
            }
        }
        else {
            networkHandler.Registration_NotAvailable(client);
        }
    }

    @Override
    public void Login(Client client, String username, String password) {
        if (client.GetState() == ClientState.Connected) {
            if (dataBaseManager.Check(username, Hash.GetHashFor(password)))
            {
                client.SetState(ClientState.LoggedIn);
                client.SetName(username);
                System.out.println(client.GetName() + " logged in.");
                networkHandler.Login_Success(client);
            }
            else {
                networkHandler.Login_InvalidUsernameOrPassword(client);
            }
        }
        else {
            networkHandler.Login_NotAvailable(client);
        }
    }

    @Override
    public void Logout(Client client) {
        if (client.GetState() == ClientState.LoggedIn) {
            client.SetState(ClientState.Connected);
            System.out.println(client.GetName() + " logged out.");
            client.SetName(null);
            networkHandler.Logout_Success(client);
        }
        else if (client.GetState() == ClientState.Waiting) {
            client.GetRoom().RemoveClient(client);
            System.out.println(client.GetName() + " left queue and logged out.");
            client.SetState(ClientState.Connected);
            client.SetName(null);
            networkHandler.Logout_Success(client);
        }
        else if (client.GetState() == ClientState.Loading || client.GetState() == ClientState.Ready || client.GetState() == ClientState.Playing) {
            client.GetRoom().RemoveClient(client);
            System.out.println(client.GetName() + " left game and logged out");
            client.SetState(ClientState.Connected);
            client.SetName(null);
            networkHandler.Logout_Success(client);
        }
        else {
            networkHandler.Logout_NotAvailable(client);
        }
    }

    @Override
    public void Enter(Client client, int players) {
        if (client.GetState() == ClientState.LoggedIn) {
            client.SetState(ClientState.Waiting);
            Room room = rooms.get(players);
            if (room == null) {
                rooms.put(players, new Room());
                room = rooms.get(players);
            }
            room.AddPlayer(client);
            System.out.println(client.GetName() + " is waiting for " + players + " players");
            networkHandler.Enter_Success(client);
            if (room.HasEnoughPlayers(players)) {
                System.out.println("Game started.");
                rooms.remove(players);
                int level_id = 42; // todo: generate level_ID
                room.Initialize(level_id);
                for(Client cli : room.GetClients()) {
                    cli.SetState(ClientState.Loading);
                    networkHandler.CheckLevel(cli, level_id);
                }
            }
        }
        else {
            networkHandler.Enter_NotAvailable(client);
        }
    }

    @Override
    public void Leave(Client client) {
        if (client.GetState() == ClientState.Waiting) {
            client.SetState(ClientState.LoggedIn);
            client.GetRoom().RemoveClient(client);
            System.out.println(client.GetName() + " left queue");
            networkHandler.Leave_Success(client);
        }
        else {
            networkHandler.Leave_NotAvailable(client);
        }
    }

    @Override
    public void Download(Client client, int level_id) {
        // todo
    }

    @Override
    public void WarehouseReady(Client client) {
        if (client.GetState() == ClientState.Loading) {
            System.out.println(client.GetName() + " is ready");
            client.SetState(ClientState.Ready);
            Room room = client.GetRoom();
            if (room.EverybodyReady()) {
                for (Client cli : room.GetClients()) {
                    cli.SetState(ClientState.Playing);
                    networkHandler.GameStarted(cli, room.WorkerIndexOf(cli));
                }
            }
        }
    }

    @Override
    public void OwnResults(Client client) {
        if (client.GetState() == ClientState.LoggedIn) {
            ArrayList<Integer> results = dataBaseManager.GetResultOf(client.GetName());
            String msg = "Wins: " + results.get(0) + ". Losses: " + results.get(1);
            networkHandler.ResultResponse(client, msg);
            System.out.println("Seinding wwn results...");
        }
    }

    @Override
    public void TopResults(Client client) {
        if (client.GetState() == ClientState.LoggedIn) {
            ArrayList<String> bestPlayers = dataBaseManager.GetBestPlayers();
            String msg = "Top 5 players:\n";
            for(String player : bestPlayers) {
                ArrayList<Integer> results = dataBaseManager.GetResultOf(player);
                msg = msg + player + ": " + results.get(0) + "-" + results.get(1) + "\n";
            }
            networkHandler.ResultResponse(client, msg);
        }
    }

    @Override
    public void Move(Client client, Direction dir) {
        if (client.GetState() == ClientState.Playing) {
            Room room = client.GetRoom();
            if (!room.TimeIsUp()) {
                room.MoveWorker(client, dir);
                int clientIndex = room.WorkerIndexOf(client);
                for(Client cli : room.GetClients()) {
                    networkHandler.WorkerMoved(cli, clientIndex, dir);
                }
            }
            else {
                FinishGame(room);
            }
        }
    }

    @Override
    public void PlaceHoney(Client client) {
        if (client.GetState() == ClientState.Playing) {
            Room room = client.GetRoom();
            if (!room.TimeIsUp()) {
                room.PlaceHoney(client);
                int clientIndex = room.WorkerIndexOf(client);
                for(Client cli : room.GetClients()) {
                    networkHandler.HoneyPlaced(cli, clientIndex);
                }
            }
            else {
                FinishGame(room);
            }
        }
    }

    @Override
    public void PlaceOil(Client client) {
        if (client.GetState() == ClientState.Playing) {
            Room room = client.GetRoom();
            if (!room.TimeIsUp()) {
                room.PlaceOil(client);
                int clientIndex = room.WorkerIndexOf(client);
                for(Client cli : room.GetClients()) {
                    networkHandler.OilPlaced(cli, clientIndex);
                }
            }
            else {
                FinishGame(room);
            }
        }
    }

    private void FinishGame(Room room) {
        System.out.println("Game Finished");
        ArrayList<String> winners = room.GetWinners();
        ArrayList<String> losers = room.GetLosers();
        for(Client cli : room.GetClients()) {
            networkHandler.GameFinished(cli);
        }
        room.Finish();
        for(String winner : winners) {
            dataBaseManager.Win(winner);
        }
        for(String loser : losers) {
            dataBaseManager.Lose(loser);
        }
    }
}
