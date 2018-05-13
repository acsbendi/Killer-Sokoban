package client.ui.Scenes;

import client.ui.GUI;
import client.ui.Scenes.SokobanScene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.*;

/**
 * 
 */
public class WelcomeScene extends SokobanScene {

    /**
     * Default constructor
     */
    public WelcomeScene() {

    }

    /**
     * 
     */
    private Button loginButton;

    /**
     * 
     */
    private Button registerButton;

    /**
     * 
     */
    private Button disconnectButton;


    public static WelcomeScene Create(Stage window, GUI gui) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(GameScene.class.getResource("welcomeScene.fxml"));
        Parent root = fxmlLoader.load();
        WelcomeScene welcomeScene = fxmlLoader.getController();

        welcomeScene.window = window;
        welcomeScene.gui = gui;
        welcomeScene.scene = new Scene(root,MIN_WIDTH,MIN_HEIGHT);

        return welcomeScene;
    }

    // default Load() method is used

    private void loginButtonPressed() {

    }

    private void registerButtonPressed() {

    }

    private void disconnectButtonPressed() {

    }
}