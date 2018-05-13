package client.ui;

import client.ui.Scenes.*;

import java.util.EnumMap;


/**
 * 
 */
public class GUI extends UserInterface {

    /**
     *
     */
    private SokobanScene currentScene;

    private ConnectScene connectScene = new ConnectScene();
    private GameScene gameScene = new GameScene();
    private LoginScene loginScene = new LoginScene();
    private MainScene mainScene = new MainScene();
    private RegisterScene registerScene = new RegisterScene();
    private ResultsScene resultsScene = new ResultsScene();
    private WaitScene waitScene = new WaitScene();
    private WelcomeScene welcomeScene = new WelcomeScene();

    /**
     * Default constructor
     */
    public GUI() {
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
}