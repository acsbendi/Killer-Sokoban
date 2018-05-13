package client.ui;

import common.model.Field;
import javafx.scene.layout.StackPane;

public class GridSquare {

    public GridSquare(Field field){
        this.field = field;
    }

    private final Field field;

    public String ToString(){
        return field == null ? "---" : field.ToString();
    }

    /**
     * @return
     */
    public StackPane CreateImage() {
        // TODO implement here
        return null;
    }
}
