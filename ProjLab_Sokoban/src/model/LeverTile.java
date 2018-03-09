package model;

import java.util.ArrayList;
import java.util.List;

public class LeverTile extends Tile {
	
	private boolean pressed=false;
	private List<TrapTile> trapTiles=new ArrayList<>();

	@Override
	public void Check(Direction dir) {
		if(placeholder != null){
			if(IsPressed()){
				for(TrapTile tt : trapTiles)
					tt.Check();
			}
			neighbours.get(dir).Check(dir);
		}
	}

	@Override
	public void Accept(Placeholder obj, Direction dir) {
		if(IsEmpty()){
			obj.ArrivedAt(this);
			obj.SetField(this);
		} else {
			obj.Push(placeholder,dir);
			if(IsEmpty()){
				obj.ArrivedAt(this);
				obj.SetField(this);
			}
		}
	}
	
	public void ReleaseObject() {
		if(IsPressed()) {
			for (TrapTile trapTile : trapTiles)
				trapTile.SignOff();
			Release();
		}
	}
	
	public void Press() {
		pressed=true;
		for (TrapTile trapTile : trapTiles )
			trapTile.SignOn();
	}
	
	public void AddTrapdoor(TrapTile traptile) {
		trapTiles.add(traptile);
	}
	private boolean IsPressed() {
		return pressed;
	}
	private void Release() {
		pressed=false;
	}

}
