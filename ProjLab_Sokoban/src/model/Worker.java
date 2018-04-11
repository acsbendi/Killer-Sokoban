package model;

public class Worker extends Placeholder {
	private int force; //TODO hogy kapjon értéket?

	@Override
	public void PushedBy(Worker w, Direction dir,Move move) {

	}

	@Override
	public void Push(Placeholder obj, Direction dir,Move move) {
		obj.PushedBy(this, dir,move);
	}

	@Override
	public void AcceptPoint(Direction dir) {
		// TODO Auto-generated method stub
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
		//TODO
	}
	
}
