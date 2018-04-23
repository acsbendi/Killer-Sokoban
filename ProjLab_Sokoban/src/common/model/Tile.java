package common.model;

import common.util.Direction;

/**
 * Class representing a tile, a general field, on which placeholders can move and
 * on which liquids can be placed.
 */
public class Tile extends Field {

    /** The placeholder occupying this tile, may be null. */
    protected Placeholder placeholder;

    /** The liquid on the surface of the tile, may be null. */
    private Liquid liquid;

    /**
     * Method for post-move checking, if the tile is not empty,
     * forwards the checking in the specified direction.
     *
     * @param dir The direction of the move and thus the direction of the checking.
     */
    @Override
    public void Check(Direction dir) {
        if (placeholder != null)
            neighbours.get(dir).Check(dir);
    }

    /**
     * Releases the placeholder currently occupying this tile.
     */
    public void ReleaseObject() {
        placeholder = null;
    }

    /**
     * Returns whether the tile is empty, that is, there is no occupying placeholder (null).
     *
     * @return True, if the tile is empty, false if not.
     */
    protected boolean IsEmpty() {
        return placeholder == null;
    }

    /**
     * Initializes the placeholder occupying this tile at the beginning.
     *
     * @param obj The occupying placeholder.
     */
    public void InitializeObject(Placeholder obj) {
        placeholder = obj;
        placeholder.InitializeTile(this);
    }

    /**
     * Method for placing a liquid on this tile.
     *
     * @param liquid The liquid to be placed.
     */
    public void Place(Liquid liquid) {
        this.liquid = liquid;
    }

    /**
     * Getter for the coefficient of friction (COF) this tile currently has.
     * An empty tile has a COF of 1.
     *
     * @return The coefficient of friction.
     */
    public double GetFriction() {
        if (liquid == null) return 1;
        return liquid.GetFriction();
    }

    /**
     * Method for accepting a placeholder. If the tile is empty, it can move.
     * If it is occupied, the incoming placeholder pushes the other.
     *
     * @param obj  The incoming placeholder.
     * @param dir  The direction in which the placeholder is moving.
     * @param move The move during which this method is called.
     */
    @Override
    public void Accept(Placeholder obj, Direction dir, Move move) {
        if (IsEmpty()) {
            obj.SetTile(this);
            this.placeholder = obj;
        }
        else {
            obj.Push(placeholder, dir, move);
            if (IsEmpty()) {
                obj.SetTile(this);
                this.placeholder = obj;
            }
        }
    }

    /**
     * Returns the string representation of a tile,
     * which depends on its (subclass-specific) type and
     * current state, i.e. what placeholder and liquid are on it (NOT subclass-specific).
     * @return The string representation of a tile.
     */
    @Override
    public String ToString(){
        return TypeToString() + (liquid == null ? "-" : liquid.ToString()) +
                (placeholder == null ? "-" : placeholder.ToString());
    }

    /**
     * Returns the string representation of the type of normal tiles, that is, a "t".
     * @return The string representation.
     */
    protected String TypeToString(){
        return "t";
    }

}
