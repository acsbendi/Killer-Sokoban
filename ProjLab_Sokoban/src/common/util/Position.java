package common.util;

import javax.json.JsonObject;

public class Position implements Comparable<Position> {
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
	
	@Override
	public String toString() {
		return "{x: "+column+", y: "+row+"}";
	}

	@Override
	public boolean equals(Object obj) {
		return (this.row == ((Position)obj).row) && (this.column == ((Position)obj).column);
	}

	@Override
	public int compareTo(Position o) {
		if (row>o.row) return 1;
		if (row<o.row) return -1;
		return column-o.column;
	}
}