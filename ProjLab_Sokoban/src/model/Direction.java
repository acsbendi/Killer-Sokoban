package model;

import skeleton.Logger;

public enum Direction {
	Up, Down, Left, Right;
	public Direction Opposite() {
		Logger.BeginMethod(this,"Opposite");

		Logger.EndMethod(this,"Opposite");

		switch (this) {
		case Up:
			return Down;
		case Down:
			return Up;
		case Left:
			return Right;
		case Right:
			return Left;
		default:
			return null;
		}
	}
}
