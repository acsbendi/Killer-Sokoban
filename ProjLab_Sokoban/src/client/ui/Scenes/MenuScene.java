package client.ui.Scenes;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Class responsible for the effect shown after loading menus.
 */
public abstract class MenuScene extends SokobanScene {
    @FXML
    private ImageView imageView1;

    @FXML
    private ImageView imageView2;

    private void ShowEffect() {
        imageView1.setPreserveRatio(false);
        imageView2.setPreserveRatio(false);

        Rectangle clip1 = new Rectangle(
                imageView1.getFitWidth(), imageView1.getFitHeight()
        );

        Rectangle clip2 = new Rectangle(
                imageView2.getFitWidth(), imageView2.getFitHeight()
        );

        imageView1.setClip(clip1);
        imageView2.setClip(clip2);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(clip1.widthProperty(), 200),
                        new KeyValue(clip2.widthProperty(), 200)),
                new KeyFrame(Duration.millis(200),
                        new KeyValue(clip1.widthProperty(), 200),
                        new KeyValue(clip2.widthProperty(), 200)),
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(clip1.widthProperty(), 0),
                        new KeyValue(clip2.widthProperty(), 0))
        );

        timeline.play();
    }

    @Override
    public void Load() {
        super.Load();
        ShowEffect();
    }
}
