package view;

import java.util.concurrent.TimeUnit;

import constants.GameConstants;
import constants.ViewImages;

/**
 * This enum serves to distinguish the type of unit to be drawn and each element has his own image path.
 */
public enum UnitViewType {

    /**Swordsmen of player 1.*/
    SWORDSMEN_PLAYER1(ViewImages.SEP + "SwordsmenP1.png", TimeUnit.MILLISECONDS.toSeconds(GameConstants.SWORDSMEN_TIMER)),
    /**Swordsmen of player 2.*/
    SWORDSMEN_PLAYER2(ViewImages.SEP + "SwordsmenP2.png", TimeUnit.MILLISECONDS.toSeconds(GameConstants.SWORDSMEN_TIMER)),

    /**Spearmen of player 1.*/
    SPEARMEN_PLAYER1(ViewImages.SEP + "SpearmenP1.png", TimeUnit.MILLISECONDS.toSeconds(GameConstants.SPEARMEN_TIMER)),
    /**Spearmen of player 2.*/
    SPEARMEN_PLAYER2(ViewImages.SEP + "SpearmenP2.png", TimeUnit.MILLISECONDS.toSeconds(GameConstants.SPEARMEN_TIMER)),

    /**Archer of player 1.*/
    ARCHER_PLAYER1(ViewImages.SEP + "ArcherP1.png", TimeUnit.MILLISECONDS.toSeconds(GameConstants.ARCHER_TIMER)),
    /**Archer of player 2.*/
    ARCHER_PLAYER2(ViewImages.SEP + "ArcherP2.png", TimeUnit.MILLISECONDS.toSeconds(GameConstants.ARCHER_TIMER));

    private final String path;
    private final long waitingTime;

    UnitViewType(final String path, final long waitingTime) {
        this.path = path;
        this.waitingTime = waitingTime;
    }

    /**@return unit path. */
    public String getPath() {
        return this.path;
    }

    /**@return unit wainting time. */
    public int getWaitingTime() {
        return (int) this.waitingTime;
    }
}
