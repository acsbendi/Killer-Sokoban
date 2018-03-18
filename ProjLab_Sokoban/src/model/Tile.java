package model;

import skeleton.Logger;

import java.util.EnumMap;
import java.util.Map;

public class Tile implements Field {
	
	protected Map<Direction,Field> neighbours = new EnumMap<Direction,Field>(Direction.class);
	protected Placeholder placeholder;
	
	@Override
	public void Check(Direction dir) {
		Logger.BeginMethod(this,"Check", dir);

		if(placeholder != null)
			neighbours.get(dir).Check(dir);

		Logger.EndMethod(this,"Check", dir);
	}
	
	public Field GetNeighbour(Direction dir) {
		Logger.BeginMethod(this,"GetNeighbour", dir);

		Logger.EndMethod(this,"GetNeighbour", dir);
		return neighbours.get(dir);
	}
	
	public void ReleaseObject() {
		Logger.BeginMethod(this,"ReleaseObject");

		placeholder = null;

		Logger.EndMethod(this,"ReleaseObject");
	}
	
	protected boolean IsEmpty() {
		Logger.BeginMethod(this,"IsEmpty");

		Logger.EndMethod(this,"IsEmpty");
		return placeholder==null;
	}
	
	public void ForwardPoint(Direction dir) {
		Logger.BeginMethod(this,"ForwardPoint", dir);

		if (!IsEmpty())
			placeholder.AcceptPoint(dir);

		Logger.EndMethod(this,"ForwardPoint", dir);
	}
	
	public void SetNeighbour(Direction dir,Field field) {
		Logger.BeginMethod(this,"SetNeighbour", dir, field);

		neighbours.put(dir, field);

		Logger.EndMethod(this,"SetNeighbour", dir, field);
	}
	
	public void InitializeObject(Placeholder obj) {
		Logger.BeginMethod(this,"InitializeObject", obj);

		placeholder = obj;
		placeholder.InitializeField(this);

		Logger.EndMethod(this,"InitializeObject", obj);
	}
	
	public Direction GetOppositeDirectionOf(Field field) {
		Logger.BeginMethod(this,"GetOppositeDirectionOf", field);

		for (Direction dir: neighbours.keySet()) {
			if (field== neighbours.get(dir)) {
				Logger.EndMethod(this,"GetOppositeDirectionOf", field);

				return dir.Opposite();
			}
		}
		Logger.EndMethod(this,"GetOppositeDirectionOf", field);

		return null;
	}

	@Override
	public void Accept(Placeholder obj, Direction dir) {
		Logger.BeginMethod(this,"Accept", obj, dir);

		if (IsEmpty())
			obj.SetField(this);
		else {
			obj.Push(placeholder, dir);
			if (IsEmpty())
				obj.SetField(this);
		}

		Logger.EndMethod(this,"Accept", obj, dir);
	}

}
