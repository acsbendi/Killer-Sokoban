package common.model;

import common.util.Direction;

public class GoalTile extends Tile {
	
	private boolean locked=false;
	
	@Override
	public void Accept(Placeholder obj,Direction dir,Move move) {
		if (!IsLocked())
			if (IsEmpty()) {
				obj.ArrivedAt(this,move);
				obj.SetField(this);
			}
			else {
				obj.Push(placeholder, dir,move);
				if (IsEmpty()) {
					obj.ArrivedAt(this,move);
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
