package common.model;

import common.util.Direction;

/**
 * Class representing a lever tile.
 * Can be opened by a number of trap tiles.
 * If it is opened, works the same way as a hole.
 */
public class TrapTile extends Tile {

    /** How many connected lever tiles are pressed? */
	private int openCounter = 0;

    /**
     * A connected lever tile is now pressed.
     */
	public void SignOn() {
		openCounter++;
	}

    /**
     * A connected lever tile is now not pressed.
     */
	public void SignOff() {
		openCounter--;
	}

    /**
     * Method for post-move checking, works the same way as a normal tile,
     * plus performs an internal check as well.
     *
     * @param dir The direction of the move and thus the direction of the checking.
     */
	@Override
	public void Check(Direction dir) {
		Check();
		neighbours.get(dir).Check(dir);
	}

    /**
     * Returns whether the lever tile is closed.
     * @return True if closed, false if opened.
     */
	private boolean IsClosed() {
		return openCounter == 0;
	}

    /**
     * Internal check, if the trap tile is opened and
     * there is a placeholder on it, it gets destroyed.
     */
	public void Check() {
		if (!IsClosed() && placeholder != null) {
			liquid=null;
			placeholder.Destroy();
		}

	}

	/**
	 * Returns the string representation of the type of a trap tile,
     * depending on its specific state, that is, if it is closed ("c") or not ("o").
	 * @return The string representation.
	 */
	@Override
	protected String TypeToString() {
        return IsClosed() ? "c" : "o";
    }
}
