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

    @Override
    public final int getWaitingTime() {
        return this.timer;
    }

    @Override
    public final int getHp() {
        return this.hp;
    }

    @Override
    public final int getStep() {
        return this.step;
    }

    @Override
    public final int getRange() {
        return this.range;
    }

    @Override
    public final int getDamage() {
        return this.dmg;
    }

    @Override
    public final void damage(final int damage) {
        this.hp = this.hp - damage;
        if (this.hp <= 0) {
                this.alive = false;
            }
        }

    @Override
    public final PlayerType getPlayer() {
        return this.player;
    }

    @Override
    public final UnitType getUnitType() {
        return this.unitType;
    }

    @Override
    public final void attack(final Unit unit) {
        unit.damage(this.dmg);
    }

    @Override
    public final boolean isAlive() {
        return this.alive;
    }

}
