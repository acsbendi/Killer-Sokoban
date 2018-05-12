package client.ui.Views;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import java.util.*;

/**
 * 
 */
public class HoleView extends View {

    /**
     * Default constructor
     */
    public HoleView() {
    }

    /**
     * 
     */
    private static Image image = new Image("hole.png");


    @Override
    protected Image GetImage(){
        return image;
    }

}