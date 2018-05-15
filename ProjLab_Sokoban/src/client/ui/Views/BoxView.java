package client.ui.Views;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

import java.util.*;

/**
 * Class responsible for the graphical representation of a box.
 */
public class BoxView extends View {

    /**
     * Default constructor
     */
    public BoxView() {
    }

    /**
     * The image of a box.
     * Static member, so the image resource will be loaded only once.
     */
    private static Image image = new Image("box.png");

    /**
     * Getter for the static image showing a box.
     * @return Image of a box.
     */
    @Override
    protected Image GetImage(){
        return image;
    }

}