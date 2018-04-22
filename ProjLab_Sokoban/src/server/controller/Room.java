package server.controller;

import common.model.Warehouse;
import common.model.Worker;
import common.util.Direction;

import java.util.ArrayList;

public class Room {
    ArrayList<Client> clients;

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

    }

    public int IndexOf(Client client) {
        return clients.indexOf(client);
    }
}
