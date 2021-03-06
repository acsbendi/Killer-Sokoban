package client.ui.Scenes;

import client.ui.Camera;
import client.ui.GUI;
import client.ui.WarehouseView;
import common.util.Direction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.*;

/**
 * 
 */
public class GameScene extends SokobanScene {
    private static final int MIN_WIDTH = 1130;
    private static final int MIN_HEIGHT = 423;

    public void setWarehouseView(WarehouseView warehouseView) {
        this.warehouseView = warehouseView;
        camera.SetWarehouseView(warehouseView);
    }

    public void setPoints(ArrayList<Integer> points, int workerNumber) {
        this.points = points;
        this.workerNumber = workerNumber;
    }

    private WarehouseView warehouseView;
    private Camera camera;
    private ArrayList<Integer> points;
    private int workerNumber;

    /**
     * 
     */
    @FXML
    private GridPane gamePane;

    /**
     * 
     */
    @FXML
    private Label pointsLabel;

    @Override
    public void Load(){
        super.Load();
        UpdateScreen();
    }

    @FXML
    private void OnKeyPressed(KeyEvent event){
        System.out.println(event.getCode() + "pressed ");
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
                gui.UpdateScreen();
                break;
            case LEFT:
                camera.Move(Direction.Left);
                gui.UpdateScreen();
                break;
            case DOWN:
                camera.Move(Direction.Down);
                gui.UpdateScreen();
                break;
            case RIGHT:
                camera.Move(Direction.Right);
                gui.UpdateScreen();
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

        String msg = "Points: ";
        for(int i = 0; i < points.size(); i++) {
            if (i != workerNumber) {
                msg = msg + "" + (i+1) + ": " + points.get(i) + "; ";
            }
            else if (i == workerNumber) {
                msg = msg + "TE: " + points.get(i) + "; ";
            }
        }
        pointsLabel.setText(msg);
    }

    public static GameScene Create(Stage window, GUI gui) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(GameScene.class.getResource("/gameScene.fxml"));
        Parent root = fxmlLoader.load();
        GameScene gameScene = fxmlLoader.getController();

        gameScene.window = window;
        gameScene.gui = gui;
        gameScene.camera = new Camera((int)gameScene.gamePane.getMinWidth(),(int)gameScene.gamePane.getMinHeight());
        gameScene.scene = new Scene(root,MIN_WIDTH,MIN_HEIGHT);
        root.requestFocus();

        return gameScene;
    }

}