package client.ui.Views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import java.util.*;

/**
 * 
 */
public class HoneyView extends View {

    /**
     * Default constructor
     */
    public HoneyView() {
    }

    /**
     * 
     */
    private static Image image = new Image("honey.png");

    /**
     * @param stackPane
     */
    @Override
    public void Process(StackPane stackPane ) {
        ImageView imageView = new ImageView(GetImage());
        imageView.setFitHeight(stackPane.getHeight());
        imageView.setFitWidth(stackPane.getWidth());
        imageView.setOpacity(0.54);

        stackPane.getChildren().add(imageView);
    }

    @Override
    protected Image GetImage(){
        return image;
    }

}