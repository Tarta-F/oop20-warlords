package model;

//import java.util.List;
//import java.util.Map;
//import constants.ConstantsGame;

/**
 *Unit Implementation.
 */
public class UnitImpl implements Unit {

    private int hp, dmg, range, timer, step;
    private boolean alive;
    private UnitType unitType;
    private PlayerType player;
    private Direction direction;


    public UnitImpl(final UnitType unitType, final PlayerType player) {
        super();
        this.unitType = unitType;
        this.hp = unitType.getHealth();
        this.dmg = unitType.getDamage();
        this.range = unitType.getRange();
        this.timer = unitType.getTimer();
        this.step = unitType.getStep();
        this.player = player;
        this.direction = player.getDirection();
        this.alive = true;
    }

    public int getWaitingTime() {
        return this.timer;
    }

    public int getHP() {
        return this.hp;
    }

    public int getDamage() {
        return this.dmg;
    }

    public int getStep() {
        return step;
    }

    public PlayerType getPlayer() {
        return this.player;
    }

    public UnitType getUnitType() {
        return this.unitType;
    }

    @Override
    public void attack(final Unit unit) {
        unit.damage(this.dmg);
    }

    @Override
    public void damage(final int damage) {
        this.hp = this.hp - damage;
        if(this.hp <= 0) {
              //TODO notificare l'observer per cancellazione unita'
                this.alive = false;
            }
        }

    @Override
    public int getRange() {
        return this.range;
    }

    @Override
    public boolean isAlive() {
        return this.alive;
    }

}
