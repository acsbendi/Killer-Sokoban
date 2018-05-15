package client.ui.Views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import javax.imageio.ImageIO;
import java.util.*;

/**
 * Class responsible for the graphical representation of a wall.
 */
public class WallView extends View {

    /**
     * Default constructor
     */
    public WallView() {
    }

    /**
     * The image of a wall.
     * Static member, so the image resource will be loaded only once.
     */
    private static Image image = new Image("wall.png");

    /**
     * Getter for the static image showing a wall.
     * @return Image of a wall.
     */
    @Override
    protected Image GetImage(){
        return image;
    }
}