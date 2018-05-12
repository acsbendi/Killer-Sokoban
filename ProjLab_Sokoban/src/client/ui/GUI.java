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

    ConnectScene connectScene = new ConnectScene();
    GameScene gameScene = new GameScene();
    LoginScene loginScene = new LoginScene();
    MainScene mainScene = new MainScene();
    RegisterScene registerScene = new RegisterScene();
    ResultsScene resultsScene = new ResultsScene();
    WaitScene waitScene = new WaitScene();
    WelcomeScene welcomeScene = new WelcomeScene();

    /**
     * Default constructor
     */
    public GUI() {
        currentScene = connectScene;
        currentScene.Load();
    }

    /**
     * 
     */
    public void GoBack() {
        // TODO implement here
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

    public void LoginSuccess() //
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
        if ()
    }

    public void LogoutFailure(String err) // error kiírása
    {
        // TODO implement here
    }

    public void Results(String msg) // msg kiírása
    {
        // TODO implement here
    }

    public void ResultFailure(String err) // error kiírása
    {
        // TODO implement here
    }

    public void EnterSuccess() //
    {
        // TODO implement here
    }

    public void EnterFailure(String err) // error kiírása
    {
        // TODO implement here
    }

    public void LeaveSuccess() //
    {
        // TODO implement here
    }

    public void LeaveFailure(String err) // error kiírása
    {
        // TODO implement here
    }

    public void GameStarted()
    {
        // TODO implement here
    }

    public void UpdateScreen()
    {
        // TODO implement here
    }

    public void GameFinished()
    {
        // TODO implement here
    }

    public void OfflineFailure() // ha online parancsot offline használunk
    {
        // TODO implement here
    }

    public void OnlineFailure() // ez meg fordítva
    {
        // TODO implement here
    }

    public void MakeTestFailure() // ez meg fordítva
    {
        // TODO implement here
    }
}