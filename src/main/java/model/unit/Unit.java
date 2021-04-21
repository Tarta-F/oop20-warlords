package model.unit;

import constants.PlayerType;

public interface Unit {

    /**
     * Get the TIME of RESPAWN of the unit.
     * @return unit TIMER
     * */
    int getWaitingTime();

    /**
     * Get the HEALTH of the unit. 
     * @return unit HP
     * */
    int getHp();

    /**
     * Get the MOVEMENT of the unit.
     * @return unit MOVMENT
     * */
    int getStep();

    /**
     * Get the RANGE of the unit.
     * @return unit RANGE
     * */
    int getRange();

    /**
     * Get the DAMAGE of the unit.
     * @return unit DMG
     * */
    int getDamage();

    /**
     * Unit take damage from another unit.
     * @param damage received
     * */
    void damage(int damage);

    /**
     * Get the PLAYER of the unit.
     * @return unit PLAYER
     * */
    PlayerType getPlayer();

    /**
     * Get the TYPE of the unit.
     * @return unit TYPE
     * */
    UnitType getUnitType();

    /**
     * Unit attack on another unit.
     * @param unit target
     * */
    void attack(Unit unit);

    /**
     * Status if the unit is ALIVE. 
     * @return status ALIVE
     * */
    boolean isAlive();

}
