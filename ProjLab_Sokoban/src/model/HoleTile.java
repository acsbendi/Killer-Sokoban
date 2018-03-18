package model;

import skeleton.Logger;

public class HoleTile extends Tile {

	@Override
	public void Accept(Placeholder obj, Direction dir) {
		Logger.BeginMethod(this, "Accept",obj,dir);
		obj.Destroy();
		Logger.EndMethod(this, "Accept",obj,dir);
	}
	
	@Override
	public void Check(Direction dir) {
		Logger.BeginMethod(this, "Check",dir);
		Logger.EndMethod(this, "Check",dir);
	}

}
