package model.unit;

import constants.PlayerType;

/**
 *Unit Implementation.
 */
public class UnitImpl implements Unit {

    private int hp;
    private final int dmg;
    private final int range;
    private final int timer;
    private final int step;
    private boolean alive;
    private final UnitType unitType;
    private final PlayerType player;

    public UnitImpl(final UnitType unitType, final PlayerType player) {
        this.unitType = unitType;
        this.hp = unitType.getHealth();
        this.dmg = unitType.getDamage();
        this.range = unitType.getRange();
        this.timer = unitType.getTimer();
        this.step = unitType.getStep();
        this.player = player;
        this.alive = true;
    }

    /**
     * Get the TIME of RESPAWN of the unit.
     * @return unit TIMER
     * */
    @Override
    public int getWaitingTime() {
        return this.timer;
    }

    /**
     * Get the HEALTH of the unit. 
     * @return unit HP
     * */
    @Override
    public int getHP() {
        return this.hp;
    }

    /**
     * Get the MOVEMENT of the unit.
     * @return unit MOVMENT
     * */
    @Override
    public int getStep() {
        return this.step;
    }

    /**
     * Get the DAMAGE of the unit.
     * @return unit DMG
     * */
    @Override
    public int getDamage() {
        return this.dmg;
    }

    /**
     * Unit take damage from another unit.
     * @param damage received
     * */
    @Override
    public void damage(final int damage) {
        this.hp = this.hp - damage;
        if (this.hp <= 0) {
                this.alive = false;
            }
        }

    /**
     * Get the PLAYER of the unit.
     * @return unit PLAYER
     * */
    @Override
    public PlayerType getPlayer() {
        return this.player;
    }

    /**
     * Get the TYPE of the unit.
     * @return unit TYPE
     * */
    @Override
    public UnitType getUnitType() {
        return this.unitType;
    }

    /**
     * Unit attack on another unit.
     * @param unit target
     * */
    @Override
    public void attack(final Unit unit) {
        unit.damage(this.dmg);
    }

    /**
     * Get the RANGE of the unit.
     * @return unit RANGE
     * */
    @Override
    public int getRange() {
        return this.range;
    }

    /**
     * Status if the unit is ALIVE. 
     * @return status ALIVE
     * */
    @Override
    public boolean isAlive() {
        return this.alive;
    }

}
