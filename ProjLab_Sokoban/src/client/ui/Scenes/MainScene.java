package client.ui.Scenes;

import client.ui.GUI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

/**
 *A főképernyőt reprezentáló osztály
 */
public class MainScene extends MenuScene {

    /**
     * Default constructor
     */
    public MainScene() {
    }

    /**
     *Belépés gomb
     */
    private Button enterButton;

    /**
     *Szövegdoboz a játék méretének megadásához
     */
    @FXML
    private TextField roomSizeText;

    /**
     *kijelentkező gomb
     */
    private Button logoutButton;

    /**
     *Eredmények gomb
     */
    private Button resultButton;



    /**Létrehoz egy MainScenet és beállítja a kezdőértékeket
     *
     * @param window - erre a Stage-re hozza létre
     * @param gui - a felhasználói felület amihez tartozik
     *
     *@return a MainScenet  egy megfelelően alaphelyzetbe állított pldánya
     * */
    // STATIC CREATE
    public static MainScene Create(Stage window, GUI gui) throws IOException {
        return (MainScene)SokobanScene.Create("/mainscene.fxml",window,gui);
    }
    /**A Scene betöltésekor beállítja a megjelenítendő objektumok paramétereit
     * */
    public void Load() {
        roomSizeText.setText("");
        super.Load();
    }
    /**Az eredmények gomb eseménykezelő függvénye.
     * Meghívja a felhasználói felület megfelelő függvényét.
     * */
    @FXML
    public void ResultButtonPressed() {
        gui.MainScene_resultButtonPressed();
    }
    /**A kijelentkezés gomb eseménykezelő függvénye.
     * Meghívja a felhasználói felület megfelelő függvényét.
     * */
    @FXML
    public void LogoutButtonPressed() {
        gui.MainScene_logoutButtonPressed();
    }
    /**A belépés gomb eseménykezelő függvénye.
     * Ellenőrzi, hogy a megadott értékek érvényesek-e és meghívja a felhasználói felület megfelelő függvényét.
     * */
    @FXML
    public void EnterButtonPressed() {
        try {
            int roomSize = Integer.parseInt(roomSizeText.getText());
            if(roomSize >= 2 && roomSize <=4)
                gui.MainScene_enterButtonPressed(roomSize);
        } catch (NumberFormatException e){

        }

    }

}