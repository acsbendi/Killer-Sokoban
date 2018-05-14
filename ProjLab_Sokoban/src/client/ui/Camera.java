package client.ui;

import common.util.Direction;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.util.*;

/**
 * Class responsible for looking at the currently played warehouse, that is, selecting a part of it to be shown.
 * It is useful for larger warehouses, when the whole cannot fit on the screen.
 */
public class Camera {

    private int width;
    private int height;
    private int gridSize = 50;
    private Point position;
    private WarehouseView warehouseView;

    /**
     * Default constructor
     */
    public Camera(int width, int height) {
        //TODO set position to show local player's worker
        position = new Point(0,0);
        this.width = width;
        this.height = height;
    }

    /**
     * Sets the WarehouseView that the camera will look at.
     * @param warehouseView The WarehouseView object to look at.
     */
    public void SetWarehouseView(WarehouseView warehouseView) {
        this.warehouseView = warehouseView;
    }

    /**
     * Moves the camera is the specified position.
     * It cannot move past the borders of the warehouse.
     * @param direction The direction of the move.
     */
    public void Move(Direction direction){
        switch (direction){
            case Up:
                if(position.y > 0)
                    position.y--;
                break;
            case Down:
                if(position.y < warehouseView.GetHeight() - 1)
                    position.y++;
                break;
            case Left:
                if(position.x > 0)
                    position.x--;
                break;
            case Right:
                if(position.x < warehouseView.GetWidth() - 1)
                    position.x++;
                break;
        }
    }

    /**
     * Returns the currently visible field's images, wrapped in StackPanes.
     * The StackPanes included in a 2D array for easier position calculation.
     * @return The array containing the images to be shown.
     */
    public StackPane[][] GetView() {
        int horizontalCount = 27;//width/gridSize;
        int verticalCount = 11; //height/gridSize;

        GridSquare[][] gridSquares = warehouseView.GetGrid(position.x,position.y,horizontalCount,verticalCount);

        StackPane[][] stackPanes = new StackPane[verticalCount][horizontalCount];

        for (int i = 0; i < verticalCount; i++) {
            for (int j = 0; j < horizontalCount; j++) {
                if(gridSquares[i] != null && gridSquares[i][j] != null)
                    stackPanes[i][j] = gridSquares[i][j].CreateImage();
            }
        }

        return stackPanes;
    }
}