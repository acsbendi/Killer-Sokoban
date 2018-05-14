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
public class ResultsScene extends SokobanScene {

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
        FXMLLoader fxmlLoader = new FXMLLoader(GameScene.class.getResource("/resultscene.fxml"));
        Parent root = fxmlLoader.load();
        ResultsScene resultsScene = fxmlLoader.getController();

        resultsScene.window = window;
        resultsScene.gui = gui;
        resultsScene.scene = new Scene(root,MIN_WIDTH,MIN_HEIGHT);

        return resultsScene;
    }
    /**A Scene betöltésekor beállítja a megjelenítendő objektumok paramétereit
     * */
    @Override
    public void Load() {
        message.setText("");
        ownResults.setSelected(true);
        window.setScene(scene);
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