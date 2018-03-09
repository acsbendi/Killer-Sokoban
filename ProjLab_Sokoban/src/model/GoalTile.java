package model;

public class GoalTile extends Tile {
	
	private boolean locked=false;
	
	@Override
	public void Accept(Placeholder obj,Direction dir) {
		//TODO
	}
	
	public void Lock() {
		locked=true;
	}
	
	private boolean IsLocked() {
		return locked;
	}
}
