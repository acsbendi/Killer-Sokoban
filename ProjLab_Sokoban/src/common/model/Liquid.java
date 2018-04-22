package common.model;

import java.util.HashMap;
import java.util.Map;

import common.util.Factory;

/**
 * Interface representing liquids, that can be placed onto tiles and that have a friction.
 */
public interface Liquid {
    /**
     * Returns the coefficient of friction (COF) specific to a liquid.
     * @return The coefficient of friction.
     */
	double GetFriction();

    /**
     * Returns the string representation of a liquid,
     * which depends on its (subclass-specific) type.
     * @return The string representation of a liquid.
     */
     String ToString();

	public static Liquid create(String type) {
		Map<String, Factory<Liquid>> prototypes = new HashMap<>();
		prototypes.put("Oil", new Factory<Liquid>() {

			@Override
			public Liquid create() {
				return new Oil();
			}

		});
		prototypes.put("Honey", new Factory<Liquid>() {

			@Override
			public Liquid create() {
				return new Honey();
			}

		});
		return prototypes.get(type).create();
	}
}
