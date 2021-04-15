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
    //private final Direction direction;


    public UnitImpl(final UnitType unitType, final PlayerType player) {
        super();
        this.unitType = unitType;
        this.hp = unitType.getHealth();
        this.dmg = unitType.getDamage();
        this.range = unitType.getRange();
        this.timer = unitType.getTimer();
        this.step = unitType.getStep();
        this.player = player;
        //this.direction = player.getDirection();
        this.alive = true;
    }

    /**@return time of respawn.*/
    public int getWaitingTime() {
        return this.timer;
    }

    /**@return health.*/
    public int getHP() {
        return this.hp;
    }

    /**@return damage.*/
    public int getDamage() {
        return this.dmg;
    }

    /**@return step.*/
    public int getStep() {
        return this.step;
    }

    /**@return player.*/
    public PlayerType getPlayer() {
        return this.player;
    }

    /**@return unit type.*/
    public UnitType getUnitType() {
        return this.unitType;
    }

    /**@Override deal damage to another unit.
     * @param unit Unit*/
    public void attack(final Unit unit) {
        unit.damage(this.dmg);
    }

    /**@Override take damage from another unit
     * @param damage int*/
    public void damage(final int damage) {
        this.hp = this.hp - damage;
        if (this.hp <= 0) {
              //TODO notificare l'observer per cancellazione unita'
                this.alive = false;
            }
        }

    /**@return unit range.*/
    public int getRange() {
        return this.range;
    }

    /**@return status alive.*/
    public boolean isAlive() {
        return this.alive;
    }

}
