package client.ui.Scenes;

import client.ui.GUI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Az eredmény képernyőt remrezentáló osztály
 */
public class ResultsScene extends MenuScene {

    /**
     * Default constructor
     */
    public ResultsScene() {
    }

    /**
     *Vissza gomb
     */
    private Button backButton;

    /**
     * Címke, amely megjeleníti a program üzeneteit a felhasználó számára
     */
    @FXML
    private Label message;

    /**
     *CheckBox, hogy a képernyő a saját eredményt jelenítse-e meg
     */
    @FXML
    private CheckBox ownResults;
    /**Megjeleníti a paraméterben átadott String-et eredményként
     * */
    public void ShowResults(String msg) {
        message.setText(msg);
    }

    /**Létrehoz egy ResultScenet és beállítja a kezdőértékeket
     *
     * @param window - erre a Stage-re hozza létre
     * @param gui - a felhasználói felület amihez tartozik
     *
     *@return a ResultScenet egy megfelelően alaphelyzetbe állított pldánya
     * */
    public static ResultsScene Create(Stage window, GUI gui) throws Exception {
        return (ResultsScene)SokobanScene.Create("/resultscene.fxml",window,gui);
    }

    /**A Scene betöltésekor beállítja a megjelenítendő objektumok paramétereit
     * */
    @Override
    public void Load() {
        message.setText("");
        ownResults.setSelected(true);
        super.Load();
    }
    /**A vissza gomb megnyomásának eseménykezelő függvénye.
     * Meghívja a felhaszálói felület megfelelő függvényét
     * */
    @FXML
    public void BackButtonPressed() {
        gui.ResultsScene_backButtonPressed();
    }
    /**A saját eredmény jelölő eseménykezelője.
     * Meghívja a felhaszálói felület megfelelő függvényét
     * */
    @FXML
    public void OwnResultsChecked() {
        gui.ResultsScene_ownResultClicked(ownResults.isSelected());
    }

}