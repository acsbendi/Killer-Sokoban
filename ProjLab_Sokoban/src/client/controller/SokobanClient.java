package client.controller;

import common.model.Warehouse;
import client.ui.UserInterface;
import common.util.Direction;

public class SokobanClient implements UserInputExecutor,ControllerLogic {
private UserInterface userInterface; //TODO ezt hogy �ll�tom be?
private Warehouse warehouse=new Warehouse();
private NetworkHandler networkHandler=new NetworkHandler();
public void Iterate() {
	//TODO
}
@Override
public void ConnectionResult(boolean res) {
	// TODO Auto-generated method stub
	
}
@Override
public void Disconnected() {
	// TODO Auto-generated method stub
	
}
@Override
public void RegistrationSuccess() {
	// TODO Auto-generated method stub
	
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
public void GameStarted() {
	// TODO Auto-generated method stub
	
}
@Override
public void WorkerMoved(int player, Direction dir) {
	// TODO Auto-generated method stub
	
}
@Override
public void OilPlaced(int player, Direction dir) {
	// TODO Auto-generated method stub
	
}
@Override
public void HoneyPlaced(int player, Direction dir) {
	// TODO Auto-generated method stub
	
}
@Override
public void GameFinished() {
	// TODO Auto-generated method stub
	
}
@Override
public void Connect() {
	// TODO Auto-generated method stub
	
}
@Override
public void Disconnect() {
	// TODO Auto-generated method stub
	
}
@Override
public void Register(String username, String password) {
	// TODO Auto-generated method stub
	
}
@Override
public void Login(String username, String password) {
	// TODO Auto-generated method stub
	
}
@Override
public void Logout() {
	// TODO Auto-generated method stub
	
}
@Override
public void Enter(int players) {
	// TODO Auto-generated method stub
	
}
@Override
public void Leave() {
	// TODO Auto-generated method stub
	
}
@Override
public void Move(Direction dir) {
	// TODO Auto-generated method stub
	
}
@Override
public void PlaceHoney() {
	// TODO Auto-generated method stub
	
}
@Override
public void PlaceOil() {
	// TODO Auto-generated method stub
	
}
@Override
public void OwnResults() {
	// TODO Auto-generated method stub
	
}
@Override
public void TopResults() {
	// TODO Auto-generated method stub
	
}
}
