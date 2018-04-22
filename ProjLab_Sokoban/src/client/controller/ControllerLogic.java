package client.controller;

import common.util.Direction;

public interface ControllerLogic {
void ConnectionResult(boolean res);
void Disconnected();
void RegistrationSuccess();
void RegistrationFailure(String err);
void LoginSuccess();
void LoginFailure(String err);
void LogoutSuccess();
void LogoutFailure(String err);
void Results(String msg);
void ResultFailure(String err);
void EnterSuccess();
void EnterFailure(String err);
void LeaveSuccess();
void LeaveFailure(String err);
void GameStarted(int worker);
void WorkerMoved(int player,Direction dir);
void OilPlaced(int player);
void HoneyPlaced(int player);
void GameFinished();
void OfflineFailure();
void OnlineFailure();
void TryLoad(int level_id);
}
