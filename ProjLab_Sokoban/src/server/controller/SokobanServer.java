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

    }

    @Override
    public void Disconnected(Client client) {

    }

    @Override
    public void Register(Client client, String username, String password) {
        if (client.GetState() == ClientState.Connected) {
            if (password.length() >= 8) {
                if (dataBaseManager.Register(username, Hash.GetHashFor(password)))
                {
                    System.out.println("Someone registered(" + username + ", " + password + ")");
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
                System.out.println(client.GetName() + " logged in with: " + password);
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
            System.out.println(client.GetName() + " logged out");
            client.SetName("");
            networkHandler.Logout_Success(client);
        }
        else if (client.GetState() == ClientState.Waiting) {
            // todo
        }
        else if (client.GetState() == ClientState.Playing) {
            // todo
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
                rooms.put(players, new Room(players));
                room = rooms.get(players);
            }
            room.AddPlayer(client);
            System.out.println(client.GetName() + " is waiting for " + players + " players");
            networkHandler.Enter_Success(client);
            if (room.HasEnoughPlayers(players)) {
                rooms.remove(room);
                int level_id = 42; // todo: generate level_ID
                for(Client cli : room.GetClients()) {
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
    public void Move(Client client, Direction dir) {
        if (client.GetState() == ClientState.Playing) {
            Room room = client.GetRoom();
            room.MoveWorker(client, dir);
            int clientIndex = room.IndexOf(client);
            for(Client cli : room.GetClients()) {
                networkHandler.WorkerMoved(cli, clientIndex, dir);
            }
        }
    }

    @Override
    public void PlaceHoney(Client client) {

    }

    @Override
    public void PlaceOil(Client client) {

    }

    @Override
    public void Download(Client client, int level_id) {

    }

    @Override
    public void WarehouseReady(Client client) {

    }

    @Override
    public void OwnResults(Client client) {

    }

    @Override
    public void TopResults(Client client) {

    }
}
