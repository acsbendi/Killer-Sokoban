package client.ui.Scenes;

import client.ui.GUI;
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
public class LoginScene extends SokobanScene {

    /**
     * Default constructor
     */
    public LoginScene() {
    }

    /**
     * 
     */
    @FXML
    private TextField userNameText;

    /**
     * 
     */
    @FXML
    private TextField passwordText;

    /**
     * 
     */
    private Button loginButton;

    /**
     * 
     */
    @FXML
    private Label message;

    /**
     * 
     */
    private Button backButton;

    // STATIC CREATE
    public static LoginScene Create(Stage window, GUI gui) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(GameScene.class.getResource("/loginScene.fxml"));
        Parent root = fxmlLoader.load();
        LoginScene loginScene = fxmlLoader.getController();

        loginScene.window = window;
        loginScene.gui = gui;
        loginScene.scene = new Scene(root,MIN_WIDTH,MIN_HEIGHT);

        return loginScene;
    }

    public void Load() {
        //userNameText.setText("");
        //passwordText.setText("");
        message.setText("Enter username and password.");
        window.setScene(scene);
    }

    @FXML
    private void loginButtonPressed() {
        gui.LoginScene_loginButtonPressed(userNameText.getText(), passwordText.getText());
    }

    @FXML
    private void backButtonPressed() {
        gui.LoginScene_backButtonPressed();
    }

    public void LoginFailure(String errorMessage){
        message.setText(errorMessage);
    }
}