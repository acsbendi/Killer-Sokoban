package client.ui.Views;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import java.util.*;

/**
 * Class responsible for the graphical representation of a worker.
 */
public class WorkerView extends View {

    /** Does the worker represented by this view belong to the local user? */
    private final boolean local;

    /**
     * Default constructor
     */
    public WorkerView(boolean local) {
        this.local = local;
    }

    /**
     * The image of a worker belonging to the local user.
     * Static member, so the image resource will be loaded only once.
     */
    private static Image playerImage = new Image("player.png");

    /**
     * The image of any worker not belonging to the local user.
     * Static member, so the image resource will be loaded only once.
     */
    private static Image workerImage = new Image("worker.png");


    /**
     * Getter for the static image showing a worker.
     * @return Image of a worker, can be local or not.
     */
    @Override
    protected Image GetImage(){
        return local ? playerImage : workerImage;
    }

}