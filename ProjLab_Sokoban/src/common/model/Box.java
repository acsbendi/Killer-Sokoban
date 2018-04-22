package common.model;

import common.util.Direction;

/**
 * Class representing a worker, a type of placeholder, that is, it can occupy a tile.
 */
public class Box extends Placeholder {

    /** The mass of a box, the same constant value for all Box objects */
    private static final int mass = 20;

    /**
     * Constructs a new box.
     */
	public Box(){
		super(mass);
	}

    /**
     * The box is pushed by a worker, tries to move to the next field.
     * @param w The pushing worker.
     * @param dir The direction of the push (move).
     * @param move The move, during which the push is carried out.
     */
	@Override
	public void PushedBy(Worker w, Direction dir,Move move) {
		TryMove(dir,move);
	}

    /**
     * The box is pushed by another box, tries to move to the next field.
     * @param box The pushing box.
     * @param dir The direction of the push (move).
     * @param move The move, during which the push is carried out.
     */
	@Override
	public void PushedBy(Box box, Direction dir,Move move) {
		TryMove(dir,move);
	}

    /**
     * The box pushes another placeholder, calls the PushedBy method on that placeholder
     * (for double dispatch through visitor pattern)
     * @param obj The placeholder to be pushed by this box.
     * @param dir The direction of the push (move).
     * @param move The move, during which the push is carried out.
     */
	@Override
	public void Push(Placeholder obj, Direction dir,Move move) {
		obj.PushedBy(this, dir,move);
	}


    /**
     * Box arrives at a goal tile, it gets locked (cannot move anymore) and gives point for it.
     * @param gt The goal tile, on which the box arrives.
     * @param move The move, during which it arrives.
     */
	@Override
	public void ArrivedAt(GoalTile gt,Move move) {
		gt.Lock();
		move.AcceptPoint();
	}

    /**
     * Box arrives at a lever tile, presses it.
     * @param lt The lever tile, on which the box arrives.
     */
	@Override
	public void ArrivedAt(LeverTile lt) {
		lt.Press();
	}

    /**
     * Checks whether the box is stuck, i.e. cannot move.
     * @return True, if the box is stuck, false if not.
     */
	public boolean IsStuck() {
		//TODO
		return false;
	}
}
