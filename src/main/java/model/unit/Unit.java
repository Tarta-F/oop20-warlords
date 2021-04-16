package model.unit;

import constants.PlayerType;

public interface Unit {

    /**@return unit TIME of RESPAWN*/
    int getWaitingTime();

    /**@return unit HP*/
    int getHP();

    /**@return unit RANGE attack*/
    int getRange();

    /**@return unit step ahead*/
    int getStep();

    /**@return unit DMG taken*/
    int getDamage();

    /**@param damage to be taken by this unit*/
    void damage(int damage);

    /**@return unit PLAYER*/
    PlayerType getPlayer();

    /**@return unit TYPE*/
    UnitType getUnitType();

    /**@param unit target*/
    void attack(Unit unit);

    /**@return if the unit is alive or not*/
    boolean isAlive();
}
