package client.ui.Views;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import java.util.*;

/**
 * 
 */
public class WorkerView extends View {

    private boolean local;

    /**
     * Default constructor
     */
    public WorkerView(boolean local) {
        this.local = local;
    }

    /**
     * 
     */
    private static Image playerImage = new Image("player.png");

    /**
     * 
     */
    private static Image workerImage = new Image("worker.png");


    @Override
    protected Image GetImage(){
        return local ? playerImage : workerImage;
    }

}