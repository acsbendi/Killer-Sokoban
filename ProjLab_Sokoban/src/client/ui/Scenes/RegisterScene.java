package client.ui.Scenes;

import client.ui.GUI;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * 
 */
public class RegisterScene extends SokobanScene {

    /**
     * Default constructor
     */
    public RegisterScene() {
    }

    /**
     * 
     */
    private TextField userNameText;

    /**
     * 
     */
    private TextField passwordText;

    /**
     * 
     */
    private TextField repeatPasswordText;

    /**
     * 
     */
    private Button registerButton;

    /**
     * 
     */
    private Button backButton;

    /***/
    private Label message;

    public static RegisterScene Create(Stage window, GUI gui) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(GameScene.class.getResource("registerScene.fxml"));
        Parent root = fxmlLoader.load();
        RegisterScene registerScene = fxmlLoader.getController();

        registerScene.window = window;
        registerScene.gui = gui;
        registerScene.scene = new Scene(root,MIN_WIDTH,MIN_HEIGHT);

        return registerScene;
    }

    @Override
    public void Load(){
        userNameText.setText("");
        passwordText.setText("");
        repeatPasswordText.setText("");
        message.setText("");
    }

    private void registerButtonPressed(){
        if("requirements".equals(passwordText.getText())){
            message.setText("Sorry, but your password must contain a capital letter, two numbers, a symbol, an inspiring message, a haiku, a spell, a hieroglyph, a gang sign and the blood of a virgin.");
        }
        if(userNameText.getText().isEmpty()){
            message.setText("Please enter a username");
            return;
        }
        if(passwordText.getText().isEmpty())
        {
            message.setText("Please enter a password");
            return;
        }
        if(repeatPasswordText.getText().isEmpty()){
            message.setText("Please repeat your password");
            return;
        }
        if(passwordText.getText().equals(repeatPasswordText.getText()))
            gui.RegisterScene_registerButtonPressed(userNameText.getText(), passwordText.getText());
        else message.setText("The given passwords don't match");
    }

    private void backButtonPressed(){
        gui.RegisterScene_backButtonPressed();
    }

    public void RegistrationSuccess(){
        // TODO implement here
        message.setText("You have registered successfully");
    }

    public void RegistrationFailure(String errorMessage){
        // TODO implement here
        message.setText(errorMessage);
    }
}