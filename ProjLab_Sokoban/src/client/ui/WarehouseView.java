package client.ui;

/**
 * Class responsible for storing all GridCells in the currently played warehouse/map.
 */
public class WarehouseView {

    /**
     * Default constructor
     */
    public WarehouseView(GridCell[][] gridCells) {
        this.gridCells = gridCells;
    }

    /**
     *
     */
    private GridCell[][] gridCells;


    public GridCell[][] GetGrid(int startX, int startY, int width, int height) {
        GridCell[][] result = new GridCell[height][width];

        for (int i =  0; i < height; i++) {
            if(gridCells.length  > startY + i) {
                int to = startX + width < gridCells[startY + i].length ?
                        startX + width : gridCells[startY + i].length;
                for (int j = startX, k = 0; j < to; j++, k++) {
                    result[i][k] = gridCells[startY + i][j];
                }
            }else
                result[i] = null;
        }

        return result;
    }

    public int GetWidth(){
        return gridCells[0].length;
    }

    public int GetHeight(){
        return gridCells.length;
    }

}