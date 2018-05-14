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

/**
 * A regisztrációs képernyőt reprezentáló osztály
 */
public class RegisterScene extends SokobanScene {

    /**
     * Default constructor
     */
    public RegisterScene() {
    }

    /**
     * Szövegdoboz a felhasználói név megadására
     */
    @FXML
    private TextField userNameText;

    /**
     * Szövegdoboz a jelszó megadására
     */
    @FXML
    private TextField passwordText;

    /**
     * Szövegdoboz a jelszó ellenőrzésére
     */
    @FXML
    private TextField repeatPasswordText;

    /**
     * Regisztráció gomb
     */
    private Button registerButton;

    /**
     * Vissza gomb
     */
    private Button backButton;

    /**
     * Címke, amely megjeleníti a program üzeneteit a felhasználó számára
     */
    @FXML
    private Label message;
    /**Létrehoz egy RegisterScenet és beállítja a kezdőértékeket
     *
     * @param window - erre a Stage-re hozza létre
     * @param gui - a felhasználói felület amihez tartozik
     *
     *@return a RegisterScenet egy megfelelően alaphelyzetbe állított pldánya
     * */
    public static RegisterScene Create(Stage window, GUI gui) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(GameScene.class.getResource("/registerScene.fxml"));
        Parent root = fxmlLoader.load();
        RegisterScene registerScene = fxmlLoader.getController();

        registerScene.window = window;
        registerScene.gui = gui;
        registerScene.scene = new Scene(root,MIN_WIDTH,MIN_HEIGHT);

        return registerScene;
    }
    /**A Scene betöltésekor beállítja a megjelenítendő objektumok paramétereit
     * */
    @Override
    public void Load(){
        userNameText.setText("");
        passwordText.setText("");
        repeatPasswordText.setText("");
        message.setText("");
        window.setScene(scene);
    }
    /**A regisztráció gomb eseménykezelő függvénye.
     * Ellenőrzi, hogy a megadott értékek érvényesek-e és meghívja a felhasználói felület megfelelő függvényét.
     * */
    @FXML
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
    /**A vissza gomb eseménykezelő függvénye.
     * Meghívja a felhasználói felület megfelelő függvényét.
     * */
    @FXML
    private void backButtonPressed(){
        gui.RegisterScene_backButtonPressed();
    }
    /**Sikeres regisztráció esetén hívódik, erről visszajelzést ad a felhasználónak
     * */
    public void RegistrationSuccess(){
        message.setText("You have registered successfully");
    }
    /**Sikertelen regisztráció esetén kiírja a paraméterben átadott üzenetet.
     *
     * @param errorMessage - a kiírandó üzenet
     * */
    public void RegistrationFailure(String errorMessage){
        // TODO implement here
        message.setText(errorMessage);
    }
}