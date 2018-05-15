package client.ui.Views;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import java.util.*;

/**
 * Class responsible for the graphical representation of an oil puddle.
 */
public class OilView extends View {

    /**
     * Default constructor
     */
    public OilView() {
    }

    /**
     * The image of an oil puddle.
     * Static member, so the image resource will be loaded only once.
     */
    private static Image image = new Image("oil.png");

    /**
     * Getter for the static image showing an oil puddle.
     * @return Image of an oil puddle.
     */
    @Override
    protected Image GetImage(){
        return image;
    }

}