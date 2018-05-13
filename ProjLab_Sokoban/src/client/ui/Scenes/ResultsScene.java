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
 *
 */
public class ResultsScene extends SokobanScene {

    /**
     * Default constructor
     */
    public ResultsScene() {
    }

    /**
     *
     */
    private Button backButton;

    /**
     *
     */
    @FXML
    private Label message;

    /**
     *
     */
    @FXML
    private CheckBox ownResults;

    public void ShowResults(String msg) {
        message.setText(msg);
    }

    public static ResultsScene Create(Stage window, GUI gui) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(GameScene.class.getResource("resultsScene.fxml"));
        Parent root = fxmlLoader.load();
        ResultsScene resultsScene = fxmlLoader.getController();

        resultsScene.window = window;
        resultsScene.gui = gui;
        resultsScene.scene = new Scene(root,MIN_WIDTH,MIN_HEIGHT);

        return resultsScene;
    }

    @Override
    public void Load() {
        message.setText("");
        ownResults.setSelected(true);
    }

    @FXML
    public void BackButtonPressed() {
        gui.ResultsScene_backButtonPressed();
    }

    @FXML
    public void OwnResultsChecked() {
        gui.ResultsScene_ownResultClicked(ownResults.isSelected());
    }

}