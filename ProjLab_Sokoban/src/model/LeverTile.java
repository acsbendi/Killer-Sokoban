package model;

import java.util.ArrayList;
import java.util.List;

import skeleton.Logger;

public class LeverTile extends Tile {
	
	private boolean pressed=false;
	private List<TrapTile> trapTiles=new ArrayList<>();

	@Override
	public void Check(Direction dir) {
		Logger.BeginMethod(this, "Check",dir);
		if(placeholder != null){
			if(IsPressed()){
				for(TrapTile tt : trapTiles)
					tt.Check();
			}
			neighbours.get(dir).Check(dir);
		}
		Logger.EndMethod(this, "Check",dir);
	}

	@Override
	public void Accept(Placeholder obj, Direction dir) {
		Logger.BeginMethod(this, "Accept",obj,dir);
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
		Logger.EndMethod(this, "Accept",obj,dir);
	}
	
	public void ReleaseObject() {
		Logger.BeginMethod(this, "ReleaseObject");
		if(IsPressed()) {
			for (TrapTile trapTile : trapTiles)
				trapTile.SignOff();
			Release();
		}
		Logger.EndMethod(this, "ReleaseObject");
	}
	
	public void Press() {
		Logger.BeginMethod(this, "Press");
		pressed=true;
		for (TrapTile trapTile : trapTiles )
			trapTile.SignOn();
		Logger.EndMethod(this, "Press");
	}
	
	public void AddTrapdoor(TrapTile traptile) {
		Logger.BeginMethod(this, "AddTrapdoor",traptile);
		trapTiles.add(traptile);
		Logger.EndMethod(this, "AddTrapdoor",traptile);
	}
	private boolean IsPressed() {
		Logger.BeginMethod(this, "IsPressed");
		Logger.EndMethod(this, "IsPressed");
		return pressed;
	}
	private void Release() {
		Logger.BeginMethod(this, "Release");
		pressed=false;
		Logger.EndMethod(this, "Release");
	}
}
