package server.controller;

import common.model.Honey;
import common.model.Oil;
import common.model.Warehouse;
import common.model.Worker;
import common.util.Direction;

import java.util.ArrayList;

public class Room {
    ArrayList<Client> clients;
    Warehouse warehouse;
    ArrayList<Worker> workers;

    public Room() {
        clients = new ArrayList<>();
    }

    public void AddPlayer(Client client) {
        clients.add(client);
        client.SetRoom(this);
    }

    public boolean HasEnoughPlayers(int players) {
        return clients.size() == players;
    }

    public ArrayList<Client> GetClients() {
        // todo: WARNING. clients might contain NULL
        return clients;
    }

    public int IndexOf(Client client) {
        return clients.indexOf(client);
    }

    public void RemoveClient(Client client) {
        clients.set(clients.indexOf(client), null);
    }

    public void Initialize(int level_id) {
        // todo
    }

    public boolean EverybodyReady() {
        for (Client client : clients) {
            if (client != null && client.GetState() != ClientState.Ready) {
                return false;
            }
        }
        return true;
    }

    public void MoveWorker(Client client, Direction dir) {
        workers.get(clients.indexOf(client)).Move(dir);
    }

    public void PlaceHoney(Client client) {
        workers.get(clients.indexOf(client)).Place(new Honey());
    }

    public void PlaceOil(Client client) {
        workers.get(clients.indexOf(client)).Place(new Oil());
    }
}
