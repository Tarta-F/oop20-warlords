package view;

/**
 * This enum serves to distinguish the type of unit to be drawn and each element has his own image path.
 */
public enum UnitViewType {

    /**Swordsmen of player 1.*/
    SWORDSMEN_PLAYER1("/swordsman_1.png"),
    /**Swordsmen of player 2.*/
    SWORDSMEN_PLAYER2("/swordsman_2.png"),

    /**Spearmen of player 1.*/
    SPEARMEN_PLAYER1("/spearman_1.png"),
    /**Spearmen of player 2.*/
    SPEARMEN_PLAYER2("/spearman_2.png"),

    /**Archer of player 1.*/
    ARCHER_PLAYER1("/archer_1.png"),
    /**Archer of player 2.*/
    ARCHER_PLAYER2("/archer_2.png");

    private final String path;

    UnitViewType(final String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
