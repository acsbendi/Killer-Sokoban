package model;

import java.util.HashMap;
import java.util.Map;

import util.Factory;

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
	
	private static Map<String, Factory<Placeholder>> prototypes = new HashMap<>();
	static {
		prototypes.put("Worker", new Factory<Placeholder>() {

			@Override
			public Placeholder create() {
				return new Worker();
			}

		});
		prototypes.put("Box", new Factory<Placeholder>() {

			@Override
			public Placeholder create() {
				return new Box();
			}

		});
	}

	public static Placeholder create(String type) {
		return prototypes.get(type).create();
	}
}
