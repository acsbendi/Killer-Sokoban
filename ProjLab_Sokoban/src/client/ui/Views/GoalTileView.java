package client.ui.Views;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

import java.util.*;

/**
 * 
 */
public class GoalTileView extends View {

    /**
     * Default constructor
     */
    public GoalTileView() {
    }

    /**
     * 
     */
    private static Image image = new Image("goal.png");


    @Override
    protected Image GetImage(){
        return image;
    }

}