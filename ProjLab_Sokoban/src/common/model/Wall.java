package common.model;

import common.util.Direction;

/**
 * Class representing a hole, a type of field,
 * where no placeholders can enter
 */
public class Wall extends Field {



    /**
     * Method for post-move checking, nothing happens, the checking sequence ends here.
     *
     * @param dir The direction of the move and thus the direction of the checking.
     */
	@Override
	public void Check(Direction dir) {

	}

    /**
     * If any placeholder tries to move on it, it fails invariably.
     *
     * @param obj The incoming placeholder.
     * @param dir The direction in which the placeholder is moving.
     * @param move The move during which this method is called.
     */
	@Override
	public void Accept(Placeholder obj, Direction dir,Move move) {
		
	}

}
