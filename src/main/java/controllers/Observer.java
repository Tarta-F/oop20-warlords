package controllers;

import constants.PlayerType;

public interface Observer {
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
     * @return true if the unit has been spawned correctly
     */
    boolean spawnUnit(PlayerType playerType);

    /**
     * Destroys/stops data flows about the current game.
     */
    void stopGame();

    /**
     * @param player
     * @return The name of the player
     */
    String getPlayerName(PlayerType player);

}
