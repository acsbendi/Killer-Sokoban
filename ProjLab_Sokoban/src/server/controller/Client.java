package server.controller;

public class Client {
    private ClientState state;
    private String name;

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
}
