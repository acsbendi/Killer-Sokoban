package client.ui.Views;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import java.util.*;

/**
 * 
 */
public class LeverTileView extends View {

    /**
     * Default constructor
     */
    public LeverTileView() {
    }

    /**
     * 
     */
    private static Image image = new Image("lever.png");

    @Override
    protected Image GetImage(){
        return image;
    }

}