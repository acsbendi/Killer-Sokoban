package model;

public class Worker extends Placeholder {
	private int force; //TODO hogy kapjon értéket?

	@Override
	public void PushedBy(Worker w, Direction dir) {

	}

	@Override
	public void Push(Placeholder obj, Direction dir) {
		obj.PushedBy(this, dir);
	}

	@Override
	public void AcceptPoint(Direction dir) {
		// TODO Auto-generated method stub
	}

	@Override
	public void PushedBy(Box box, Direction dir) {
		Tile from=tile;
		TryMove(dir);
		if (tile==from)
			Destroy();		
	}

	@Override
	public void ArrivedAt(GoalTile gt) {

	}

	@Override
	public void ArrivedAt(LeverTile lt) {

	}
	
	public void Move(Direction dir) { //TODO
		Tile old = tile;
		TryMove(dir);
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
