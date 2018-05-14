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
 *A főképernyőt reprezentáló osztály
 */
public class MainScene extends SokobanScene {

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
    public static MainScene Create(Stage window, GUI gui) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(GameScene.class.getResource("/mainScene.fxml"));
        Parent root = fxmlLoader.load();
        MainScene mainScene = fxmlLoader.getController();

        mainScene.window = window;
        mainScene.gui = gui;
        mainScene.scene = new Scene(root,MIN_WIDTH,MIN_HEIGHT);

        return mainScene;
    }
    /**A Scene betöltésekor beállítja a megjelenítendő objektumok paramétereit
     * */
    public void Load() {
        window.setScene(scene);
        roomSizeText.setText("");
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
            System.err.println("wrong room sized given");
        }

    }

}