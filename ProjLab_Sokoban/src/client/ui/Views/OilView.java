package client.ui.Views;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import java.util.*;

/**
 * 
 */
public class OilView extends View {

    /**
     * Default constructor
     */
    public OilView() {
    }

    /**
     * 
     */
    private static Image image = new Image("oil.png");

    @Override
    protected Image GetImage(){
        return image;
    }

}