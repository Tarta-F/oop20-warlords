package view;

import constants.ViewImages;

/**
 * This enum serves to distinguish the type of unit to be drawn and each element has his own image path.
 */
public enum UnitViewType {

    /**Swordsmen of player 1.*/
    SWORDSMEN_PLAYER1(ViewImages.SEP + "SwordsmenP1.png"),
    /**Swordsmen of player 2.*/
    SWORDSMEN_PLAYER2(ViewImages.SEP + "SwordsmenP2.png"),

    /**Spearmen of player 1.*/
    SPEARMEN_PLAYER1(ViewImages.SEP + "SpearmenP1.png"),
    /**Spearmen of player 2.*/
    SPEARMEN_PLAYER2(ViewImages.SEP + "SpearmenP2.png"),

    /**Archer of player 1.*/
    ARCHER_PLAYER1(ViewImages.SEP + "ArcherP1.png"),
    /**Archer of player 2.*/
    ARCHER_PLAYER2(ViewImages.SEP + "ArcherP2.png");

    private final String path;

    UnitViewType(final String path) {
        this.path = path;
    }

    /**@return unit path. */
    public String getPath() {
        return this.path;
    }
}
