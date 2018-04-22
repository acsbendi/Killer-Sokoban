package server.controller;

import common.util.Direction;

public class Client {
    private ClientState state;
    private String name;
    private Room room;

    public Client() {
        state = ClientState.Connected;
    }

    public String GetName() {
        return name;
    }

    public void SetName(String name) {
        this.name = name;
    }

    public ClientState GetState() {
        return state;
    }

    public void SetState(ClientState state) {
        this.state = state;
    }

    public void SetRoom(Room room) {
        this.room = room;
    }

    public Room GetRoom() {
        return room;
    }
}
