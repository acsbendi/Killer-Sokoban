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
        System.out.println("whw grid injected");
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
        System.out.println("get grid ");

        for (int i =  0; i < (int)rect.getHeight(); i++) {
            if(gridSquares[(int)rect.getY() + i] != null) {
                int to = (int) rect.getX() + (int) rect.getWidth() < gridSquares[(int) rect.getY() + i].length ?
                        (int) rect.getX() + (int) rect.getWidth() : gridSquares[(int) rect.getY() + i].length;
                result[i] = Arrays.copyOfRange(gridSquares[(int) rect.getY() + i], (int) rect.getX(), to);
                System.out.println("copying " + (int) rect.getY() + i + " from " + (int) rect.getX() + (int) rect.getX() + (int) rect.getWidth());
            }
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