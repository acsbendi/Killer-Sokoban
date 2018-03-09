package model;

public abstract class Placeholder {
	
	protected Tile tile;
	public abstract void PushedBy(Worker w,Direction dir);	
	public abstract void PushedBy(Box box,Direction dir);	
	public abstract void Push(Placeholder obj,Direction dir);
	public abstract void ArrivedAt(GoalTile gt);
	public abstract void ArrivedAt(LeverTile lt);
	public abstract void AcceptPoint(Direction dir);
	public void SetField(Tile tile) {
		this.tile=tile;
	}
	public void InitializeField() {
		//TODO
	}
	public void Destroy() {
		//TODO
	}
	protected void TryMove(Direction dir) {
		//TODO
	}
	
}
