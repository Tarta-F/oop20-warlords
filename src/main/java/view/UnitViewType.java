package view;

import java.util.concurrent.TimeUnit;

import constants.PlayerType;
import model.unit.UnitConstants;
import view.constants.ViewImages;

/**
 * This enum serves to distinguish the type of unit to be drawn and each element has his own image path.
 */
public enum UnitViewType {

    /**Swordsmen of player 1.*/
    SWORDSMEN_PLAYER1(ViewImages.SEP + "SwordsmenP1.png", TimeUnit.MILLISECONDS.toSeconds(UnitConstants.SWORDSMEN_TIMER), PlayerType.PLAYER1),
    /**Swordsmen of player 2.*/
    SWORDSMEN_PLAYER2(ViewImages.SEP + "SwordsmenP2.png", TimeUnit.MILLISECONDS.toSeconds(UnitConstants.SWORDSMEN_TIMER), PlayerType.PLAYER2),

    /**Spearmen of player 1.*/
    SPEARMEN_PLAYER1(ViewImages.SEP + "SpearmenP1.png", TimeUnit.MILLISECONDS.toSeconds(UnitConstants.SPEARMEN_TIMER), PlayerType.PLAYER1),
    /**Spearmen of player 2.*/
    SPEARMEN_PLAYER2(ViewImages.SEP + "SpearmenP2.png", TimeUnit.MILLISECONDS.toSeconds(UnitConstants.SPEARMEN_TIMER), PlayerType.PLAYER2),

    /**Archer of player 1.*/
    ARCHER_PLAYER1(ViewImages.SEP + "ArcherP1.png", TimeUnit.MILLISECONDS.toSeconds(UnitConstants.ARCHER_TIMER), PlayerType.PLAYER1),
    /**Archer of player 2.*/
    ARCHER_PLAYER2(ViewImages.SEP + "ArcherP2.png", TimeUnit.MILLISECONDS.toSeconds(UnitConstants.ARCHER_TIMER), PlayerType.PLAYER2);

    private final String path;
    private final long waitingTime;
    private final PlayerType player;

    UnitViewType(final String path, final long waitingTime, final PlayerType player) {
        this.path = path;
        this.waitingTime = waitingTime;
        this.player = player;
    }

    /**@return unit path. */
    public String getPath() {
        return this.path;
    }

    /**@return unit wainting time. */
    public int getWaitingTime() {
        return (int) this.waitingTime;
    }

    /**@return unit player. */
    public PlayerType getPlayer() {
        return this.player;
    }
}
