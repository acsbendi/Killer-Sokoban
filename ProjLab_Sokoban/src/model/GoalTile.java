package model;

import skeleton.Logger;

public class GoalTile extends Tile {
	
	private boolean locked=false;
	
	@Override
	public void Accept(Placeholder obj,Direction dir) {
		Logger.BeginMethod(this, "Accept", obj,dir);
		if (!IsLocked())
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
		Logger.EndMethod(this, "Accept", obj,dir);
	}
	
	public void Lock() {
		Logger.BeginMethod(this,"Lock");
		locked=true;
		Logger.EndMethod(this,"Lock");
	}
	
	private boolean IsLocked() {
		Logger.BeginMethod(this, "IsLocked");
		Logger.EndMethod(this, "IsLocked");
		return locked;
	}
}
