package common.model;

/**
 * Class representing oil, a type of liquid, can be placed on fields.
 */
public class Oil implements Liquid {

    /** The coefficient of friction (COF), the same constant value for all Oil objects */
	private static final double friction = 0.6;

    /**
     * Returns the COF of oil.
     * @return The coefficient of friction.
     */
	@Override
	public double GetFriction() {
		return friction;
	}

}
