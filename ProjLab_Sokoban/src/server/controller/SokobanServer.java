package server.controller;

import common.util.Direction;
import server.network.NetworkHandler;

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
        while (true) {
            networkHandler.AcceptClient();
            networkHandler.CollectMessages();
            networkHandler.SendMessages();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void Register(Client client, String username, String password) {

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
