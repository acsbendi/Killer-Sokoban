package model;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {
	private static List<Field> fields = new ArrayList<>();
	private static List<Box> boxesInPlay = new ArrayList<>();

	public static void Load(int id) {
		//TODO

	}

	public static boolean IsAllBoxStuck() {
		for(Box box : boxesInPlay)
			if(!box.IsStuck())
				return false;
		return true;
	}
}
