package client.ui.Scenes;

import client.ui.Camera;
import client.ui.GUI;
import client.ui.Scenes.SokobanScene;
import client.ui.WarehouseView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.*;

/**
 * 
 */
public class GameScene extends SokobanScene {

    public void setWarehouseView(WarehouseView warehouseView) {
        this.warehouseView = warehouseView;
        camera.SetWarehouseView(warehouseView);
    }

    private WarehouseView warehouseView;
    private Camera camera;

    /**
     * 
     */
    @FXML
    private GridPane gamePane;

    /**
     * 
     */
    private Label pointsLabel;

    @Override
    public void Load(){
        window.setTitle("Sokoban Game");
        window.setScene(scene);
        UpdateScreen();
    }


    /**
     * 
     */
    public void UpdateScreen() {
        // TODO implement here
    }

    public static GameScene Create(Stage window, GUI gui) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(GameScene.class.getResource("gameScene.fxml"));
        Parent root = fxmlLoader.load();
        GameScene gameScene = fxmlLoader.getController();

        gameScene.window = window;
        gameScene.gui = gui;
        gameScene.camera = new Camera();
        gameScene.scene = new Scene(root,MIN_WIDTH,MIN_HEIGHT);

        return gameScene;
    }

}