package common.model;

import common.util.Direction;

public class Worker extends Placeholder {
	private int force;
	public static int defaultForce=80;
	
	public Worker(int force) {
		this.force=force;
	}

	public Worker() {
		force=defaultForce;
	}

	@Override
	public void PushedBy(Worker w, Direction dir,Move move) {

	}

	@Override
	public void Push(Placeholder obj, Direction dir,Move move) {
		obj.PushedBy(this, dir,move);
	}


	@Override
	public void PushedBy(Box box, Direction dir,Move move) {
		Tile from=tile;
		TryMove(dir,move);
		if (tile==from)
			Destroy();		
	}

	@Override
	public void ArrivedAt(GoalTile gt,Move move) {

	}

	@Override
	public void ArrivedAt(LeverTile lt) {

	}
	
	public void Move(Direction dir) { //TODO
		Tile old = tile;
		Move move = new Move(this);
		TryMove(dir,move);
		if(tile != null && tile != old)
			tile.Check(dir);
	}

	public int GetForce() {
		return force;
	}

	public void Place(Liquid liquid) {
		tile.Place(liquid);
	}

	public void AcceptPoint() {
		// TODO Auto-generated method stub
		
	}
	
}