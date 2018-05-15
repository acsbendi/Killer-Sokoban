package client.ui.Views;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import java.util.*;

/**
 * Class responsible for the graphical representation of a lever tile.
 */
public class LeverTileView extends View {

    /**
     * Default constructor
     */
    public LeverTileView() {
    }

    /**
     * The image of a lever tile.
     * Static member, so the image resource will be loaded only once.
     */
    private static Image image = new Image("lever.png");

    /**
     * Getter for the static image showing a lever tile.
     * @return Image of a lever tile.
     */
    @Override
    protected Image GetImage(){
        return image;
    }

}