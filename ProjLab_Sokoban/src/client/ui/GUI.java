package client.ui;

import client.ui.Scenes.*;
import common.util.Direction;
import javafx.stage.Stage;

import java.util.EnumMap;


/**
 * 
 */
public class GUI extends UserInterface {

    /**
     *
     */
    private SokobanScene currentScene;

    private ConnectScene connectScene;
    private GameScene gameScene;
    private LoginScene loginScene;
    private MainScene mainScene;
    private RegisterScene registerScene;
    private ResultsScene resultsScene;
    private WaitScene waitScene;
    private WelcomeScene welcomeScene;

    /**
     * Default constructor
     */
    public GUI(Stage window) throws Exception{
        connectScene = ConnectScene.Create(window, this);
        gameScene = GameScene.Create(window,this);
        loginScene = LoginScene.Create(window, this);
        mainScene = MainScene.Create(window, this);
        registerScene = RegisterScene.Create(window, this);
        resultsScene = ResultsScene.Create(window, this);
        waitScene = WaitScene.Create(window, this);
        welcomeScene = WelcomeScene.Create(window, this);

        window.setTitle("Sokoban Game");

        currentScene = connectScene;
        currentScene.Load();
    }

    @Override
    public void InitializeWarehouse(GridSquare[][] gridSquares){
        gameScene.setWarehouseView(new WarehouseView(gridSquares));
    }

    public void ConnectionResult(boolean success) // connection successful/failure, lasd: doksi
    {
        if (currentScene == connectScene) {
            if (success) {
                currentScene.Hide();
                currentScene = welcomeScene;
                currentScene.Load();
            }
            else {
                connectScene.ConnectionFailed();
            }
        }
    }

    public void Disconnected()
    {
        currentScene.Hide();
        currentScene = connectScene;
        currentScene.Load();
    }

    public void RegistrationSuccess()
    {
        if (currentScene == registerScene) {
            registerScene.RegistrationSuccess();
        }
    }

    public void RegistrationFailure(String error)
    {
        if (currentScene == registerScene) {
            registerScene.RegistrationFailure(error);
        }
    }

    public void LoginSuccess()
    {
        if (currentScene == loginScene) {
            currentScene.Hide();
            currentScene = mainScene;
            currentScene.Load();
        }
    }

    public void LoginFailure(String err) // error kiírása
    {
        if (currentScene == loginScene) {
            loginScene.LoginFailure(err);
        }
    }

    public void LogoutSuccess() //
    {
        if (currentScene == mainScene) {
            currentScene.Hide();
            currentScene = loginScene;
            currentScene.Load();
        }
    }

    public void LogoutFailure(String err)
    { }

    public void Results(String msg)
    {
        if (currentScene == resultsScene) {
            resultsScene.ShowResults(msg);
        }
    }

    public void ResultFailure(String err) // error kiírása
    { }

    public void EnterSuccess()
    {
        if (currentScene == mainScene) {
            currentScene.Hide();
            currentScene = waitScene;
            currentScene.Load();
        }
    }

    public void EnterFailure(String err)
    { }

    public void LeaveSuccess()
    {
        if (currentScene == waitScene) {
            currentScene.Hide();
            currentScene = mainScene;
            currentScene.Load();
        }
    }

    public void LeaveFailure(String err) // error kiírása
    { }

    public void GameStarted()
    {
        if (currentScene == waitScene) {
            currentScene.Hide();
            currentScene = gameScene;
            currentScene.Load();  // TODO: Reference to warehouse is necessary!!
        }
    }

    public void UpdateScreen()
    {
        if (currentScene == gameScene) {
            gameScene.UpdateScreen();
        }
    }

    public void GameFinished()
    {
        if (currentScene == gameScene) {
            currentScene.Hide();
            currentScene = mainScene;
            currentScene.Load();
        }
    }

    public void OfflineFailure() // ha online parancsot offline használunk
    { }

    public void OnlineFailure() // ez meg fordítva
    { }

    public void MakeTestFailure() // ez meg fordítva
    { }



    public void ConnectScene_connectButtonPressed(String IP, int port) {
        if (currentScene == connectScene)
            userInputExecutor.Connect(IP, port);
    }

    public void WelcomeScene_loginButtonPressed() {
        if (currentScene == welcomeScene) {
            currentScene.Hide();
            currentScene = loginScene;
            currentScene.Load();
        }
    }

    public void WelcomeScene_registerButtonPressed() {
        if (currentScene == welcomeScene) {
            currentScene.Hide();
            currentScene = registerScene;
            currentScene.Load();
        }
    }

    public void WelcomeScene_disconnectButtonPressed() {
        if (currentScene == welcomeScene) {
            userInputExecutor.Disconnect();
        }
    }

    public void LoginScene_loginButtonPressed(String username, String password) {
        if (currentScene == loginScene) {
            userInputExecutor.Login(username, password);
        }
    }

    public void LoginScene_backButtonPressed() {
        if (currentScene == loginScene) {
            currentScene.Hide();
            currentScene = welcomeScene;
            currentScene.Load();
        }
    }

    public void RegisterScene_registerButtonPressed(String username, String password) {
        if (currentScene == registerScene) {
            userInputExecutor.Register(username, password);
        }
    }

    public void RegisterScene_backButtonPressed() {
        if (currentScene == registerScene) {
            currentScene.Hide();
            currentScene = welcomeScene;
            currentScene.Load();
        }
    }

    public void MainScene_enterButtonPressed(int roomSize) {
        if (currentScene == mainScene) {
            userInputExecutor.Enter(roomSize);
        }
    }

    public void MainScene_resultButtonPressed() {
        if (currentScene == mainScene) {
            userInputExecutor.OwnResults();
            currentScene.Hide();
            currentScene = resultsScene;
            currentScene.Load();
        }
    }

    public void MainScene_logoutButtonPressed() {
        if (currentScene == mainScene) {
            userInputExecutor.Logout();
        }
    }

    public void ResultsScene_ownResultClicked(boolean ownResults) {
        if (currentScene == resultsScene) {
            if (ownResults) {
                userInputExecutor.OwnResults();
            }
            else {
                userInputExecutor.TopResults();
            }
        }
    }

    public void ResultsScene_backButtonPressed() {
        if (currentScene == resultsScene) {
            currentScene.Hide();
            currentScene = mainScene;
            currentScene.Load();
        }
    }

    public void WaitScene_leaveButtonPressed() {
        if (currentScene == waitScene) {
            userInputExecutor.Leave();
        }
    }

    public void GameScene_MoveWorker(Direction dir) {
        if (currentScene == gameScene) {
            userInputExecutor.Move(dir);
        }
    }

    public void GameScene_PlaceHoney() {
        if (currentScene == gameScene) {
            userInputExecutor.PlaceHoney();
        }
    }

    public void GameScene_PlaceOil() {
        if (currentScene == gameScene) {
            userInputExecutor.PlaceOil();
        }
    }
}