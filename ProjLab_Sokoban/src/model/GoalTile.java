package model;

public class GoalTile extends Tile {
	
	private boolean locked=false;
	
	@Override
	public void Accept(Placeholder obj,Direction dir) {
		if (IsLocked())
			if (IsEmpty()) {
				obj.ArrivedAt(this);
				obj.SetField(this);
			}
			else {
				obj.Push(placeholder, dir);
				if (IsEmpty()) {
					obj.ArrivedAt(this);
					obj.SetField(this);
				}
			}
	}
	
	public void Lock() {
		locked=true;
	}
	
	private boolean IsLocked() {
		return locked;
	}
}
