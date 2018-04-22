package common.model;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import common.util.Direction;
import common.util.Factory;

/**
 * Abstract base class for all fields in the warehouse.
 */
public abstract class Field {

    /**
     * Abstract method for accepting a incoming placeholder. To be implemented by subclasses.
     * @param obj The incoming placeholder.
     * @param dir The direction in which the placeholder is moving.
     * @param move The move during which this method is called.
     */
	public abstract void Accept(Placeholder obj, Direction dir, Move move);

    /**
     * Abstract method for checking after a successful move. To be implemented by subclasses.
     * @param dir The direction of the move and thus the direction of the checking.
     */
	public abstract void Check(Direction dir);

	/** The map containing the neighboring fields of this field, in each direction. */
	protected Map<Direction, Field> neighbours = new EnumMap<Direction, Field>(Direction.class);

    /**
     * Setter for a neighbor in the specified direction.
     * @param dir The direction of the neighbor to be set.
     * @param field The neighbor to be set.
     */
	public void SetNeighbour(Direction dir, Field field) {
		neighbours.put(dir, field);
	}

    /**
     * Getter for the neighbor in a specified direction.
     * @param dir The direction of the neighbor.
     * @return The neighbor in a specified direction.
     */
	public Field GetNeighbour(Direction dir) {
		return neighbours.get(dir);
	}

    /**
     * Abstract method for returning the string representation of a field.
     * @return The string representation (reflecting type and current state).
     */
	public abstract String ToString();

	private static Map<String, Factory<Field>> prototypes = new HashMap<>();
	static {
		prototypes.put("GoalTile", new Factory<Field>() {

			@Override
			public Field create() {
				return new GoalTile();
			}

		});
		prototypes.put("Hole", new Factory<Field>() {

			@Override
			public Field create() {
				return new Hole();
			}

		});
		prototypes.put("LeverTile", new Factory<Field>() {

			@Override
			public Field create() {
				return new LeverTile();
			}

		});
		prototypes.put("Tile", new Factory<Field>() {

			@Override
			public Field create() {
				return new Tile();
			}

		});
		prototypes.put("TrapTile", new Factory<Field>() {

			@Override
			public Field create() {
				return new TrapTile();
			}

		});
		prototypes.put("Wall", new Factory<Field>() {

			@Override
			public Field create() {
				return new Wall();
			}

		});
	}


	public static Field create(String type) {
		return prototypes.get(type).create();
	}

}
