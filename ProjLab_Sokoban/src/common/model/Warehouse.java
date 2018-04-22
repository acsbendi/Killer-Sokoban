package common.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Warehouse {
	private List<Field> fields = new ArrayList<>();
	private List<Box> boxesInPlay = new ArrayList<>(); // �sszes l�da alapvet�en (?)

	

	public Warehouse(Collection<Field> values, List<Box> boxes) {
		fields.addAll(values);
		boxesInPlay.addAll(boxes);
	}



	public boolean IsAllBoxStuck() {
		for (Box box : boxesInPlay)
			if (!box.IsStuck())
				return false;
		return true;
	}
}
