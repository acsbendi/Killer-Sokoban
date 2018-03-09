package model;

public class Box extends Placeholder {

	@Override
	public void PushedBy(Worker w, Direction dir) {
		TryMove(dir);
	}

	@Override
	public void PushedBy(Box box, Direction dir) {
		TryMove(dir);
	}

	@Override
	public void Push(Placeholder obj, Direction dir) {
		obj.PushedBy(this, dir);
	}

	@Override
	public void AcceptPoint(Direction dir) {
		Field next=tile.GetNeighbour(dir);
		((Tile)next).ForwardPoint(dir);
	}

	@Override
	public void ArrivedAt(GoalTile gt) {
		gt.Lock();
		Direction dir=tile.GetOppositeDirectionOf(gt);
		Field next=tile.GetNeighbour(dir);
		((Tile) next).ForwardPoint(dir);
	}

	@Override
	public void ArrivedAt(LeverTile lt) {
		lt.Press();
	}
	
	public boolean IsStuck() {
		//TODO
		return false;
	}

}
