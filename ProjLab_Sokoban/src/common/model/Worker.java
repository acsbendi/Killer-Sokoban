package common.model;

import common.util.Direction;

/**
 * Class representing a worker, a type of placeholder, that is, it can occupy a tile.
 */
public class Worker extends Placeholder {

    /** The force of this worker, plays a role in moving. */
	private int force;

	/** The points the worker has earned in the game so far. */
	private int points;

    /** The default force of workers. */
	private static final int defaultForce = 80;

    /** The mass of a worker, the same constant value for all Worker objects */
    private static final int mass = 10;

    /** The worker of the local player. */
    public static Worker localWorker;

    /**
     * Constructs a new worker, with the specified force.
     */
	public Worker(int force) {
		super(mass);
	    this.force=force;
		points = 0;
	}

    /**
     * Constructs a new worker, with the default force.
     */
	public Worker() {
		super(mass);
	    force=defaultForce;
	    points = 0;
	}

    /**
     * The worker is pushed by another worker, nothing happens.
     * @param w The pushing worker.
     * @param dir The direction of the push (move).
     * @param move The move, during which the push is carried out.
     */
	@Override
	public void PushedBy(Worker w, Direction dir,Move move) {

	}

    /**
     * The worker pushes another placeholder, calls the PushedBy method on that placeholder
     * (for double dispatch through visitor pattern)
     * @param obj The placeholder to be pushed by this worker.
     * @param dir The direction of the push (move).
     * @param move The move, during which the push is carried out.
     */
	@Override
	public void Push(Placeholder obj, Direction dir,Move move) {
		obj.PushedBy(this, dir,move);
	}


    /**
     * The worker is pushed by a box, tries to move to the next field, gets destroyed on failure,
     * otherwise takes ownership of the move.
     * @param box The pushing box.
     * @param dir The direction of the push (move).
     * @param move The move, during which the push is carried out.
     */
	@Override
	public void PushedBy(Box box, Direction dir,Move move) {
        move.UpdateOwner(this);
		Tile from = tile;
		TryMove(dir,move);
		if (tile==from)
			Destroy();
	}

    /**
     * Worker arrives at a goal tile, nothing happens.
     * @param gt The goal tile, on which the worker arrives.
     * @param move The move, during which it arrives.
     */
	@Override
	public void ArrivedAt(GoalTile gt,Move move) {

	}

    /**
     * Worker arrives at a lever tile, nothing happens.
     * @param lt The lever tile, on which the worker arrives.
     */
	@Override
	public void ArrivedAt(LeverTile lt) {

	}

    /**
     * The worker starts a new move in the specified direction.
     * @param dir The direction of the move.
     */
	public void Move(Direction dir) {
		if (tile != null) {
			Tile old = tile;
			Move move = new Move(this);
			TryMove(dir,move);
			if(tile != null && tile != old) {
				tile.Check(dir);
			}
		}
	}

    /**
     * Returns the force of the worker.
     * @return The force that the worker has.
     */
	public int GetForce() {
		return force;
	}

	/**
	 * Returns the points of the worker.
	 * @return The points that the worker has.
	 */
	public int GetPoints() {
		return points;
	}

	/**
     * The worker places a specified liquid on its current field.
     * @param liquid The liquid to be placed.
     */
	public void Place(Liquid liquid) {
		if (tile != null) {
			tile.Place(liquid);
		}
	}

	/**
	 * Returns the string representation of a worker, that is, a "w",
     * if this worker does not belong to the local player, "p" otherwise.
	 * @return The string representation.
	 */
	@Override
	public String ToString(){

	    return this == localWorker ? "p" : "w";
    }

    /**
     * Worker accepts point.
     */
	public void AcceptPoint() {
		points++;
	}
	
}
