package client.ui;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

import java.util.*;

/**
 * 
 */
public class Camera {

    private int width;
    private int height;
    private int gridSize = 50;

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
    public StackPane[][] GetView() {
        int horizontalCount = width/gridSize;
        int verticalCount = height/gridSize;

        GridSquare[][] gridSquares = warehouseView.GetGrid(new Rectangle(horizontalCount,verticalCount));

        StackPane[][] stackPanes = new StackPane[verticalCount][horizontalCount];

        for (int i = 0; i < verticalCount; i++) {
            for (int j = 0; j < horizontalCount; j++) {
                if(gridSquares[i][j] != null)
                    stackPanes[i][j] = gridSquares[i][j].CreateImage();
            }
        }

        return stackPanes;
    }

    //TODO set the position of the camera
}