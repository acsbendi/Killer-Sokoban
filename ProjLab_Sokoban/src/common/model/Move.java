package common.model;

/**
 * Class representing a move.
 */
public class Move {
    /** The last worker who participated in the move.  */
    private Worker owner;

    /** The currently remaining force. Decreases with every participating placeholder. */
    private double remainingForce;

    /**
     * Constructs a new move, with the specified owner at the beginning.
     * @param worker
     */
    public Move(Worker worker) {
        owner = worker;
        remainingForce = owner.GetForce();
    }

    /**
     * Gives the point to the owner of the move.
     */
    public void AcceptPoint() {
        owner.AcceptPoint();
    }

    /**
     * Decreases the remaining force of the move, returns whether the move is still valid.
     * @param decr The amount by which the remaining force should be decreased.
     * @return True, if the move is valid after the decrease, false if not.
     */
    public boolean DecreaseForce(double decr) {
        return (remainingForce -= decr) > 0;
    }

    /**
     * Updates the owner of the move.
     * @param worker The new owner.
     */
    public void UpdateOwner(Worker worker) {
        owner = worker;
    }
}
