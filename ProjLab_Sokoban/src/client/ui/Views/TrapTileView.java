package client.ui.Views;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import java.util.*;

/**
 * 
 */
public class TrapTileView extends View {

    private boolean open;

    /**
     * Default constructor
     */
    public TrapTileView(boolean open) {
        this.open = open;
    }

    /**
     * 
     */
    private static Image openImage = new Image("trapdoor-open.png");

    /**
     * 
     */
    private static Image closedImage  = new Image("trapdoor-closed.png");


    @Override
    protected Image GetImage(){
        return open ? openImage : closedImage;
    }

}