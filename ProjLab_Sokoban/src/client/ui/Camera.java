package client.ui;

import javafx.scene.layout.GridPane;

import java.util.*;

/**
 * 
 */
public class Camera {

    /**
     * Default constructor
     */
    public Camera() {
    }

    public void SetWarehouseView(WarehouseView warehouseView) {
        this.warehouseView = warehouseView;
    }

    private WarehouseView warehouseView;


    /**
     * @return
     */
    public GridPane GetView() {
        // TODO implement here
        return null;
    }

    //TODO set the position of the camera
}