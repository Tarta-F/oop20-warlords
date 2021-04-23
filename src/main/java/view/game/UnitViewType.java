package view.game;

import java.util.concurrent.TimeUnit;

import constants.PlayerType;
import model.unit.UnitConstants;
import view.constants.ResourcesConstants;

/**
 * This enum serves to distinguish the type of unit to be drawn and each element has his own image path.
 */
public enum UnitViewType {

    /**SWORDSMEN of player 1.*/
    SWORDSMEN_PLAYER1(ResourcesConstants.P1_SWORDSMEN, TimeUnit.MILLISECONDS.toSeconds(UnitConstants.SWORDSMEN_TIMER), PlayerType.PLAYER1),
    /**SWORDSMEN of player 2.*/
    SWORDSMEN_PLAYER2(ResourcesConstants.P2_SWORDSMEN, TimeUnit.MILLISECONDS.toSeconds(UnitConstants.SWORDSMEN_TIMER), PlayerType.PLAYER2),

    /**SPEARMEN of player 1.*/
    SPEARMEN_PLAYER1(ResourcesConstants.P1_SPEARMEN, TimeUnit.MILLISECONDS.toSeconds(UnitConstants.SPEARMEN_TIMER), PlayerType.PLAYER1),
    /**SPEARMEN of player 2.*/
    SPEARMEN_PLAYER2(ResourcesConstants.P2_SPEARMEN, TimeUnit.MILLISECONDS.toSeconds(UnitConstants.SPEARMEN_TIMER), PlayerType.PLAYER2),

    /**ARCHER of player 1.*/
    ARCHER_PLAYER1(ResourcesConstants.P1_ARCHER, TimeUnit.MILLISECONDS.toSeconds(UnitConstants.ARCHER_TIMER), PlayerType.PLAYER1),
    /**ARCHER of player 2.*/
    ARCHER_PLAYER2(ResourcesConstants.P2_ARCHER, TimeUnit.MILLISECONDS.toSeconds(UnitConstants.ARCHER_TIMER), PlayerType.PLAYER2);

    private final String path;
    private final long waitingTime;
    private final PlayerType player;

    UnitViewType(final String path, final long waitingTime, final PlayerType player) {
        this.path = path;
        this.waitingTime = waitingTime;
        this.player = player;
    }

    /**
     * Method to get the PATH of the unit.
     * @return unit path
     *  */
    public String getPath() {
        return this.path;
    }

    /**
     * Method to get the WAITING TIME of the unit.
     * @return unit waiting time
     * */
    public int getWaitingTime() {
        return (int) this.waitingTime;
    }

    /**
     * Method to get the PLAYERTYPE of the unit.
     * @return unit player 
     * */
    public PlayerType getPlayer() {
        return this.player;
    }

}
