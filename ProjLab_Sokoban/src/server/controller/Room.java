package server.controller;

import common.model.*;
import common.util.Direction;
import common.util.JsonManager;
import common.util.Position;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.TreeMap;

public class Room {
    ArrayList<Client> clients;
    Warehouse warehouse;
    ArrayList<Worker> workers;
    HashMap<Client, Worker> map;
    HashMap<Worker, String> names;
    long lastPointChange;

    public Room() {
        clients = new ArrayList<>();
        workers = new ArrayList<>();
        map = new HashMap<>();
        names = new HashMap<>();
    }

    public void AddPlayer(Client client) {
        clients.add(client);
        client.SetRoom(this);
    }

    public boolean HasEnoughPlayers(int players) {
        return clients.size() == players;
    }

    public ArrayList<Client> GetClients() {
        return clients;
    }

    public int WorkerIndexOf(Client client) {
        return workers.indexOf(map.get(client));
    }

    public void RemoveClient(Client client) {
        clients.remove(client);
    }

    public void Initialize(int level_id) {
        InputStream inputStream = JsonManager.ResolveFileId(level_id);
        TreeMap<Position, Field> pitch = new TreeMap<>();
        ArrayList<Box> boxes = new ArrayList<>();
        try {
            JsonManager.EnforceConfigFile(inputStream, pitch, boxes, workers);
            warehouse = new Warehouse(pitch.values(), boxes);
            for(int i = 0; i < clients.size(); i++) {
                map.put(clients.get(i), workers.get(i));
                names.put(workers.get(i), clients.get(i).GetName());
            }
        } catch (FileNotFoundException | ClassCastException e) {
            e.printStackTrace();
        }
    }

    public boolean EverybodyReady() {
        for (Client client : clients) {
            if (client.GetState() != ClientState.Ready) {
                return false;
            }
        }
        lastPointChange = System.currentTimeMillis();
        return true;
    }

    public void MoveWorker(Client client, Direction dir) {
        int pointsDifference = 0;
        for(Worker worker : workers) {
            pointsDifference -= worker.GetPoints();
        }
        map.get(client).Move(dir);
        for(Worker worker : workers) {
            pointsDifference += worker.GetPoints();
        }
        if (pointsDifference == 1) {
            lastPointChange = System.currentTimeMillis();
        }
    }

    public void PlaceHoney(Client client) {
        map.get(client).Place(new Honey());
    }

    public void PlaceOil(Client client) {
        map.get(client).Place(new Oil());
    }

    public boolean TimeIsUp() {
        return (System.currentTimeMillis() - lastPointChange) >= 40000;
    }

    public ArrayList<String> GetWinners() {
        int max = workers.get(0).GetPoints();
        for(Worker worker : workers) {
            if (worker.GetPoints() > max) {
                max = worker.GetPoints();
            }
        }
        ArrayList<String> winners = new ArrayList<>();
        for(Worker worker : workers) {
            if (worker.GetPoints() == max) {
                winners.add(names.get(worker));
            }
        }
        return winners;
    }

    public ArrayList<String> GetLosers() {
        int max = workers.get(0).GetPoints();
        for(Worker worker : workers) {
            if (worker.GetPoints() > max) {
                max = worker.GetPoints();
            }
        }
        ArrayList<String> losers = new ArrayList<>();
        for(Worker worker : workers) {
            if (worker.GetPoints() < max) {
                losers.add(names.get(worker));
            }
        }
        return losers;
    }

    public void Finish() {
        for (Client cli : clients) {
            cli.SetState(ClientState.LoggedIn);
            cli.SetRoom(null);
        }
    }
}