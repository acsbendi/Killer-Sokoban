package client.ui.Views;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import java.util.*;

/**
 * Class responsible for the graphical representation of a tile.
 */
public class TileView extends View {

    /**
     * Default constructor
     */
    public TileView() {
    }

    /**
     * The image of a tile.
     * Static member, so the image resource will be loaded only once.
     */
    private static Image image = new Image("tile.png");

    /**
     * Getter for the static image showing a tile.
     * @return Image of a tile.
     */
    @Override
    protected Image GetImage(){
        return image;
    }
}