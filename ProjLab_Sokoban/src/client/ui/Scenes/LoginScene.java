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
public class LoginScene extends MenuScene {

    private static final String DEFAULT_MESSAGE = "Enter username and password.";

    /**
     * Szövegdoboz a felhasználói név megadására
     */
    @FXML
    private TextField userNameText;

    /**
     * Szövegdoboz a jelzsó megadására
     */
    @FXML
    private TextField passwordText;

    /**
     * Bejelentkező gomb
     */
    private Button loginButton;

    /**
     * Címke, amely megjeleníti a program üzeneteit a felhasználó számára
     */
    @FXML
    private Label message;

    /**
     * Vissza gomb
     */
    private Button backButton;
    /**Létrehoz egy LoginScenet és beállítja a kezdőértékeket
     *
     * @param window - erre a Stage-re hozza létre
     * @param gui - a felhasználói felület amihez tartozik
     *
     *@return a LoginScenet  egy megfelelően alaphelyzetbe állított pldánya
     * */
    // STATIC CREATE
    public static LoginScene Create(Stage window, GUI gui) throws Exception {
        return (LoginScene)SokobanScene.Create("/loginscene.fxml",window,gui);
    }
    /**A Scene betöltésekor beállítja a megjelenítendő objektumok paramétereit
     * */
    public void Load() {
        //userNameText.setText("");
        //passwordText.setText("");
        message.setText(DEFAULT_MESSAGE);
        super.Load();
    }
    /**A bejelentkező gomb megnyomásának eseménykezelő függvénye.
     * Meghívja a felhaszálói felület megfelelő függvényét
     * */
    @FXML
    private void loginButtonPressed() {
        gui.LoginScene_loginButtonPressed(userNameText.getText(), passwordText.getText());
    }
    /**A vissza gomb megnyomásának eseménykezelő függvénye.
     * Meghívja a felhaszálói felület megfelelő függvényét
     * */
    @FXML
    private void backButtonPressed() {
        gui.LoginScene_backButtonPressed();
    }
    /**Bejelentkezési hiba eseténe kiírja a paraméterben megkapott üzenetet.
     * @param errorMessage - a kiírandó üzenet
     * */
    public void LoginFailure(String errorMessage){
        message.setText(errorMessage);
    }
}