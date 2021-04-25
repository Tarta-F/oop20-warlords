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
     * Set the View used by this Controller.
     * @param gameView the new GameView of this Controller
     */
    void setView(GameView gameView);

    /**
     * Set the variable that show if the {@link GameTimer}'s time is ended up.
     */
    void setTimerIsOver();

    /**
     * Controls if the game is over because a player wins.
     * @return true if there is a winner, false otherwise.
     */
    boolean isOver();

    /**
     * Controls if the timer is ended up.
     * @return true if timer is over, false otherwise.
     */
    boolean isTimerOver();

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

    /**
     * @param player
     * @return The name of the player
     */
    String getPlayerName(PlayerType player);

}
