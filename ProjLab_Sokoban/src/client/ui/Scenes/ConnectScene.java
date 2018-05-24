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
public class ConnectScene extends MenuScene {

    private static final int MIN_WIDTH = 600;
    private static final int MIN_HEIGHT = 400;

    private static final String DEFAULT_IP = "vm.ik.bme.hu";
    private static final String DEFAULT_PORT = "7305";
    private static final String DEFAULT_MESSAGE = "Set IP address and port number.";

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
        return (ConnectScene)SokobanScene.Create("/connectscene.fxml",window,gui);
    }

    /**A Scene betöltésekor beállítja a megjelenítendő objektumok paramétereit
     * */
    // Load method
    @Override
    public void Load() {
        IPtext.setText(DEFAULT_IP);
        portText.setText(DEFAULT_PORT);
        message.setText(DEFAULT_MESSAGE);
        super.Load();
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