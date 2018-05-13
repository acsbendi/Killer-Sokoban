package client.ui;

import common.util.Direction;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.util.*;

/**
 * 
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
        position.x = 0;
        position.y = 0;
        this.width = width;
        this.height = height;
    }

    public void SetWarehouseView(WarehouseView warehouseView) {
        this.warehouseView = warehouseView;
    }

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
            case Right:
                if(position.x > 0)
                    position.x--;
                break;
            case Left:
                if(position.x < warehouseView.GetWidth() - 1)
                    position.x++;
                break;
        }
    }


    /**
     * @return
     */
    public StackPane[][] GetView() {
        int horizontalCount = width/gridSize;
        int verticalCount = height/gridSize;

        GridSquare[][] gridSquares = warehouseView.GetGrid(new Rectangle(position.x,position.y,horizontalCount,verticalCount));

        StackPane[][] stackPanes = new StackPane[verticalCount][horizontalCount];

        for (int i = 0; i < verticalCount; i++) {
            for (int j = 0; j < horizontalCount; j++) {
                if(gridSquares[i][j] != null)
                    stackPanes[i][j] = gridSquares[i][j].CreateImage();
            }
        }

        return stackPanes;
    }
}