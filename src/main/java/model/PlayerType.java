package model;

public enum PlayerType {

    PLAYER1(Direction.DX), PLAYER2(Direction.SX);

    PlayerType(Direction direction) {
        this.direction = direction;
    }

    private final Direction direction;

    Direction getDirection() {
        return this.direction;
    }
}
