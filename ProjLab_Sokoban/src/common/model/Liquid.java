package common.model;

import java.util.HashMap;
import java.util.Map;

import common.util.Factory;

public interface Liquid {
	double GetFriction();

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
