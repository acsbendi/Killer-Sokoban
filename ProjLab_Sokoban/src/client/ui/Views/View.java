package client.ui.Views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * 
 */
public abstract class View {


    protected abstract Image GetImage();

    /**
     * @param stackPane
     */
    void Process(StackPane stackPane ){
        ImageView imageView = new ImageView(GetImage());
        imageView.setFitHeight(stackPane.getHeight());
        imageView.setFitWidth(stackPane.getWidth());

        stackPane.getChildren().add(imageView);
    }

}