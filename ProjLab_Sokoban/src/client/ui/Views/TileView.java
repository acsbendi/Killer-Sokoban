package client.ui.Views;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import java.util.*;

/**
 * 
 */
public class TileView extends View {

    /**
     * Default constructor
     */
    public TileView() {
    }

    /**
     * 
     */
    private static Image image = new Image("tile.png");

    @Override
    protected Image GetImage(){
        return image;
    }
}