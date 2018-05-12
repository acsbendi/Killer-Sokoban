package client.ui.Views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import javax.imageio.ImageIO;
import java.util.*;

/**
 * 
 */
public class WallView extends View {

    /**
     * Default constructor
     */
    public WallView() {
    }

    /**
     *
     */
    private static Image image = new Image("wall.png");

    @Override
    protected Image GetImage(){
        return image;
    }
}