package model;

public class Box extends Placeholder {

	@Override
	public void PushedBy(Worker w, Direction dir,Move move) {
		TryMove(dir,move);
	}

	@Override
	public void PushedBy(Box box, Direction dir,Move move) {
		TryMove(dir,move);
	}

	@Override
	public void Push(Placeholder obj, Direction dir,Move move) {
		obj.PushedBy(this, dir,move);
	}


	@Override
	public void ArrivedAt(GoalTile gt,Move move) {
		gt.Lock();
		move.AcceptPoint();
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
