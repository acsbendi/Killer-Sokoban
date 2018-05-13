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



    /**
     * @param rect 
     * @return
     */
    public GridSquare[][] GetGrid(Rectangle rect) {
        GridSquare[][] result = new GridSquare[(int)rect.getHeight()][(int)rect.getWidth()];

        for (int i =  0; i < (int)rect.getHeight(); i++) {
            result[i] = Arrays.copyOfRange(gridSquares[(int)rect.getY() + i],(int)rect.getX(),
                    (int)rect.getX() + (int)rect.getWidth() );
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