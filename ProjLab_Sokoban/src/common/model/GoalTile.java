package common.model;

import common.util.Direction;

/**
 * Class representing a goal tile. The boxes should arrive here.
 */
public class GoalTile extends Tile {

    /** Is the goal tile locked, i.e. is there a(n unmovable) box on it? */
    private boolean locked = false;

    /**
     * Method for accepting a placeholder, works the same way as a normal tile,
     * plus calls the ArrivedAt when necessary.
     *
     * @param obj  The incoming placeholder.
     * @param dir  The direction in which the placeholder is moving.
     * @param move The move during which this method is called.
     */
    @Override
    public void Accept(Placeholder obj, Direction dir, Move move) {
        if (!IsLocked())
            if (IsEmpty()) {
                obj.ArrivedAt(this, move);
                obj.SetTile(this);
                this.placeholder = obj;
            } else {
                obj.Push(placeholder, dir, move);
                if (IsEmpty()) {
                    obj.ArrivedAt(this, move);
                    obj.SetTile(this);
                    this.placeholder = obj;
                }
            }
    }

    /**
     * Locks the goal tile, preventing any movement on it in the future.
     */
    public void Lock() {
        locked = true;
    }

    /**
     * Returns whether the goal tile is locked.
     * @return True, if it is locked, false if not.
     */
    private boolean IsLocked() {
        return locked;
    }

    /**
     * Returns the string representation of the type of goal tiles, that is, a "g".
     * @return The string representation.
     */
    @Override
    protected String TypeToString(){
        return "g";
    }
}
