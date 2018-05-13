package client.ui.Scenes;

import client.ui.Camera;
import client.ui.GUI;
import client.ui.Scenes.SokobanScene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.*;

/**
 * 
 */
public class ConnectScene extends SokobanScene {

    /**
     * Default constructor
     */
    public ConnectScene() {
    }

    private TextField IPtext;

    private TextField portText;

    private Button connectButton;

    private Label message;

    // STATIC CREATE
    public static ConnectScene Create(Stage window, GUI gui) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(GameScene.class.getResource("connectScene.fxml"));
        Parent root = fxmlLoader.load();
        ConnectScene connectScene = fxmlLoader.getController();

        connectScene.window = window;
        connectScene.gui = gui;
        connectScene.scene = new Scene(root,MIN_WIDTH,MIN_HEIGHT);

        return connectScene;
    }

    // Load method
    @Override
    public void Load() {
        IPtext.setText("");
        portText.setText("");
        message.setText("Set IP address and port number.");
    }

    // User event handlers
    public void connectButtonPressed() {
        gui.ConnectScene_connectButtonPressed(IPtext.getText(), Integer.parseInt(portText.getText()));
    }

    // Network event handlers
    public void ConnectionFailed(){
        message.setText("Connection failed.");
    }
}