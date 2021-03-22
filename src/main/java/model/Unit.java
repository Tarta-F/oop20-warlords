package model;
//import java.util.Map;(Bisogna creare la map tile se la utiliziamo)
//import java.util.List;

public interface Unit {

    //Metodo da implementare?
    //void setPath(List<MapTile> path);

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
}
