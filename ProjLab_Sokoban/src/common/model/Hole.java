package common.model;

import common.util.Direction;

public class Hole extends Field {

	@Override
	public void Accept(Placeholder obj, Direction dir,Move move) {
		obj.Destroy();
	}
	
	@Override
	public void Check(Direction dir) {

	}

}
