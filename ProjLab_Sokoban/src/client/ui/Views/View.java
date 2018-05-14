package client.ui.Views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * Abstract base class for the view (graphical representation) of the individual game objects.
 * Game objects can be fields, liquids and placeholders.
 */
public abstract class View {


    /**
     * Abstract getter for the image (view) of a game object.
     * @return The image of a game object.
     */
    protected abstract Image GetImage();

    /**
     * Puts the image of the associated game object onto the StackPane.
     * @param stackPane A container for all the images to be shown on a grid square.
     */
    public void Process(StackPane stackPane ){
        ImageView imageView = new ImageView(GetImage());
        imageView.setFitHeight(stackPane.getPrefHeight());
        imageView.setFitWidth(stackPane.getPrefWidth());

        stackPane.getChildren().add(imageView);
    }

}