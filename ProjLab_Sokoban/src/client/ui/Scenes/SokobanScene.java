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
    public abstract void Load();

    /**
     * 
     */
    public abstract void Hide();

}