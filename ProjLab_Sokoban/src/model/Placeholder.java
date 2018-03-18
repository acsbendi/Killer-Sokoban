package model;

import skeleton.Logger;

public abstract class Placeholder {
	
	protected Tile tile;
	public abstract void PushedBy(Worker w,Direction dir);	
	public abstract void PushedBy(Box box,Direction dir);	
	public abstract void Push(Placeholder obj,Direction dir);
	public abstract void ArrivedAt(GoalTile gt);
	public abstract void ArrivedAt(LeverTile lt);
	public abstract void AcceptPoint(Direction dir);
	public void SetField(Tile tile) {
		Logger.BeginMethod(this, "SetField",tile);
		this.tile.ReleaseObject();
		this.tile=tile;
		Logger.EndMethod(this, "SetField",tile);
	}
	public void InitializeField(Tile tile) {
		Logger.BeginMethod(this, "InitializeField",tile);
		this.tile = tile;
		Logger.EndMethod(this, "InitializeField",tile);
	}
	public void Destroy() {
		Logger.BeginMethod(this, "Destroy");
		//TODO
		Logger.EndMethod(this, "Destroy");
	}
	protected void TryMove(Direction dir) {
		Logger.BeginMethod(this, "TryMove",dir);
		Field to=tile.GetNeighbour(dir);
		to.Accept(this, dir);
		Logger.BeginMethod(this, "TryMove",dir);
	}
}
