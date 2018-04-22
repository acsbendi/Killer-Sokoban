package common.model;

import java.util.ArrayList;
import java.util.List;

import common.util.Direction;

/**
 * Class representing a lever tile.
 * Can open a number of trap tiles.
 */
public class LeverTile extends Tile {

    /** Is the lever tile currently pressed?*/
    private boolean pressed = false;

    /** The trap tile that are wired/connected to this lever tile. */
    private List<TrapTile> trapTiles = new ArrayList<>();

    /**
     * Method for post-move checking, works the same way as a normal tile, plus if pressed,
     * also checks its trap tiles (may have just been pressed).
     *
     * @param dir The direction of the checking (and the preceding move).
     */
    @Override
    public void Check(Direction dir) {
        if (placeholder != null) {
            if (IsPressed()) {
                for (TrapTile tt : trapTiles)
                    tt.Check();
            }
            neighbours.get(dir).Check(dir);
        }
    }

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
        if (IsEmpty()) {
            obj.ArrivedAt(this);
            obj.SetTile(this);
        } else {
            obj.Push(placeholder, dir, move);
            if (IsEmpty()) {
                obj.ArrivedAt(this);
                obj.SetTile(this);
            }
        }
    }

    /**
     * Releases the placeholder currently occupying this tile, and if it was pressed,
     * it cease to be, also notifying the connected trap tiles about it.
     */
    @Override
    public void ReleaseObject() {
        if (IsPressed()) {
            for (TrapTile trapTile : trapTiles)
                trapTile.SignOff();
            Release();
        }
        placeholder = null;
    }

    /**
     * Method for activating, pressing the lever tile, notifies the connected trap tiles about it.
     */
    public void Press() {
        pressed = true;
        for (TrapTile trapTile : trapTiles)
            trapTile.SignOn();
    }

    /**
     * Wires a trap tile (door) to this lever tile.
     * That is, it will be able to be opened by this lever tile.
     * @param traptile The trap tile to wired.
     */
    public void AddTrapdoor(TrapTile traptile) {
        trapTiles.add(traptile);
    }

    /**
     * Returns whether the lever tile is pressed.
     * @return True if pressed, false if not.
     */
    private boolean IsPressed() {
        return pressed;
    }

    /**
     * The lever tile is not pressed anymore.
     */
    private void Release() {
        pressed = false;
    }


    /**
     * Returns the string representation of the type of lever tiles, that is, a "l".
     * @return The string representation.
     */
    @Override
    protected String TypeToString(){
        return "l";
    }
}
