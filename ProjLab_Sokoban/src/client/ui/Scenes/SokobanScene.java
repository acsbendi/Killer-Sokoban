package client.ui.Scenes;

import client.ui.GUI;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.*;

/**
 * 
 */
public abstract class SokobanScene {

    protected Stage window;
    protected Scene scene;

    /**
     * 
     */
    protected GUI gui;


    /**
     * 
     */
    public void Load() {
        window.setScene(scene);
        // TODO implement here
    }

    /**
     * 
     */
    public void Hide() {
        // TODO implement here
    }

}