/**
 * Represents a Barge ship in the Battleship game.
 * <p>
 * A Barge is the smallest possible ship, occupying only one grid cell.
 * It has a fixed size of 1 and a fixed name ("Barca").
 * Since it consists of a single cell, its orientation (bearing) does not affect
 * how it is placed on the game board.
 * </p>
 */
package iscteiul.ista.battleship;

public class Barge extends Ship {
    /** The size of the Barge (always 1). */
    private static final Integer SIZE = 1;

    /** The name of the ship ("Barca"). */
    private static final String NAME = "Barca";

    /**
     * Constructs a new {@code Barge} object.
     *
     * @param bearing the orientation of the barge (included for consistency with other ships,
     *                though not functionally relevant since the barge occupies one cell)
     * @param pos     the position of the barge on the game grid (its only occupied cell)
     */
    public Barge(Compass bearing, IPosition pos) {
        super(Barge.NAME, bearing, pos);
        // A Barge occupies only one position — add that single position to the ship.
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
    }

    /**
     * Returns the size of the barge.
     *
     * @return the integer value {@code 1}, representing the size of the ship
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }
}
