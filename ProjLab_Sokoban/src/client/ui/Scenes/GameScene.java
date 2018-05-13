package client.ui.Scenes;

import client.ui.Scenes.SokobanScene;
import client.ui.WarehouseView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.*;

/**
 * 
 */
public class GameScene extends SokobanScene {

    public void setWarehouseView(WarehouseView warehouseView) {
        this.warehouseView = warehouseView;
    }

    private WarehouseView warehouseView;


    /**
     * Default constructor
     */
    public GameScene() {
    }

    /**
     * 
     */
    private GridPane gamePane;

    /**
     * 
     */
    private Button leaveButton;

    /**
     * 
     */
    private Label pointsLabel;




    /**
     * 
     */
    public void UpdateScreen() {
        // TODO implement here
    }

}