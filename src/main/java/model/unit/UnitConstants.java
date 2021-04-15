package model.unit;

public final  class UnitConstants {

    // SWORDSMEN
    /**HP of the unit type SWORDMEN. */
    public static final int SWORDSMEN_HP = 24;
    /**DMG(damage) of the unit type SWORDSMEN. */
    public static final int SWORDSMEN_DMG = 3;

    /**RANGE(attack range) of the unit type SWORDSMEN. */
      public static final int SWORDSMEN_RANGE = 1;

    /**TIMER(time for re_spawn) of the unit type SWORDSMEN. */
      public static final int SWORDSMEN_TIMER = 4000;

    // SPEARMEN
    /**HP of the unit type SPEARMEN. */
    public static final int SPEARMEN_HP = 20;

    /**DMG(damage) of the unit type SPEARMEN. */
      public static final int SPEARMEN_DMG = 4;

    /**RANGE(attack range) of the unit type SPEARMEN. */
    public static final int SPEARMEN_RANGE = 2;

    /**TIMER(time for re_spawn) of the unit type SPEARMEN. */
    public static final int SPEARMEN_TIMER = 5000;


    // ARCHER
    /**HP of the unit type ARCHER. */
    public static final int ARCHER_HP = 15;

    /**DMG(damage) of the unit type ARCHER. */
    public static final int ARCHER_DMG = 2;

    /**RANGE(attack range) of the unit type ARCHER. */
    public static final int ARCHER_RANGE = 5;

    /**TIMER(time for re_spawn) of the unit type ARCHER. */
    public static final int ARCHER_TIMER = 6000;

    //Da metterne altri, se si vuole implementare il movimento diverso per tutte le unita'
    /**Movement of the unit. */
    public static final int STEP = 1;

    private UnitConstants() {
    }
}
