package model;

import java.util.Map;
import java.util.Set;

public interface Lane {

    void addUnit(Unit u);
    Set<Unit> getUnitsAtPosition(int position);
    Map<Unit, Integer> getUnits();
    int getLenght();
    Integer getScore(PlayerType player);

    /**
     * Updates every unit in this lane with attack, score or move.
     * PROBLEMI :
     * -TODO Quando una truppa muore o fa score deve essere rimossa ma non può essere fatto nel ciclo
     */
    void update(); 

}
