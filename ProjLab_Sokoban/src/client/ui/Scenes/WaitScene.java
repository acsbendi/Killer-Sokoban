package client.ui.Scenes;

import client.ui.GUI;
import client.ui.Scenes.SokobanScene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.*;

/**
 * A várakozó képernyőt reprezentáló osztály
 */
public class WaitScene extends SokobanScene {

    /**
     * Default constructor
     */
    public WaitScene() {
    }

    /**
     * Kilépés gomb
     */
    private Button leaveButton;
    /**Létrehoz egy WaitScenet és beállítja a kezdőértékeket
     *
     * @param window - erre a Stage-re hozza létre
     * @param gui - a felhasználói felület amihez tartozik
     *
     *@return a WaitScenet egy megfelelően alaphelyzetbe állított pldánya
     * */
    public static WaitScene Create(Stage window, GUI gui) throws Exception {
        return (WaitScene)SokobanScene.Create("/waitScene.fxml",window,gui);
    }

    // private user event handlers
    /**Kilépés gomb eseménykezelője.
     * Meghívja a felhaszálói felület megfelelő függvényét
     * */
    @FXML
    private void leaveButtonPressed() {
        System.out.println("left!!!!");
        gui.WaitScene_leaveButtonPressed();
    }

    // no network event handlers
}