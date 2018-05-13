package client.ui.Scenes;

import client.ui.Camera;
import client.ui.GUI;
import client.ui.Scenes.SokobanScene;
import javafx.fxml.FXML;
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

    private static final int MIN_WIDTH = 1000;
    private static final int MIN_HEIGHT = 378;

    /**
     * Default constructor
     */
    public ConnectScene() {
    }

    @FXML
    private TextField IPtext;

    @FXML
    private TextField portText;

    private Button connectButton;

    @FXML
    private Label message;

    // STATIC CREATE
    public static ConnectScene Create(Stage window, GUI gui) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ConnectScene.class.getResource("/connectscene.fxml"));
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
        window.setScene(scene);
        System.out.println("ConnectScene loaded");
    }

    // private user event handlers
    @FXML
    private void connectButtonPressed() {
        gui.ConnectScene_connectButtonPressed(IPtext.getText(), Integer.parseInt(portText.getText()));
    }

    // Network event handlers
    public void ConnectionFailed(){
        message.setText("Connection failed.");
    }
}