package client.ui;

import client.ui.Scenes.*;

import java.util.EnumMap;


/**
 * 
 */
public class GUI extends UserInterface {

    private enum Scenes { Welcome, Register, Login, Connect, Wait, Main, Results, Game }

    /**
     *
     */
    private SokobanScene currentScene;

    /**
     *
     */
    private EnumMap<Scenes, SokobanScene> scenes = new EnumMap<>(Scenes.class);

    /**
     * Default constructor
     */
    public GUI() {
        scenes.put(Scenes.Welcome, new WelcomeScene());
        scenes.put(Scenes.Register, new RegisterScene());
        scenes.put(Scenes.Login, new LoginScene());
        scenes.put(Scenes.Connect, new ConnectScene());
        scenes.put(Scenes.Wait, new WaitScene());
        scenes.put(Scenes.Main, new MainScene());
        scenes.put(Scenes.Results, new ResultsScene());
        scenes.put(Scenes.Game, new GameScene());

        currentScene = scenes.get(Scenes.Connect);
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
        if (currentScene == scenes.get("connect")) {
            if (success) {
                currentScene.Hide();
                currentScene = scenes.get("")
            }
        }
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