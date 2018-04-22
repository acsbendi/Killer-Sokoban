package client.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import client.ui.UserInterface;
import common.model.Box;
import common.model.Field;
import common.model.Warehouse;
import common.model.Worker;
import common.util.Direction;
import common.util.JsonManager;
import common.util.Position;

public class SokobanClient implements UserInputExecutor,ControllerLogic {
    private UserInterface userInterface;
    private Warehouse warehouse; //kesobb inicializalando
    private NetworkHandler networkHandler=new NetworkHandler(this);
    private List<Worker> workers=new ArrayList<>();

    public static SokobanClient Create(UserInterface userInterface) {
        SokobanClient client = new SokobanClient(userInterface);
        userInterface.SetUserInputExecutor(client);
        client.networkHandler = new NetworkHandler(client);
        return client;
    }

    private SokobanClient(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public synchronized void Iterate() {
        networkHandler.CollectMessages();
        networkHandler.SendMessages();
    }

    @Override
    public void ConnectionResult(boolean res) {
        userInterface.ConnectionResult(res);
    }

    @Override
    public synchronized void Disconnected() {
        userInterface.Disconnected();
    }

    @Override
    public void RegistrationSuccess() {
        userInterface.RegistrationSuccess();
    }

    @Override
    public void RegistrationFailure(String err) {
        userInterface.RegistrationFailure(err);
    }

    @Override
    public void LoginSuccess() {
        userInterface.LoginSuccess();
    }

    @Override
    public void LoginFailure(String err) {
        userInterface.LoginFailure(err);
    }

    @Override
    public void LogoutSuccess() {
        userInterface.LogoutSuccess();
    }

    @Override
    public void LogoutFailure(String err) {
        userInterface.LoginFailure(err);
    }

    @Override
    public void Results(String msg) {
        userInterface.Results(msg);
    }

    @Override
    public void ResultFailure(String err) {
        userInterface.RegistrationFailure(err);
    }

    @Override
    public void EnterSuccess() {
        userInterface.EnterSuccess();
    }

    @Override
    public void EnterFailure(String err) {
        userInterface.EnterFailure(err);
    }

    @Override
    public void LeaveSuccess() {
        userInterface.LeaveSuccess();
    }

    @Override
    public void LeaveFailure(String err) {
        userInterface.LeaveFailure(err);
    }

    @Override
    public void TryLoad(int level_id) {
    	Map<Position,Field> pitch = new HashMap<>(); 
    	List<Box> boxes=new ArrayList<>();
    	try {
			JsonManager.EnforceConfigFile(JsonManager.ResolveFileId(level_id),pitch,boxes,workers);
			warehouse=new Warehouse(pitch.values(),boxes);
		} catch (FileNotFoundException | ClassCastException e) {
			networkHandler.Download(level_id);
		}
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
        userInterface.OfflineFailure();
    }

    @Override
    public void OnlineFailure() { userInterface.OnlineFailure(); }

    @Override
    public synchronized void Connect() {
        networkHandler.Connect();
    }

    @Override
    public synchronized void Disconnect() {
        networkHandler.Disconnect();
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
    public synchronized void Move(Direction dir) { networkHandler.Move(dir); }
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
    public void Maketest(String[] params) {

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
