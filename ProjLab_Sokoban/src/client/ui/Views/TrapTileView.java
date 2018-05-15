package client.ui.Views;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import java.util.*;

/**
 * Class responsible for the graphical representation of a trap tile.
 */
public class TrapTileView extends View {

    /** Is the trap tile represented by this view open? */
    private final boolean open;

    /**
     * Constructor, can construct a view of either a closed or an open trap tile.
     * @param open Is the represented trap tile open?
     */
    public TrapTileView(boolean open) {
        this.open = open;
    }

    /**
     * The image of an open trap tile.
     * Static member, so the image resource will be loaded only once.
     */
    private static Image openImage = new Image("trapdoor-open.png");

    /**
     * The image of a closed trap tile.
     * Static member, so the image resource will be loaded only once.
     */
    private static Image closedImage  = new Image("trapdoor-closed.png");

    /**
     * Getter for the static image showing a trap tile.
     * @return Image of a trap tile, can be open or closed.
     */
    @Override
    protected Image GetImage(){
        return open ? openImage : closedImage;
    }

}