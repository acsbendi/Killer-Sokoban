package model;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import util.Factory;

public abstract class Field {
	public abstract void Accept(Placeholder obj, Direction dir, Move move);

	public abstract void Check(Direction dir);

	protected Map<Direction, Field> neighbours = new EnumMap<Direction, Field>(Direction.class);

	public void SetNeighbour(Direction dir, Field field) {
		neighbours.put(dir, field);
	}

	public Field GetNeighbour(Direction dir) {
		return neighbours.get(dir);
	}

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
