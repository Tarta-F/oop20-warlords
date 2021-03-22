package constants;

/**
 * This class contain the various constants that we use in the game.
 */
public final class GameConstants {

    // SWORDSMEN
    /**HP of the unit type SWORDMEN. */
    public static final int SWORDSMEN_HP = 24;
    /**DMG(damage) of the unit type SWORDSMEN. */
    public static final int SWORDSMEN_DMG = 3;

    /**RANGE(attack range) of the unit type SWORDSMEN. */
      public static final int SWORDSMEN_RANGE = 1;

    /**TIMER(time for re_spawn) of the unit type SWORDSMEN. */
      public static final int SWORDSMEN_TIMER = 1000;

    // SPEARMEN
    /**HP of the unit type SPEARMEN. */
    public static final int SPEARMEN_HP = 20;

    /**DMG(damage) of the unit type SPEARMEN. */
      public static final int SPEARMEN_DMG = 4;

    /**RANGE(attack range) of the unit type SPEARMEN. */
    public static final int SPEARMEN_RANGE = 2;

    /**TIMER(time for re_spawn) of the unit type SPEARMEN. */
    public static final int SPEARMEN_TIMER = 1000;


    // ARCHER
    /**HP of the unit type ARCHER. */
    public static final int ARCHER_HP = 15;

    /**DMG(damage) of the unit type ARCHER. */
    public static final int ARCHER_DMG = 2;

    /**RANGE(attack range) of the unit type ARCHER. */
    public static final int ARCHER_RANGE = 5;

    /**TIMER(time for re_spawn) of the unit type ARCHER. */
    public static final int ARCHER_TIMER = 1000;

    /** Lane number for testing. */
    public static final int ONE_LANE = 1;

    /** Lane number for easy mode. */
    public static final int THREE_LANES = 3;

    /** Lane number for normal mode. */
    public static final int FOUR_LANES = 4;

    /** Lane number for hard mode. */
    public static final int FIVE_LANES = 5;

    /** Cell number for each lane. */
    public static final int CELLS_NUM = 15;

  //Da metterne altri, se si vuole implementare il movimento diverso per tutte le unita'
    /**Movement of the unit. */
    public static final int STEP = 1;

    private GameConstants() { }

<<<<<<< HEAD
    
=======
    //prova
>>>>>>> cbc9d00b8905a4b5dc71625a1b4c40e6462cd1c2
}
