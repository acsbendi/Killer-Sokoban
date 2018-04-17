package client.controller;

import common.util.Direction;

public interface ControllerLogic {
void ConnectionResult(boolean res);
void Disconnected();
void RegistrationSuccess();
void LoginSuccess();
void LoginFailure(String err);
void Results(String msg);
void ResultFailure(String err);
void EnterSuccess();
void EnterFailure(String err);
void LeaveSuccess();
void LeaveFailure(String err);
void CheckLevel(int level_id);
void GameStarted();
void WorkerMoved(int player,Direction dir);
void OilPlaced(int player,Direction dir);
void HoneyPlaced(int player,Direction dir);
void GameFinished();
}
