package client.ui.Scenes;

import client.ui.Camera;
import client.ui.GUI;
import client.ui.GridSquare;
import client.ui.Scenes.SokobanScene;
import client.ui.WarehouseView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.*;

/**
 * 
 */
public class GameScene extends SokobanScene {

    private static final int MIN_WIDTH = 500;
    private static final int MIN_HEIGHT = 500;

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
        StackPane[][] panesToShow = camera.GetView();

        for (int i = 0; i < panesToShow.length; i++) {
            for (int j = 0; j < panesToShow[i].length; j++) {
                if(panesToShow[i][j] != null)
                    gamePane.add(panesToShow[i][j],i,j);
            }
        }
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