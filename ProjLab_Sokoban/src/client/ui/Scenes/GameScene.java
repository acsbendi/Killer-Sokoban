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
 * A játék képernyőt reprezentáló osztály
 */
public class GameScene extends SokobanScene {

    /**A raktár ami a játékban szerepel
     * */
    private WarehouseView warehouseView;
    /**Beállítja, hogy melyik WarehouseView-t kell kezelnie
     * */
    public void setWarehouseView(WarehouseView warehouseView) {
        this.warehouseView = warehouseView;
        camera.SetWarehouseView(warehouseView);
    }
    /**A nézetet előállító kamera
     * */
    private Camera camera;

    /**
     * A grafikus felületen megjelenő négyzetrács
     */
    @FXML
    private GridPane gamePane;

    /**
     * A pontokat kiíró címke
     */
    private Label pointsLabel;
    /**A Scene betöltésekor beállítja a megjelenítendő objektumok paramétereit
     * */
    @Override
    public void Load(){
        System.out.println("started  !!!!");
        window.setScene(scene);
        UpdateScreen();
    }
    /**A billentyűzet eseménykezelő függvénye
     * */
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
    /**Kilépés a játékból
     * */
    @FXML
    private void Leave(){

    }


    /**
     * Frissíti a képet a játék aktuális állapotának megfelelően
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
    /**Létrehoz egy GameScenet és beállítja a kezdőértékeket
     *
     * @param window - erre a Stage-re hozza létre
     * @param gui - a felhasználói felület amihez tartozik
     *
     *@return a GameScenet egy megfelelően alaphelyzetbe állított pldánya
     * */
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