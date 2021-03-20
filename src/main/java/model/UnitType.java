package model;

import constants.GameConstants;

/**
 * All unit types.
 * */
public enum UnitType {

    /**Swordsmen unit stats.*/
    SWORDSMEN(GameConstants.SWORDSMEN_HP, GameConstants.SWORDSMEN_DMG, GameConstants.SWORDSMEN_RANGE, GameConstants.SWORDSMEN_TIMER),

    /**Spearmen unit stats.*/
    SPEARMEN(GameConstants.SPEARMEN_HP, GameConstants.SPEARMEN_DMG, GameConstants.SPEARMEN_RANGE, GameConstants.SPEARMEN_TIMER),

    /**Archer unit stats.*/
    ARCHER(GameConstants.ARCHER_HP, GameConstants.ARCHER_DMG, GameConstants.ARCHER_RANGE, GameConstants.ARCHER_TIMER);


    private int health, damage, range, timer;

    UnitType(final int health, final int damage, final int range, final int timer) {
        this.health = health;
        this.damage = damage;
        this.range = range;
        this.timer = timer;
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
}
