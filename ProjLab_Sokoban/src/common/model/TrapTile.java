package common.model;

import common.util.Direction;

public class TrapTile extends Tile {
	private int openCounter = 0;

	public void SignOn() {
		openCounter++;
	}

	public void SignOff() {
		openCounter--;
	}

	@Override
	public void Check(Direction dir) {
		Check();
		neighbours.get(dir).Check(dir);
	}

	private boolean IsClosed() {
		return openCounter == 0;
	}

	public void Check() {
		if (!IsClosed() && placeholder != null)
			placeholder.Destroy();
	}
}
