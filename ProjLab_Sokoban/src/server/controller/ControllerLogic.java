package server.controller;

import common.util.Direction;

public interface ControllerLogic {
    void Register(Client client, String username, String password);
    void Login(Client client, String username, String password);
    void Logout(Client client);
    void Enter(Client client, int players);
    void Leave(Client client);
    void Move(Client client, Direction dir);
    void PlaceHoney(Client client);
    void PlaceOil(Client client);
    void Download(Client client, int level_id);
    void WarehouseReady(Client client);
    void OwnResults(Client client);
    void TopResults(Client client);
    void AddClient(Client client);
    void Disconnected(Client client);
}
