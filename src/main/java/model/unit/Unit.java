package model.unit;

import constants.PlayerType;

public interface Unit {

    /**@return unit time re_spawn*/
    int getWaitingTime();

    /**@return unit HP*/
    int getHP();

    /**@return unit range attack*/
    int getRange();

    /**@return unit step ahead*/
    int getStep();

    /**@return unit DMG taken*/
    int getDamage();

    /**@param damage to be taken by this unit*/
    void damage(int damage);

    /**@return unit player ID*/
    PlayerType getPlayer();

    /**@return unit type*/
    UnitType getUnitType();

    /**@param unit target hitten*/
    void attack(Unit unit);

    /**@return if the unit is alive*/
    boolean isAlive();
}
