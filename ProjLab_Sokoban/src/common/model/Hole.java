package common.model;

import common.util.Direction;


/**
 * Class representing a hole, a type of field,
 * where every incoming placeholders are destroyed (fall into the hole...)
 */
public class Hole extends Field {

    /**
     * Whenever a placeholder tries to move on it, it gets destroyed.
     *
     * @param obj The incoming placeholder.
     * @param dir The direction in which the placeholder is moving.
     * @param move The move during which this method is called.
     */
	@Override
	public void Accept(Placeholder obj, Direction dir,Move move) {
		obj.Destroy();
	}

    /**
     * Method for post-move checking, nothing happens, the checking sequence ends here.
     *
     * @param dir The direction of the move and thus the direction of the checking.
     */
	@Override
	public void Check(Direction dir) {

	}

}
