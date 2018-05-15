package client.ui.Views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import java.util.*;

/**
 * Class responsible for the graphical representation of a honey puddle.
 */
public class HoneyView extends View {

    /**
     * Default constructor
     */
    public HoneyView() {
    }

    /**
     * The image of a honey puddle.
     * Static member, so the image resource will be loaded only once.
     */
    private static Image image = new Image("honey.png");

    /**
     * Puts the image of a honey puddle onto the StackPane with a degree of opacity.
     * @param stackPane A container for all the images to be shown on a grid square.
     */
    @Override
    public void Process(StackPane stackPane ) {
        ImageView imageView = new ImageView(GetImage());
        imageView.setOpacity(0.54);
        imageView.setFitHeight(stackPane.getPrefHeight());
        imageView.setFitWidth(stackPane.getPrefWidth());

        stackPane.getChildren().add(imageView);
    }

    /**
     * Getter for the static image showing a honey puddle.
     * @return Image of a honey puddle.
     */
    @Override
    protected Image GetImage(){
        return image;
    }

}