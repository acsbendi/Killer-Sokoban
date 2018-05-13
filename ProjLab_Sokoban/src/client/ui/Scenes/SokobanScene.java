package client.ui.Scenes;

import client.ui.GUI;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.*;

/**
 * 
 */
public abstract class SokobanScene {

    protected static final int MIN_WIDTH = 1000;
    protected static final int MIN_HEIGHT = 500;

    protected Stage window;
    protected Scene scene;

    /**
     * 
     */
    protected GUI gui;


    /**
     * default Load method
     */
    public void Load() {
        window.setScene(scene);
    }

    /**
     * 
     */
    public void Hide() {}

}