package model;

public abstract class Placeholder {

	protected Tile tile;

	public abstract void PushedBy(Worker w, Direction dir, Move move);

	public abstract void PushedBy(Box box, Direction dir, Move move);

	public abstract void Push(Placeholder obj, Direction dir, Move move);

	public abstract void ArrivedAt(GoalTile gt,Move move);

	public abstract void ArrivedAt(LeverTile lt);

	public abstract void AcceptPoint(Direction dir);

	public void SetField(Tile tile) {
		this.tile.ReleaseObject();
		this.tile = tile;
	}

	public void InitializeField(Tile tile) {
		this.tile = tile;
	}

	public void Destroy() {
		// TODO
	}

	protected void TryMove(Direction dir, Move move) {
		double friction = tile.GetFriction();
		boolean success = move.DecreaseForce(friction);
		if (success) {
			Field to = tile.GetNeighbour(dir);
			to.Accept(this, dir, move);
		}
	}
}
