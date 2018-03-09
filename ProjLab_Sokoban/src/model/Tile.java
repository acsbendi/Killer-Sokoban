package model;

import java.util.EnumMap;
import java.util.Map;

public class Tile implements Field {
	
	private Map<Direction,Field> neighbour=new EnumMap<Direction,Field>(Direction.class);
	protected Placeholder placeholder;
	
	@Override
	public void Check(Direction dir) {
		// TODO Auto-generated method stub

	}
	
	public Field GetNeighbour(Direction dir) {
		return neighbour.get(dir);
	}
	
	public void ReleaseObject() {
		//TODO
	}
	
	private boolean IsEmpty() {
		return placeholder==null;
	}
	
	public void ForwardPoint(Direction dir) {
		//TODO
	}
	
	public void SetNeighbour(Direction dir,Field field) {
		neighbour.put(dir, field);
	}
	
	public void InitializeObject(Placeholder obj) {
		//TODO
	}
	
	public Direction GetOppositeDirectionOf(Field field) {
		for (Direction dir:neighbour.keySet()) {
			if (field==neighbour.get(dir))
				return dir.Opposite();
		}
	}

	@Override
	public void Accept(Placeholder obj, Direction dir) {
		// TODO Auto-generated method stub
		
	}

}
