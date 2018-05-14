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
 * A kapcsolódási képernyőt reprezentáló osztály
 */
public class ConnectScene extends SokobanScene {

    private static final int MIN_WIDTH = 1000;
    private static final int MIN_HEIGHT = 308;

    /**
     * Default constructor
     */
    public ConnectScene() {
    }
    /**Az IP cím beírásához a szövegdoboz
     */
    @FXML
    private TextField IPtext;
    /**A port szám beírásához a szövegdoboz
     * */
    @FXML
    private TextField portText;
    /**Kapcsolódás gomb
     * */
    private Button connectButton;
    /**Kiírandó szöveg, ezzel tud üzenni a játék a felhasználónak
     * */
    @FXML
    private Label message;
    /**Létrehoz egy ConnectionScenet és beállítja a kezdőértékeket
     *
     * @param window - erre a Stage-re hozza létre
     * @param gui - a felhasználói felület amihez tartozik
     *
     *@return a ConnectScene egy megfelelően alaphelyzetbe állított pldánya
     * */
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
    /**A Scene betöltésekor beállítja a megjelenítendő objektumok paramétereit
     * */
    // Load method
    @Override
    public void Load() {
        //IPtext.setText("");
        //portText.setText("");
        message.setText("Set IP address and port number.");
        window.setScene(scene);
    }
    /**A kapcsolódás gomb kattintásának eseménykezelője
     * */
    // private user event handlers
    @FXML
    private void connectButtonPressed() {
        gui.ConnectScene_connectButtonPressed(IPtext.getText(), Integer.parseInt(portText.getText()));
    }
    /**Ha a csatlakozás sikertelen volt kiírja ezt
     * */
    // Network event handlers
    public void ConnectionFailed(){
        message.setText("Connection failed.");
    }
}