package controllers;

import java.util.Optional;

import constants.PlayerType;
import view.game.GameView;

public interface Controller {

    /**
     * Get the score of the player.
     * @param player 
     * @return the score of the player
     */
    int getScore(PlayerType player);

    /**
     * Get the View used by this Controller.
     * @return the GameView of this Controller
     */
    GameView getView();

    /**
     * Set the variable that show if the {@link GameTimer}'s time is ended up.
     */
    void setTimerIsOver();

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

    /**
     * Controls if the game is over.
     * @return true if the game is over
     */
    boolean isOver();
    //TODO
    void timeOut();

    /**
     * Updates the model and print the result on the GameView.
     */
    void update();

    /**
     * Get the player has Optional.
     * @return an optional that, if not empty, contains the winner
     */
    Optional<PlayerType> getWinner();

    /**
     * Destroys/stops data flows about the current game.
     */
    void stopGame();

}
