package client.ui.Scenes;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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

    public void RegistrationSuccess(){
        // TODO implement here
    }

    public void RegistrationFailure(String errorMessage){
        // TODO implement here
    }
}