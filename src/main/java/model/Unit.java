package model;

import java.util.Optional;
//import java.util.Map;(Bisogna creare la map tile se la utiliziamo)
//import java.util.List;

public interface Unit {
    
    //Metodo da implementare?
    //void setPath(List<MapTile> path);
    
    /**Method to implement the unit movement*/
    void walk();
    
    /**@return unit time re_spawn*/
    int getWaitingTime();
    
    /**@return unit HP*/
    int getHP();
    
    /**@return unit step ahead*/
    int getStep();
    
    /**@return unit DMG taken*/
    int getDamage();
    
    /**@return unit player ID*/
    PlayerType getPlayer();
    
    /**@return unit type*/
    UnitType getUnitType();
    
    /**@return a possible target next the unit*/
    Optional<Unit> getTarget();
}