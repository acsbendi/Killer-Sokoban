package client.controller;

import client.network.NetworkHandler;
import common.model.Warehouse;
import client.ui.UserInterface;
import common.util.Direction;

public class SokobanClient implements UserInputExecutor,ControllerLogic {
    private UserInterface userInterface;
    private Warehouse warehouse;
    private NetworkHandler networkHandler;

    public static SokobanClient Create(UserInterface userInterface) {
        SokobanClient client = new SokobanClient(userInterface);
        userInterface.SetUserInputExecutor(client);
        client.networkHandler = NetworkHandler.Create(client);
        return client;
    }

    private SokobanClient(UserInterface userInterface) {
        this.userInterface = userInterface;
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
    public void OfflineFailure() {

    }

    @Override
    public synchronized void Connect() {

    }
    @Override
    public synchronized void Disconnect() {

    }
    @Override
    public synchronized void Register(String username, String password) {
        networkHandler.Register(username, password);
    }
    @Override
    public synchronized void Login(String username, String password) {
        networkHandler.Login(username, password);
    }
    @Override
    public synchronized void Logout() {
        networkHandler.Logout();
    }
    @Override
    public synchronized void Enter(int players) {
        networkHandler.Enter(players);
    }
    @Override
    public synchronized void Leave() {
        networkHandler.Leave();
    }
    @Override
    public synchronized void Move(Direction dir) {
        networkHandler.Move(dir);

    }
    @Override
    public synchronized void PlaceHoney() {
        networkHandler.PlaceHoney();
    }

    @Override
    public synchronized void PlaceOil() {
        networkHandler.PlaceOil();
    }    

    @Override
    public synchronized void OwnResults() {
        networkHandler.OwnResults();
    }

    @Override
    public synchronized void TopResults() {
        networkHandler.TopResults();
    }

    @Override
    public void Step(Direction dir) {

    }

    @Override
    public void PutHoney() {

    }

    @Override
    public void PutOil() {

    }
}
