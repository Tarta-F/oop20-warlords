package controllers;

import model.PlayerType;

public interface Controller {
    void controlLaneUp(PlayerType playerType);
    void controlLaneDown(PlayerType playerType);
    void controlNextUnit(PlayerType playerType);
    void controlPrevUnit(PlayerType playerType);
    void spawnUnit(PlayerType playerType);
}
