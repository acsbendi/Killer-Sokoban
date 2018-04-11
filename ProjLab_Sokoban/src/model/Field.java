package model;

import java.util.EnumMap;
import java.util.Map;

public abstract class Field {
	public abstract void Accept(Placeholder obj,Direction dir,Move move);
	public abstract void Check(Direction dir);
	protected Map<Direction,Field> neighbours = new EnumMap<Direction,Field>(Direction.class);
	public void SetNeighbour(Direction dir,Field field) {
		neighbours.put(dir, field);
	}
	public Field GetNeighbour(Direction dir) {
		return neighbours.get(dir);
	}

}
