package client.ui.Scenes;

import client.ui.GUI;
import client.ui.Scenes.SokobanScene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.*;

/**
 * 
 */
public class WaitScene extends SokobanScene {

    /**
     * Default constructor
     */
    public WaitScene() {
    }

    /**
     * 
     */
    private Button leaveButton;

    public static WaitScene Create(Stage window, GUI gui) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(GameScene.class.getResource("waitScene.fxml"));
        Parent root = fxmlLoader.load();
        WaitScene waitScene = fxmlLoader.getController();

        waitScene.window = window;
        waitScene.gui = gui;
        waitScene.scene = new Scene(root,MIN_WIDTH,MIN_HEIGHT);

        return waitScene;
    }

    // default Load method used

    // private user event handlers
    @FXML
    private void leaveButtonPressed() {
        gui.WaitScene_leaveButtonPressed();
    }

    // no network event handlers
}