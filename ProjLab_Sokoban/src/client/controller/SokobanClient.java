package client.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import client.ui.GridCell;
import client.ui.UserInterface;
import common.model.*;
import common.util.Direction;
import common.util.JsonManager;
import common.util.Position;

public class SokobanClient implements UserInputExecutor,ControllerLogic {
    private UserInterface userInterface;
    private Warehouse warehouse; //kesobb inicializalando
    private NetworkHandler networkHandler=new NetworkHandler(this);
    private List<Worker> workers=new ArrayList<>();
    private ArrayList<Integer> points;

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
        networkHandler.Listen();
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
        TreeMap<Position,Field> pitch = new TreeMap<>();
        List<Box> boxes=new ArrayList<>();
        try {
            JsonManager.EnforceConfigFile(JsonManager.ResolveFileId(level_id), pitch, boxes,workers);
            warehouse=new Warehouse(pitch.values(),boxes);

            int maxColumn = pitch.keySet().stream().max((x,y) -> Integer.compare(x.column,y.column)).get().column;
            int minColumn = pitch.keySet().stream().min((x,y) -> Integer.compare(x.column,y.column)).get().column;
            int maxRow = pitch.keySet().stream().max((x,y) -> Integer.compare(x.row,y.row)).get().row;
            int minRow = pitch.keySet().stream().min((x,y) -> Integer.compare(x.row,y.row)).get().row;

            int width = maxColumn - minColumn + 1;
            int height = maxRow - minRow + 1;
            GridCell[][] gridCells = new GridCell[height][width];
            for (Map.Entry<Position,Field> entry : pitch.entrySet()
                    ) {
                gridCells[entry.getKey().row - minRow][entry.getKey().column - minColumn] = new GridCell(entry.getValue());
            }

            userInterface.InitializeWarehouse(gridCells);

            networkHandler.WarehouseReady();
        } catch (FileNotFoundException e) {
            System.out.println(level_id + ".json not found.");
            networkHandler.Download(level_id);
        } catch (ClassCastException e) {
            System.out.println("Wrong format.");
        }
    }

    @Override
    public void GameStarted(int worker) {
        System.out.println("SokobanClient.GameStarted()");
        Worker.localWorker = workers.get(worker);
        this.points = new ArrayList<Integer>();
        for(int i = 0; i < workers.size(); i++) {
            points.add(0);
        }
        userInterface.InitializePoints(points, worker);
        userInterface.GameStarted();
        userInterface.UpdateScreen();
    }

    @Override
    public void WorkerMoved(int player, Direction dir) {
        workers.get(player).Move(dir);
        for(int i = 0; i < workers.size(); i++) {
            points.set(i, workers.get(i).GetPoints());
        }
        userInterface.UpdateScreen();
    }
    @Override
    public void OilPlaced(int player) {
        workers.get(player).Place(new Oil());
        userInterface.UpdateScreen();
    }
    @Override
    public void HoneyPlaced(int player) {
        workers.get(player).Place(new Honey());
        userInterface.UpdateScreen();
    }
    @Override
    public void GameFinished() {
        warehouse = null;
        userInterface.GameFinished();
    }

    @Override
    public void OfflineFailure() {
        userInterface.OfflineFailure();
    }

    @Override
    public void OnlineFailure() { userInterface.OnlineFailure(); }

    @Override
    public synchronized void Connect(String IP, int port) {
        networkHandler.Connect(IP, port);
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
}
