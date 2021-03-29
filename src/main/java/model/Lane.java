package model;

import java.util.Map;
import java.util.Set;

public interface Lane {

    /**
     * @param unit unit to be added
     */
    void addUnit(Unit unit);

    /**
     * @param position 
     * @return the set of the units in this position
     * @throws IllegalArgumentException in case of the position is out of the lane
     */
    Set<Unit> getUnitsAtPosition(int position);

    /**
     * @return a map of unit with the corresponding position in this lane
     */
    Map<Unit, Integer> getUnits();

    /**
     * @return the lenght of the lane
     */
    int getLenght();

    /**
     * @param player the player whose score to get
     * @return the numbers of units that have received the enemy base by this lane, if there are any
     */
    Integer getScore(PlayerType player);

    /**
     * Updates every unit in this lane with attack, score or move.
     * PROBLEMI :
     * -TODO Quando una truppa muore o fa score deve essere rimossa ma non può essere fatto nel ciclo
     */
    void update(); 

}
