package server.controller;

import common.util.Direction;

import java.util.HashMap;

public class SokobanServer implements ControllerLogic {
    private NetworkHandler networkHandler;
    private HashMap<Client, Room> rooms;

    public static SokobanServer Create() {
        SokobanServer sokobanServer = new SokobanServer();
        sokobanServer.networkHandler = new NetworkHandler(sokobanServer);
        return sokobanServer;
    }

    private SokobanServer() {

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
                if (true) // a felhasználónév nem foglalt
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

        }
        else {
            networkHandler.NotAvailable();
        }
    }

    @Override
    public void Logout(Client client) {

    }

    @Override
    public void Enter(Client client, int players) {

    }

    @Override
    public void Leave(Client client) {

    }

    @Override
    public void Move(Client client, Direction dir) {

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
