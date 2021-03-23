package model;

import java.util.Map;
import java.util.Set;

public interface Lane {

    void addUnit(Unit u);
    Set<Unit> getUnitsAtPosition(int position);
    Map<Unit, Integer> getUnits();
    int getLenght();
    Integer getScore(PlayerType player);
    void update(); 

}
