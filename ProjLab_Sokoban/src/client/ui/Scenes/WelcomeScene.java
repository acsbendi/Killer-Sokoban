package client.ui.Scenes;

import client.ui.GUI;
import client.ui.Scenes.SokobanScene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

/**
 * A köszöntő képernyőt reprezentáló osztály
 */
public class WelcomeScene extends MenuScene {

    /**
     * Default constructor
     */
    public WelcomeScene() {

    }

    /**
     * Bejelentkezés gomb
     */
    private Button loginButton;

    /**
     * Regisztráció gomb
     */
    private Button registerButton;

    /**
     * Lecsatlakozás gomb
     */
    private Button disconnectButton;

    /**Létrehoz egy WelcomeScenet és beállítja a kezdőértékeket
     *
     * @param window - erre a Stage-re hozza létre
     * @param gui - a felhasználói felület amihez tartozik
     *
     *@return a WelcomeScenet   egy megfelelően alaphelyzetbe állított pldánya
     * */
    public static WelcomeScene Create(Stage window, GUI gui) throws IOException {
        return (WelcomeScene)SokobanScene.Create("/welcomeScene.fxml",window,gui);
    }

    // default Load() method is used

    // private user event handlers
    /**A bejelentkezés gomb megnyomásának eseménykezelő függvénye.
     * Meghívja a felhaszálói felület megfelelő függvényét
     * */
    @FXML
    private void loginButtonPressed() {
        gui.WelcomeScene_loginButtonPressed();
    }
    /**A regisztráció gomb megnyomásának eseménykezelő függvénye.
     * Meghívja a felhaszálói felület megfelelő függvényét
     * */
    @FXML
    private void registerButtonPressed() {
        gui.WelcomeScene_registerButtonPressed();
    }
    /**A lecsatlakozás gomb megnyomásának eseménykezelő függvénye.
     * Meghívja a felhaszálói felület megfelelő függvényét
     * */
    @FXML
    private void disconnectButtonPressed() {
        gui.WelcomeScene_disconnectButtonPressed();
    }

    // no network event handlers used in GUI
}