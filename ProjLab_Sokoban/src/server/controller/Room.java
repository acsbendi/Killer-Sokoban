package server.controller;

import common.model.*;
import common.util.Direction;
import common.util.JsonManager;
import common.util.Position;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class Room {
    ArrayList<Client> clients;
    Warehouse warehouse;
    ArrayList<Worker> workers;
    HashMap<Client, Worker> map;

    public Room() {
        clients = new ArrayList<>();
        workers = new ArrayList<>();
        map = new HashMap<>();
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
        File file = JsonManager.ResolveFileId(level_id);
        HashMap<Position, Field> pitch = new HashMap<>();
        ArrayList<Box> boxes = new ArrayList<>();
        try {
            JsonManager.EnforceConfigFile(file, pitch, boxes, workers);
            for(int i = 0; i < clients.size(); i++) {
                map.put(clients.get(i), workers.get(i));
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
        return true;
    }

    public void MoveWorker(Client client, Direction dir) {
        map.get(client).Move(dir);
    }

    public void PlaceHoney(Client client) {
        map.get(client).Place(new Honey());
    }

    public void PlaceOil(Client client) {
        map.get(client).Place(new Oil());
    }
}