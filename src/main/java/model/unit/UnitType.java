package model.unit;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * All unit types.
 * */
public enum UnitType {

    /**SWORDSMEN unit and his statistics. */
    SWORDSMEN(UnitConstants.SWORDSMEN_HP, UnitConstants.SWORDSMEN_DMG, UnitConstants.SWORDSMEN_RANGE, 
            UnitConstants.SWORDSMEN_TIMER, UnitConstants.SWORDSMEN_STEP),

    /**SPEARMEN unit and his statistics. */
    SPEARMEN(UnitConstants.SPEARMEN_HP, UnitConstants.SPEARMEN_DMG, UnitConstants.SPEARMEN_RANGE, 
            UnitConstants.SPEARMEN_TIMER, UnitConstants.SPEARMEN_STEP),

    /**ARCHER unit and his statistics. */
    ARCHER(UnitConstants.ARCHER_HP, UnitConstants.ARCHER_DMG, UnitConstants.ARCHER_RANGE, 
            UnitConstants.ARCHER_TIMER, UnitConstants.ARCHER_STEP);

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
    public static int getMaxWaitingTime() {
        return (int) TimeUnit.MILLISECONDS.toSeconds(Arrays.stream(values())
                .mapToInt(u -> u.getTimer())
                .max().getAsInt());
    }

    public static int getUnitNumber() {
        return UnitType.values().length;
    }

    /**
     * Get the HEALTH of the unit. 
     * @return unit HP
     * */
    public int getHealth() {
        return this.health;
    }

    /**
     * Get the DAMAGE of the unit.
     * @return unit DMG
     * */
    public int getDamage() {
        return this.damage;
    }

    /**
     * Get the RANGE of the unit.
     * @return unit RANGE
     * */
    public int getRange() {
        return this.range;
    }

    /**
     * Get the TIME of RESPAWN of the unit.
     * @return unit TIMER
     * */
    public int getTimer() {
        return this.timer;
    }

    /**
     * Get the MOVEMENT of the unit.
     * @return unit MOVMENT
     * */
    public int getStep() {
        return this.step;
    }

}
