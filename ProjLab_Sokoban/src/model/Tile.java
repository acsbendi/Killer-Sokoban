package model;

import java.util.EnumMap;
import java.util.Map;

public class Tile implements Field {
	
	protected Map<Direction,Field> neighbours = new EnumMap<Direction,Field>(Direction.class);
	protected Placeholder placeholder;
	
	@Override
	public void Check(Direction dir) {
		if(placeholder != null)
			neighbours.get(dir).Check(dir);
	}
	
	public Field GetNeighbour(Direction dir) {
		return neighbours.get(dir);
	}
	
	public void ReleaseObject() {
		placeholder = null;
	}
	
	protected boolean IsEmpty() {
		return placeholder==null;
	}
	
	public void ForwardPoint(Direction dir) {
		if (!IsEmpty())
			placeholder.AcceptPoint(dir);
	}
	
	public void SetNeighbour(Direction dir,Field field) {
		neighbours.put(dir, field);
	}
	
	public void InitializeObject(Placeholder obj) {
		placeholder = obj;
	}
	
	public Direction GetOppositeDirectionOf(Field field) {
		for (Direction dir: neighbours.keySet()) {
			if (field== neighbours.get(dir))
				return dir.Opposite();
		}
		return null;
	}

	@Override
	public void Accept(Placeholder obj, Direction dir) {
		if (IsEmpty())
			obj.SetField(this);
		else {
			obj.Push(placeholder, dir);
			if (IsEmpty())
				obj.SetField(this);
		}
	}

}
