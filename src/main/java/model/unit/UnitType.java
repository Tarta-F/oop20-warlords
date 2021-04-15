package model.unit;

import java.util.Arrays;

/**
 * All unit types.
 * */
public enum UnitType {

    /**Swordsmen unit stats.*/
    SWORDSMEN(UnitConstants.SWORDSMEN_HP, UnitConstants.SWORDSMEN_DMG, UnitConstants.SWORDSMEN_RANGE, UnitConstants.SWORDSMEN_TIMER, UnitConstants.STEP),

    /**Spearmen unit stats.*/
    SPEARMEN(UnitConstants.SPEARMEN_HP, UnitConstants.SPEARMEN_DMG, UnitConstants.SPEARMEN_RANGE, UnitConstants.SPEARMEN_TIMER, UnitConstants.STEP),

    /**Archer unit stats.*/
    ARCHER(UnitConstants.ARCHER_HP, UnitConstants.ARCHER_DMG, UnitConstants.ARCHER_RANGE, UnitConstants.ARCHER_TIMER, UnitConstants.STEP);


    private int health;
    private int damage;
    private int range;
    private int timer;
    private int step;

    UnitType(final int health, final int damage, final int range, final int timer, final int step) {
        this.health = health;
        this.damage = damage;
        this.range = range;
        this.timer = timer;
        this.step = step;
    }

    /** 
     * Get the max waiting time of this enum values.
     * @return the max waiting time 
     */
    public static int getMaxWaintingTime() {
        return Arrays.stream(values())
                .mapToInt(u -> u.getTimer())
                .max().getAsInt();
    }

    /**@return unit HP*/
    public int getHealth() {
        return this.health;
    }

    /**@return unit DMG*/
    public int getDamage() {
        return this.damage;
    }

    /**@return unit RANGE*/
    public int getRange() {
        return this.range;
    }

    /**@return unit TIMER*/
    public int getTimer() {
        return this.timer;
    }

    /**@return unit movement*/
    public int getStep() {
        return this.step;
    }
}
