package server.controller;

import common.util.Direction;

public class SokobanServer implements ControllerLogic {
    private NetworkHandler networkHandler;

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
    public void Register(Client client, String username, String password) {
        networkHandler.RegistrationSuccess(client);
    }

    @Override
    public void Login(Client client, String username, String password) {

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
