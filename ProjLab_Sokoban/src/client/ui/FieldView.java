package client.ui;

import common.model.Field;

public class FieldView {

    public FieldView(Field field){
        this.field = field;
    }

    private final Field field;

    public String ToString(){
        return field == null ? "---" : field.ToString();
    }
}
