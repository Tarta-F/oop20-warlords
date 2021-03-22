package model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import utilities.Pair;

public interface Field {

    void addUnit(int laneIndex, Unit unit);
    List<Lane> getLanes();
    Optional<Integer> getScore(PlayerType player);
    Map<Unit, Pair<Integer, Integer>> getUnits();
    void update();

}

//PER IL CONTROLLER :
//Integer getSelectedLane(PlayerType player); 
//void upLane(PlayerType player);
//void downLane(PlayerType player);
