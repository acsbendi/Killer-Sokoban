package common.model;

/**
 * Class representing honey, a type of liquid, can be placed on fields.
 */
public class Honey implements Liquid {

    /** The coefficient of friction (COF), the same constant value for all Honey objects */
    private static final double friction = 1.4;

    /**
     * Returns the COF of honey.
     * @return The coefficient of friction.
     */
	@Override
	public double GetFriction() {
		return friction;
	}

	/**
	 * Returns the string representation of honey, that is, a "h".
	 * @return The string representation.
	 */
	@Override
	public String ToString(){
		return "h";
	}

}
