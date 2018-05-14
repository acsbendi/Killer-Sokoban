package client.ui.Scenes;

import client.ui.GUI;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.*;

/**
 * Ősosztály a képernyők összefogásához
 */
public abstract class SokobanScene {
    /**Képernyő szélesságe
     * */
    protected static final int MIN_WIDTH = 1000;
    /**képernyő magassága
     * */
    protected static final int MIN_HEIGHT = 500;
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

}