package model;

import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.apache.commons.lang3.tuple.Pair;

public interface Field {

    /**
     * @param laneIndex the lane in wich insert the unit
     * @param unit the unit to be inserted
     * @throws throws an Exception if the laneIndex doesn't exist
     */
    void addUnit(int laneIndex, Unit unit);

    /**
     * @return a list of the lanes of the field
     */
    List<Lane> getLanes();

    /**
     * @param player the player whose score to get
     * @return the numbers of units that have received the enemy base, if there are any
     */
    Optional<Integer> getScore(PlayerType player);

    /**
     * @return a map of the units with their corresponding position, rappresented by a pair (y= lane number)
     */
    Map<Unit, Pair<Integer, Integer>> getUnits();

    /**
     * Update all the lanes.
     */
    void update();

    /**
     * @return the number of lanes created
     */
    int getLaneNumber();

    /**
     * @return the lenght of each lane
     */
    int getCellsNumber();

}

//PER IL CONTROLLER :
//Integer getSelectedLane(PlayerType player); 
//void upLane(PlayerType player);
//void downLane(PlayerType player);
