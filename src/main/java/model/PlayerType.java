package model;

/** 
 *Players with corresponding unit directions.
 */
public enum PlayerType {

    /**
     * The possible Player types.
     */
    PLAYER1(Direction.RIGHT), PLAYER2(Direction.LEFT);

    PlayerType(final Direction direction) {
        this.direction = direction;
    }

    private final Direction direction;

    /**
     * @return direction of this player's unit
     */
    Direction getDirection() {
        return this.direction;
    }
}
