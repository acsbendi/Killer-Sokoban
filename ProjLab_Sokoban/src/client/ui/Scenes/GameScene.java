//TEST COMMIT - this text is meaningless
package client.ui.Scenes;

import client.ui.Camera;
import client.ui.GUI;
import client.ui.GridSquare;
import client.ui.Scenes.SokobanScene;
import client.ui.WarehouseView;
import common.util.Direction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
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
        System.out.println("started  !!!!");
        window.setScene(scene);
        UpdateScreen();
    }

    @FXML
    private void OnKeyPressed(KeyEvent event){
        switch (event.getCode()){
            case W:
                gui.GameScene_MoveWorker(Direction.Up);
                break;
            case A:
                gui.GameScene_MoveWorker(Direction.Left);
                break;
            case S:
                gui.GameScene_MoveWorker(Direction.Down);
                break;
            case D:
                gui.GameScene_MoveWorker(Direction.Right);
                break;
            case UP:
                camera.Move(Direction.Up);
                UpdateScreen();
                break;
            case LEFT:
                camera.Move(Direction.Left);
                UpdateScreen();
                break;
            case DOWN:
                camera.Move(Direction.Down);
                UpdateScreen();
                break;
            case RIGHT:
                camera.Move(Direction.Right);
                UpdateScreen();
                break;
            case E:
                gui.GameScene_PlaceHoney();
                break;
            case Q:
                gui.GameScene_PlaceOil();
                break;
        }
    }

    @FXML
    private void Leave(){

    }


    /**
     * 
     */
    public void UpdateScreen() {
        gamePane.getChildren().clear();

        StackPane[][] panesToShow = camera.GetView();

        for (int i = 0; i < panesToShow.length; i++) {
            for (int j = 0; j < panesToShow[i].length; j++) {
                if(panesToShow[i][j] != null)
                    gamePane.add(panesToShow[i][j],j,i);
            }
        }
    }

    public static GameScene Create(Stage window, GUI gui) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(GameScene.class.getResource("/gameScene.fxml"));
        Parent root = fxmlLoader.load();
        GameScene gameScene = fxmlLoader.getController();

        gameScene.window = window;
        gameScene.gui = gui;
        gameScene.camera = new Camera((int)gameScene.gamePane.getMinWidth(),(int)gameScene.gamePane.getMinHeight());
        gameScene.scene = new Scene(root,MIN_WIDTH,MIN_HEIGHT);

        return gameScene;
    }

}