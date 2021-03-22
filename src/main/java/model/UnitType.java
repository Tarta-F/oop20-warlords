package model;

import constants.ConstantsGame;

/**
 * All unit types.
 * */
public enum UnitType {

    /**Swordsmen unit stats.*/
    SWORDSMEN(ConstantsGame.SWORDSMEN_HP, ConstantsGame.SWORDSMEN_DMG, ConstantsGame.SWORDSMEN_RANGE, ConstantsGame.SWORDSMEN_TIMER, ConstantsGame.STEP),

    /**Spearmen unit stats.*/
    SPEARMEN(ConstantsGame.SPEARMEN_HP, ConstantsGame.SPEARMEN_DMG, ConstantsGame.SPEARMEN_RANGE, ConstantsGame.SPEARMEN_TIMER, ConstantsGame.STEP),

    /**Archer unit stats.*/
    ARCHER(ConstantsGame.ARCHER_HP, ConstantsGame.ARCHER_DMG, ConstantsGame.ARCHER_RANGE, ConstantsGame.ARCHER_TIMER, ConstantsGame.STEP);


    private int health, damage, range, timer, step;

    UnitType(final int health, final int damage, final int range, final int timer, final int step) {
        this.health = health;
        this.damage = damage;
        this.range = range;
        this.timer = timer;
        this.step = step;
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
