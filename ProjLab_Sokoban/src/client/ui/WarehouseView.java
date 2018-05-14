package client.ui;

import javafx.scene.shape.Rectangle;

import java.util.*;

/**
 * Class responsible for storing all GridSquare in the currently played warehouse/map.
 */
public class WarehouseView {

    /**
     * Default constructor
     */
    public WarehouseView(GridSquare[][] gridSquares) {
        this.gridSquares = gridSquares;
    }

    /**
     *
     */
    private GridSquare[][] gridSquares;


    public GridSquare[][] GetGrid(int startX, int startY, int width, int height) {
        GridSquare[][] result = new GridSquare[height][width];

        for (int i =  0; i < height; i++) {
            if(gridSquares.length  > startY + i) {
                int to = startX + width < gridSquares[startY + i].length ?
                        startX + width : gridSquares[startY + i].length;
                for (int j = startX, k = 0; j < to; j++, k++) {
                    result[i][k] = gridSquares[startY + i][j];
                }
            }else
                result[i] = null;
        }

        return result;
    }

    public int GetWidth(){
        return gridSquares[0].length;
    }

    public int GetHeight(){
        return gridSquares.length;
    }

}