package model;

import java.util.ArrayList;
import java.util.List;

public class LeverTile extends Tile {
	
	private boolean pressed=false;
	private List<TrapTile> trapTiles=new ArrayList<>();

	@Override
	public void Check(Direction dir) {
		// TODO Auto-generated method stub
		super.Check(dir);
	}

	@Override
	public void Accept(Placeholder obj, Direction dir) {
		// TODO Auto-generated method stub
		super.Accept(obj, dir);
	}
	
	public void ReleaseObject() {
		//TODO
	}
	
	public void Press() {
		pressed=true;
		//TODO
	}
	
	public void AddTrapdoor(TrapTile traptile) {
		trapTiles.add(traptile);
	}
	private boolean IsPressed() {
		return pressed;
	}
	private void Release() {
		pressed=false;
		//TODO
	}

}
