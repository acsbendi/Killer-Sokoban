package client.ui.Views;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

import java.util.*;

/**
 * 
 */
public class BoxView extends View {

    /**
     * Default constructor
     */
    public BoxView() {
    }

    /**
     * 
     */
    private static Image image = new Image("box.png");

    @Override
    protected Image GetImage(){
        return image;
    }

}