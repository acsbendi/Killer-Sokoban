package model;

import util.Direction;

public class Tile extends Field {
	
	protected Placeholder placeholder;
	private Liquid liquid;
	
	
	@Override
	public void Check(Direction dir) {
		if(placeholder != null)
			neighbours.get(dir).Check(dir);
	}
	
	
	public void ReleaseObject() {
		placeholder = null;
	}
	
	protected boolean IsEmpty() {
		return placeholder==null;
	}
	
	
	
	public void InitializeObject(Placeholder obj) {
		placeholder = obj;
		placeholder.InitializeField(this);
	}
	
	public Direction GetOppositeDirectionOf(Field field) {
		for (Direction dir: neighbours.keySet()) {
			if (field== neighbours.get(dir))
				return dir.Opposite();
		}
		return null;
	}
	
	public void Place(Liquid liquid) {
		this.liquid=liquid;
	}
	public double GetFriction() {
		if (liquid==null) return 1;
		return liquid.GetFriction();
	}


	@Override
	public void Accept(Placeholder obj, Direction dir, Move move) {
		if (IsEmpty())
			obj.SetField(this);
		else {
			obj.Push(placeholder, dir,move);
			if (IsEmpty())
				obj.SetField(this);
		}
	}

}
