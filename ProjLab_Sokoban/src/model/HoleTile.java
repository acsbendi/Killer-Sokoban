package model;

public class HoleTile extends Tile {

	@Override
	public void Accept(Placeholder obj, Direction dir) {
		obj.Destroy();
	}
	
	@Override
	public void Check(Direction dir) {

	}

}
