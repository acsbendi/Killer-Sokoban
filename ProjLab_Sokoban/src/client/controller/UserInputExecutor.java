package client.controller;

import common.util.Direction;

public interface UserInputExecutor {
void Connect();
void Disconnect();
void Register(String username,String password);
void Login(String username,String password);
void Logout();
void Enter(int players);
void Leave();
void Move(Direction dir);
void PlaceHoney();
void PlaceOil();
void Step(Direction dir);
void PutHoney();
void PutOil();
void OwnResults();
void TopResults();
}
