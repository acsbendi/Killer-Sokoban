package client.ui.Views;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

import java.util.*;

/**
 * Class responsible for the graphical representation of a goal tile.
 */
public class GoalTileView extends View {

    /**
     * Default constructor
     */
    public GoalTileView() {
    }

    /**
     * The image of a goal tile.
     * Static member, so the image resource will be loaded only once.
     */
    private static Image image = new Image("goal.png");


    /**
     * Getter for the static image showing a goal tile.
     * @return Image of a goal tile.
     */
    @Override
    protected Image GetImage(){
        return image;
    }

}