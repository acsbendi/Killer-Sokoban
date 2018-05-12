package client.ui;

import client.ui.Scenes.SokobanScene;

import java.util.Set;

/**
 * 
 */
public class GUI extends UserInterface {

    /**
     * Default constructor
     */
    public GUI() {
    }

    /**
     * 
     */
    private SokobanScene currentScene;

    /**
     * 
     */
    private Set<SokobanScene> scenes;

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