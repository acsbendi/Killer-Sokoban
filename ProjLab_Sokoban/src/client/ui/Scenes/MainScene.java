package client.ui.Scenes;

import client.ui.GUI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.*;

/**
 *
 */
public class MainScene extends SokobanScene {

    /**
     * Default constructor
     */
    public MainScene() {
    }

    /**
     *
     */
    private Button enterButton;

    /**
     *
     */
    @FXML
    private TextField roomSizeText;

    /**
     *
     */
    private Button logoutButton;

    /**
     *
     */
    private Button resultButton;




    // STATIC CREATE
    public static MainScene Create(Stage window, GUI gui) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(GameScene.class.getResource("/mainScene.fxml"));
        Parent root = fxmlLoader.load();
        MainScene mainScene = fxmlLoader.getController();

        mainScene.window = window;
        mainScene.gui = gui;
        mainScene.scene = new Scene(root,MIN_WIDTH,MIN_HEIGHT);

        return mainScene;
    }

    public void Load() {
        window.setScene(scene);
        roomSizeText.setText("");
    }

    @FXML
    public void ResultButtonPressed() {
        gui.MainScene_resultButtonPressed();
    }

    @FXML
    public void LogoutButtonPressed() {
        gui.MainScene_logoutButtonPressed();
    }

    @FXML
    public void EnterButtonPressed() {
        int roomSize = Integer.parseInt(roomSizeText.getText());
        if(roomSize >= 2 && roomSize <=4)
            gui.MainScene_enterButtonPressed(roomSize);
    }

}