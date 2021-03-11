package view;

import javafx.scene.input.KeyCode;
import model.PlayerType;

public enum InputType {

    UP_LANE_1(PlayerType.PLAYER1, KeyCode.W), 
    DOWN_LANE_1(PlayerType.PLAYER1, KeyCode.S), 
    LEFT_UNIT_1(PlayerType.PLAYER1, KeyCode.A), 
    RIGHT_UNIT_1(PlayerType.PLAYER1, KeyCode.D), 
    ADD_UNIT_1(PlayerType.PLAYER1, KeyCode.SPACE),
    UP_LANE_2(PlayerType.PLAYER2, KeyCode.UP), 
    DOWN_LANE_2(PlayerType.PLAYER2, KeyCode.DOWN), 
    LEFT_UNIT_2(PlayerType.PLAYER2, KeyCode.LEFT), 
    RIGHT_UNIT_2(PlayerType.PLAYER2, KeyCode.RIGHT), 
    ADD_UNIT_2(PlayerType.PLAYER2, KeyCode.ENTER);

    private final PlayerType player;
    private final KeyCode key;
    
    private InputType(PlayerType player, KeyCode key) {
         this.player = player;
        this.key = key;
    }
    
    public PlayerType getPlayer() {
        return this.player;
    }
    
    public KeyCode getKey(PlayerType player) {
        return this.key;
    }
    
//    if(keypressed == inputype.getkey) {
//        this.input = inputtype;
//        break;
//    }
}
