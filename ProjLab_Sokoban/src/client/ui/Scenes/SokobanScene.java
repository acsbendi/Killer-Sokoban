package client.ui.Scenes;

import client.ui.GUI;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

/**
 * Ősosztály a képernyők összefogásához
 */
public abstract class SokobanScene {
    /**Képernyő szélesságe
     * */
    protected static final int MIN_WIDTH = 600;
    /**képernyő magassága
     * */
    protected static final int MIN_HEIGHT = 400;
    /**Ehhez az ablakhoz tartozik a képernyő
     * */
    protected Stage window;
    /**A képernyő kinézetét tárolja
     * */
    protected Scene scene;

    /**
     * A grafikus felület amivel összeköttetésben van
     */
    protected GUI gui;


    /**
     * Betölti az adott képernyőt
     */
    public void Load() {
        window.setScene(scene);
    }

    /**
     * Elrejti az adott képernyőt
     */
    public void Hide() {}

    protected static SokobanScene Create(String name, Stage window, GUI gui) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(SokobanScene.class.getResource(name));
        Parent root = fxmlLoader.load();
        SokobanScene scene = fxmlLoader.getController();

        scene.window = window;
        scene.gui = gui;
        scene.scene = new Scene(root,MIN_WIDTH,MIN_HEIGHT);
        scene.scene.getStylesheets().add("/styles.css");

        return scene;
    }

}