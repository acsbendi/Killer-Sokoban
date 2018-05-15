package client.ui.Views;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import java.util.*;

/**
 * Class responsible for the graphical representation of a hole.
 */
public class HoleView extends View {

    /**
     * Default constructor
     */
    public HoleView() {
    }

    /**
     * The image of a hole.
     * Static member, so the image resource will be loaded only once.
     */
    private static Image image = new Image("hole.png");

    /**
     * Getter for the static image showing a hole.
     * @return Image of a hole.
     */
    @Override
    protected Image GetImage(){
        return image;
    }

}