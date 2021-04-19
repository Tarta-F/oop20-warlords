package view;

import constants.PlayerType;

import javafx.scene.input.KeyCode;

/**
 * The controls for playing the game.
 * For each player there are 5 controls: 
 * A and D(player 1), LEFT and RIGHT(player 2) for unit selection, 
 * W and S(player 1), UP and DOWN(player 2) for lane selection,
 * SPACE(player 1), ENTER(player 2) for unit spawn.
 */
public enum InputType {

    /**Moves up the lane selector for Player 1. */
    UP_LANE_1(PlayerType.PLAYER1, KeyCode.W),
    /**Moves down the lane selector for Player 1. */
    DOWN_LANE_1(PlayerType.PLAYER1, KeyCode.S),
    /**Moves left the unit selector for Player 1. */
    LEFT_UNIT_1(PlayerType.PLAYER1, KeyCode.A),
    /**Moves right the unit selector for Player 1. */
    RIGHT_UNIT_1(PlayerType.PLAYER1, KeyCode.D),
    /**Add the selected unit to the selected lane for Player 1. */
    ADD_UNIT_1(PlayerType.PLAYER1, KeyCode.SPACE),
    /**Moves up the lane selector for Player 2. */
    UP_LANE_2(PlayerType.PLAYER2, KeyCode.UP),
    /**Moves down the lane selector for Player 2. */
    DOWN_LANE_2(PlayerType.PLAYER2, KeyCode.DOWN),
    /**Moves left the unit selector for Player 2. */
    LEFT_UNIT_2(PlayerType.PLAYER2, KeyCode.LEFT),
    /**Moves right the unit selector for Player 2. */
    RIGHT_UNIT_2(PlayerType.PLAYER2, KeyCode.RIGHT),
    /**Add the selected unit to the selected lane for Player 2. */
    ADD_UNIT_2(PlayerType.PLAYER2, KeyCode.ENTER);

    private final PlayerType player;
    private final KeyCode key;

    InputType(final PlayerType player, final KeyCode key) {
        this.player = player;
        this.key = key;
    }

    /**
     * Get PLAYER. 
     * @return playerType player
     * */
    public PlayerType getPlayer() {
        return this.player;
    }

    /**
     * Get KEYCODE(Button from the keyboard).
     * @return keyCode key
     * */
    public KeyCode getKey() {
        return this.key;
    }

}
