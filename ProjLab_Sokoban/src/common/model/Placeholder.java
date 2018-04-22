package common.model;

import java.util.HashMap;
import java.util.Map;

import common.util.Direction;
import common.util.Factory;

/**
 * Abstract class representing a placeholder, that is, an object that can occupy a tile.
 */
public abstract class Placeholder {

    /** The tile that the placeholder currently occupies. */
	protected Tile tile;

	/** The mass of the placeholder, a constant value. */
	protected final int mass;

    /**
     * Constructs a new placeholder with the specified mass.
     * @param mass The mass of the new placeholder.
     */
	protected Placeholder(int mass){
		this.mass = mass;
	}

    /**
     * Abstract method for being pushed by a worker. To be implemented by subclasses.
     * @param w The pushing worker.
     * @param dir The direction of the push (move).
     * @param move The move, during which the push is carried out.
     */
	public abstract void PushedBy(Worker w, Direction dir, Move move);

    /**
     * Abstract method for being pushed by a box. To be implemented by subclasses.
     * @param box The pushing box.
     * @param dir The direction of the push (move).
     * @param move The move, during which the push is carried out.
     */
	public abstract void PushedBy(Box box, Direction dir, Move move);

    /**
     * Abstract method for pushing another placeholder. To be implemented by subclasses.
     * (for double dispatch through visitor pattern)
     * @param obj The placeholder to be pushed by this placeholder.
     * @param dir The direction of the push (move).
     * @param move The move, during which the push is carried out.
     */
	public abstract void Push(Placeholder obj, Direction dir, Move move);

    /**
     * Abstract method for arriving at a goal tile.  To be implemented by subclasses.
     * @param gt The goal tile, on which the placeholder arrives.
     */
	public abstract void ArrivedAt(GoalTile gt,Move move);

    /**
     * Abstract method for arriving at a lever tile.  To be implemented by subclasses.
     * @param lt The lever tile, on which the placeholder arrives.
     */
	public abstract void ArrivedAt(LeverTile lt);

    /**
     * Setter for the tile of this placeholder.
     * Also removes itself from the previous tile.
     * @param tile The new tile.
     */
	public void SetTile(Tile tile) {
		this.tile.ReleaseObject();
		this.tile = tile;
	}

    /**
     * Initializes the tile of this placeholder.
     * @param tile The starting tile of the placeholder.
     */
	public void InitializeTile(Tile tile) {
		this.tile = tile;
	}

    /**
     * Destroys the placeholder.
     */
	public void Destroy() {
		// TODO
	}

    /**
     * Tries to move onto the next field in the specified direction.
     * @param dir The direction of the move.
     * @param move The move object.
     */
	protected void TryMove(Direction dir, Move move) {
		double friction = tile.GetFriction();
		boolean success = move.DecreaseForce(friction * mass);
		if (success) {
			Field to = tile.GetNeighbour(dir);
			to.Accept(this, dir, move);
		}
	}

    /**
     * Returns the string representation of a placeholder,
     * which depends on its (subclass-specific) type.
     * @return The string representation of a placeholder.
     */
    public abstract String ToString();
	
	private static Map<String, Factory<Placeholder>> prototypes = new HashMap<>();
	static {
		prototypes.put("Worker", new Factory<Placeholder>() {

			@Override
			public Placeholder create() {
				return new Worker();
			}

		});
		prototypes.put("Box", new Factory<Placeholder>() {

			@Override
			public Placeholder create() {
				return new Box();
			}

		});
	}

	public static Placeholder create(String type) {
		return prototypes.get(type).create();
	}
}
