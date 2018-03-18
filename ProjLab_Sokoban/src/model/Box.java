package model;

import skeleton.Logger;

public class Box extends Placeholder {

	@Override
	public void PushedBy(Worker w, Direction dir) {
		Logger.BeginMethod(this,"PushedBy",w,dir);

		TryMove(dir);

		Logger.EndMethod(this,"PushedBy",w,dir);
	}

	@Override
	public void PushedBy(Box box, Direction dir) {
		Logger.BeginMethod(this,"PushedBy",box,dir);

		TryMove(dir);

		Logger.EndMethod(this,"PushedBy",box,dir);
	}

	@Override
	public void Push(Placeholder obj, Direction dir) {
		Logger.BeginMethod(this,"Push",obj,dir);

		obj.PushedBy(this, dir);

		Logger.EndMethod(this,"Push",obj,dir);
	}

	@Override
	public void AcceptPoint(Direction dir) {
		Logger.BeginMethod(this,"AcceptPoint",dir);

		Field next=tile.GetNeighbour(dir);
		((Tile)next).ForwardPoint(dir);

		Logger.EndMethod(this,"AcceptPoint",dir);
	}

	@Override
	public void ArrivedAt(GoalTile gt) {
		Logger.BeginMethod(this,"ArrivedAt",gt);

		gt.Lock();
		Direction dir=tile.GetOppositeDirectionOf(gt);
		Field next=tile.GetNeighbour(dir);
		((Tile) next).ForwardPoint(dir);

		Logger.EndMethod(this,"ArrivedAt",gt);
	}

	@Override
	public void ArrivedAt(LeverTile lt) {
		Logger.BeginMethod(this,"ArrivedAt",lt);

		lt.Press();

		Logger.EndMethod(this,"ArrivedAt",lt);
	}
	
	public boolean IsStuck() {
		Logger.BeginMethod(this,"IsStuck");

		Logger.EndMethod(this,"IsStuck");
		return false;
	}

}
