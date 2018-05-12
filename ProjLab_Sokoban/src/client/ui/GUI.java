package client.ui;

import client.ui.Scenes.*;

import java.util.HashMap;


/**
 * 
 */
public class GUI extends UserInterface {

    /**
     *
     */
    private SokobanScene currentScene;

    /**
     *
     */
    private HashMap<String, SokobanScene> scenes = new HashMap<>();

    /**
     * Default constructor
     */
    public GUI() {
        scenes.put("welcome", new WelcomeScene());
        scenes.put("register", new RegisterScene());
        scenes.put("login", new LoginScene());
        scenes.put("connect", new ConnectScene());
        scenes.put("wait", new WaitScene());
    }

    /**
     * 
     */
    public void GoBack() {
        // TODO implement here
    }

    public void ConnectionResult(boolean res) // connection successful/failure, lasd: doksi
    {
        // TODO implement here
    }

    public void Disconnected() //
    {
        // TODO implement here
    }

    public void RegistrationSuccess() //
    {
        // TODO implement here
    }

    public void RegistrationFailure(String error) // error kiírása
    {
        // TODO implement here
    }

    public void LoginSuccess() //
    {
        // TODO implement here
    }

    public void LoginFailure(String err) // error kiírása
    {
        // TODO implement here
    }

    public void LogoutSuccess() //
    {
        // TODO implement here
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