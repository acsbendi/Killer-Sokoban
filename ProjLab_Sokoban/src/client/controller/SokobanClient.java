package client.controller;

import client.network.NetworkHandler;
import common.model.Warehouse;
import client.ui.UserInterface;
import common.util.Direction;

public class SokobanClient implements UserInputExecutor,ControllerLogic {
private UserInterface userInterface; //TODO ezt hogy állítom be?
private Warehouse warehouse;
private NetworkHandler networkHandler;

public static SokobanClient Create(UserInterface userInterface) {
    SokobanClient client = new SokobanClient();
    client.userInterface = userInterface;
    client.networkHandler = NetworkHandler.Create(client);
    return client;
}

private SokobanClient() {
    warehouse = new Warehouse();
}

public synchronized void Iterate() {
	//TODO
}
@Override
public void ConnectionResult(boolean res) {
	// TODO Auto-generated method stub
	
}
@Override
public synchronized void Disconnected() {
	// TODO Auto-generated method stub
	
}
@Override
public void RegistrationSuccess() {
	// TODO Auto-generated method stub
	
}

    @Override
    public void RegistrationFailure(String err) {

    }

    @Override
public void LoginSuccess() {
	// TODO Auto-generated method stub
	
}
@Override
public void LoginFailure(String err) {
	// TODO Auto-generated method stub
	
}

    @Override
    public void LogoutSuccess() {

    }

    @Override
    public void LogoutFailure(String err) {

    }

    @Override
public void Results(String msg) {
	// TODO Auto-generated method stub
	
}
@Override
public void ResultFailure(String err) {
	// TODO Auto-generated method stub
	
}
@Override
public void EnterSuccess() {
	// TODO Auto-generated method stub
	
}
@Override
public void EnterFailure(String err) {
	// TODO Auto-generated method stub
	
}
@Override
public void LeaveSuccess() {
	// TODO Auto-generated method stub
	
}
@Override
public void LeaveFailure(String err) {
	// TODO Auto-generated method stub
	
}
@Override
public void CheckLevel(int level_id) {
	// TODO Auto-generated method stub
	
}

@Override
public void GameStarted(int worker) {

}

@Override
public void WorkerMoved(int player, Direction dir) {
	// TODO Auto-generated method stub
	
}
@Override
public void OilPlaced(int player) {
	// TODO Auto-generated method stub
	
}
@Override
public void HoneyPlaced(int player) {
	// TODO Auto-generated method stub
	
}
@Override
public void GameFinished() {
	// TODO Auto-generated method stub
	
}
@Override
public synchronized void Connect() {
	// TODO Auto-generated method stub
	
}
@Override
public synchronized void Disconnect() {
	// TODO Auto-generated method stub
	
}
@Override
public synchronized void Register(String username, String password) {
	// TODO Auto-generated method stub
	
}
@Override
public synchronized void Login(String username, String password) {
	// TODO Auto-generated method stub
	
}
@Override
public synchronized void Logout() {
	// TODO Auto-generated method stub
	
}
@Override
public synchronized void Enter(int players) {
	// TODO Auto-generated method stub
	
}
@Override
public synchronized void Leave() {
	// TODO Auto-generated method stub
	
}
@Override
public synchronized void Move(Direction dir) {
	// TODO Auto-generated method stub
	
}
@Override
public synchronized void PlaceHoney() {
	// TODO Auto-generated method stub
	
}
@Override
public synchronized void PlaceOil() {
	// TODO Auto-generated method stub
	
}
@Override
public synchronized void OwnResults() {
	// TODO Auto-generated method stub
	
}
@Override
public synchronized void TopResults() {
	// TODO Auto-generated method stub
	
}
}
