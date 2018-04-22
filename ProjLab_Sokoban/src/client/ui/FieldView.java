package client.ui;

import common.model.Field;
import jdk.internal.jline.internal.Nullable;

public class FieldView {

    FieldView(Field field){
        this.field = field;
    }

    @Nullable
	private final Field field;

    public String ToString(){
        return field == null ? "---" : field.ToString();
    }
}
