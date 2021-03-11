package model;

import java.util.List;
import java.util.Optional;

public interface Field {
    void addUnit(int laneIndex, Unit unit);
    List<Lane> getLanes();
    Optional<Integer> getScore(PlayerType player);

//          PER IL CONTROLLER :
//  Integer getSelectedLane(PlayerType player); 
//  void upLane(PlayerType player);
//  void downLane(PlayerType player);
}
