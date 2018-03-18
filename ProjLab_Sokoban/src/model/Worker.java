package model;

import skeleton.Logger;

public class Worker extends Placeholder {

	@Override
	public void PushedBy(Worker w, Direction dir) {
		Logger.BeginMethod(this,"PushedBy", w, dir);

		Logger.EndMethod(this,"PushedBy", w, dir);
	}

	@Override
	public void Push(Placeholder obj, Direction dir) {
		Logger.BeginMethod(this,"Push", obj, dir);

		obj.PushedBy(this, dir);

		Logger.EndMethod(this,"Push", obj, dir);
	}

	@Override
	public void AcceptPoint(Direction dir) {
		Logger.BeginMethod(this,"AcceptPoint", dir);

		Logger.EndMethod(this,"AcceptPoint", dir);
	}

	@Override
	public void PushedBy(Box box, Direction dir) {
		Logger.BeginMethod(this,"PushedBy", box, dir);

		Tile from=tile;
		TryMove(dir);
		if (tile==from)
			Destroy();

		Logger.EndMethod(this,"PushedBy", box, dir);
	}

	@Override
	public void ArrivedAt(GoalTile gt) {
		Logger.BeginMethod(this,"ArrivedAt", gt);

		Logger.EndMethod(this,"ArrivedAt", gt);
	}

	@Override
	public void ArrivedAt(LeverTile lt) {
		Logger.BeginMethod(this,"ArrivedAt", lt);

		Logger.EndMethod(this,"ArrivedAt", lt);
	}
	
	public void Move(Direction dir) {
		Logger.BeginMethod(this,"Move", dir);

		Tile old = tile;
		TryMove(dir);
		if(tile != null && tile != old)
			tile.Check(dir);

		Logger.EndMethod(this,"Move", dir);
	}

}