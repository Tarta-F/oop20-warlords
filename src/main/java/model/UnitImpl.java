package model;

import java.util.Optional;
//import java.util.List;
//import java.util.Map;
//import constants.ConstantsGame;

/**
 *Unit Implementation.
 */
public class UnitImpl implements Unit {

    private int hp, dmg, range, timer;
    //private boolean alive; (metterlo qui, o farlo gestire alla field)
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
        this.player = player;
        this.direction = player.getDirection();
        //this.alive = false;
    }

    public void walk() {
        //metodo per il movimento
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
        //metodo per il movimento
        return 0;
    }

    public PlayerType getPlayer() {
        return this.player;
    }

    public UnitType getUnitType() {
        return this.unitType;
    }

    public Optional<Unit> getTarget() {
        //da settare in base alla tile
        return null;
    }

}
