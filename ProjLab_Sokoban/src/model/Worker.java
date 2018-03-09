package model;

public class Worker extends Placeholder {

	@Override
	public void PushedBy(Worker w, Direction dir) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ArrivedAt(LeverTile lt) {
		// TODO Auto-generated method stub
		
	}
	
	public void Move(Direction dir) {
		//TODO
	}

}
