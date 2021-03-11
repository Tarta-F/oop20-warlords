package model;

public enum PlayerType {

    PLAYER1(Direction.RIGHT), PLAYER2(Direction.LEFT);

    PlayerType(Direction direction) {
        this.direction = direction;
    }

    private final Direction direction;

    Direction getDirection() {
        return this.direction;
    }
}
