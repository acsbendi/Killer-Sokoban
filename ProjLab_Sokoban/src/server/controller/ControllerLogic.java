package server.controller;

import common.util.Direction;

public interface ControllerLogic {
    public void Register(Client client, String username, String password);
    public void Login(Client client, String username, String password);
    public void Logout(Client client);
    public void Enter(Client client, int players);
    public void Leave(Client client);
    public void Move(Client client, Direction dir);
    public void PlaceHoney(Client client);
    public void PlaceOil(Client client);
    public void Download(Client client, int level_id);
    public void WarehouseReady(Client client);
    public void OwnResults(Client client);
    public void TopResults(Client client);
    void AddClient(Client client);
    void Disconnected(Client client);
}
