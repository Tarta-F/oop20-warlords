package model;

import java.util.Optional;

public interface Unit /*extends Entity*/ {
    int getWaitingTime();
    int getHP();
    int getStep();
    int getDamage();
    PlayerType getPlayer();
    UnitType getUnitType();
    Optional<Unit> getTarget();
    void walk();
   // void setPath(List<MapTile> path);
}