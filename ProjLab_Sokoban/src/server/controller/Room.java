package server.controller;

import common.model.Warehouse;
import common.model.Worker;
import common.util.Direction;

import java.util.ArrayList;

public class Room {
    ArrayList<Client> clients;
    Warehouse warehouse;
    ArrayList<Worker> workers;

    public Room(int players) {
        clients = new ArrayList<>();
    }

    public void AddPlayer(Client client) {
        clients.add(client);
        client.SetRoom(this);
    }

    public boolean HasEnoughPlayers(int players) {
        return clients.size()== players;
    }

    public ArrayList<Client> GetClients() {
        return clients;
    }

    public void MoveWorker(Client client, Direction dir) {
        workers.get(clients.indexOf(client)).Move(dir);
    }

    public int IndexOf(Client client) {
        return clients.indexOf(client);
    }

    public void RemoveClient(Client client) {
        clients.remove(client);
    }

    public void Initialize(int level_id) {
        // todo
    }

    public boolean EverybodyReady() {
        for (Client client : clients) {
            if (client.GetState() != ClientState.Ready) {
                return false;
            }
        }
        return true;
    }
}
