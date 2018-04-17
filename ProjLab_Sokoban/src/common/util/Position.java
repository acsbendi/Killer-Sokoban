package common.util;

import javax.json.JsonObject;

public class Position {
	public final int column;
	public final int row;

	public Position(int y, int x) {
		row = y;
		column = x;
	}

	public Position(JsonObject jsonObject) {
		column=jsonObject.getInt("x");
		row=jsonObject.getInt("y");
	}

	public Position dirOffset(Direction dir) {
		switch (dir) {
		case Up:
			return new Position(row-1,column);
		case Down:
			return new Position(row+1,column);
		case Left:
			return new Position(row,column-1);
		case Right:
			return new Position(row,column+1);
		default:
			return null;
		}
	}

}