package client.ui;

import client.ui.Views.*;
import common.model.Field;
import javafx.scene.layout.StackPane;

/**
 * Class responsible for showing the current state of an associated field.
 */
public class GridCell {

    /** The width of the image/StackPane created by GridCell. */
    private static int width = 36;

    /** The height of the image/StackPane created by GridCell. */
    private static int height = 36;

    //TODO width and height dependant on user settings.

    /** The field associated with the GridCell. */
    private final Field field;

    /** StackPane containing the graphical representation of the field.
     * This will be returned from the CreateImage method. */
    private StackPane imagePane;

    /**
     * Constructs a new GridCell associated with the specified field.
     *
     * @param field The associated field.
     */
    public GridCell(Field field) {
        this.field = field;
    }

    /**
     * Creates the image showing the current state of the associated field.
     *
     * @return StackPane containing the graphical representation of the field.
     */
    StackPane CreateImage() {
        imagePane = new StackPane();

        imagePane.setPrefWidth(width);
        imagePane.setPrefHeight(height);

        String thingsOnField = field.ToString();
        ProcessFieldView(thingsOnField.charAt(0));
        ProcessLiquidView(thingsOnField.charAt(1));
        ProcessPlaceholderView(thingsOnField.charAt(2));
        return imagePane;
    }

    /**
     * Check what kind of field is in the grid cell and puts its image onto the image pane.
     *
     * @param type A character representing the type of the field.
     */
    private void ProcessFieldView(char type) {
        View view = new TileView();
        switch (type) {
            case 'g':
                view = new GoalTileView();
                break;
            case 'h':
                view = new HoleView();
                break;
            case 'l':
                view = new LeverTileView();
                break;
            case 't':
                view = new TileView();
                break;
            case 'c':
                view = new TrapTileView(false);
                break;
            case 'o':
                view = new TrapTileView(true);
                break;
            case 'w':
                view = new WallView();
                break;
            default:
                break;
        }
        view.Process(imagePane);
    }

    /**
     * Check what kind of placeholder is on the field (tile) and puts its image on the top of the image pane.
     *
     * @param type A character representing the type of the placeholder.
     */
    private void ProcessPlaceholderView(char type) {
        View view;
        switch (type) {
            case 'b':
                view = new BoxView();
                break;
            case 'p':
                view = new WorkerView(true);
                break;
            case 'w':
                view = new WorkerView(false);
                break;
            default:
                return;
        }
        view.Process(imagePane);
    }

    /**
     * Check what kind of liquid is on the field (tile) and puts its image on the top of the image pane.
     *
     * @param type A character representing the type of the liquid.
     */
    private void ProcessLiquidView(char type) {
        View view;
        switch (type) {
            case 'o':
                view = new OilView();
                break;
            case 'h':
                view = new HoneyView();
                break;
            default:
                return;
        }
        view.Process(imagePane);
    }
}
