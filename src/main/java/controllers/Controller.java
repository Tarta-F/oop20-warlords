package controllers;

import model.PlayerType;

public interface Controller {

    /**
     * Select the next lane selected by a player.
     * @param playerType P1 or P2
     */
    void controlNextLane(PlayerType playerType);

    /**
     * Select the previous lane selected by a player.
     * @param playerType
     */
    void controlPrevLane(PlayerType playerType);

    /**
     * Select the next unit selected by a player.
     * @param playerType 
     */
    void controlNextUnit(PlayerType playerType);

    /**
     * Select the previous unit selected by a player.
     * @param playerType
     */
    void controlPrevUnit(PlayerType playerType);

    /**
     * Deploy a unit in the lane currently selected by the player.
     * @param playerType
     */
    void spawnUnit(PlayerType playerType);

    /*
     * Controls if the game is over.
     * @return true if the game is over
     */
    boolean isOver();

    /**
     * Get the score of the player.
     * @param player 
     * @return the score of the player
     */
    int getScore(PlayerType player);

}
