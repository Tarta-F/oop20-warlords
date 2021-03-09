package model;

public interface Unit /*extends Entity*/ {

    void movement();
    void spawn();
    void death();
    int getHP();
    int getUnitSpeed();
    void setSpeed(int speed);
    void setDamage(int damage);
   // void setPath(List<MapTile> path);
    UnitType getUnitType();
    void despawn();
}




/* prova */


/* prova 2*/

/* prova 4*/