package client.ui;

import client.ui.Views.*;
import common.model.Field;
import javafx.scene.layout.StackPane;

public class GridSquare {

    private static int width = 50;
    private static int height = 50;

    private final Field field;
    private StackPane imagePane;

    public GridSquare(Field field){
        this.field = field;
    }

    /**
     * @return
     */
    public StackPane CreateImage() {
        imagePane = new StackPane();

        imagePane.setPrefWidth(width);
        imagePane.setPrefHeight(height);

        String thingsOnField = field.ToString();
        ProcessFieldView(thingsOnField.charAt(0));
        ProcessPlaceholderView(thingsOnField.charAt(1));
        ProcessLiquidView(thingsOnField.charAt(2));
        return imagePane;
    }

    private void ProcessFieldView(char type){
        View view = new TileView();
        switch (type){
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

    private void ProcessPlaceholderView(char type){
        View view = null;
        switch (type){
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

    private void ProcessLiquidView(char type){
        View view;
        switch (type){
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
