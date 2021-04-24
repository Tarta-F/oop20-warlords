package view.game;

import java.util.EnumMap;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import constants.PlayerType;
import controllers.Observer;
import view.ViewClose;
import view.ViewInterface;

/** 
 * This interface models the GameView.
 */
public interface GameView extends ViewClose, ViewInterface {

    /**
     * Method to Update the selected lane.
     * @param playerType PlayerType
     * @param index lane
     * @param next lane
     * */
    void updateSelectLane(PlayerType playerType, int index, int next);

    /**
     * Method to Update the selected unit.
     * @param playerType PlayerType
     * @param index lane
     * @param next lane
     * */
    void updateSelectUnit(PlayerType playerType, int index, int next);

    /**
     * Method to Update the Timer.
     * @param mins lane
     * @param seconds lane
     * */
    void updateTimer(int mins, int seconds);

    /**
     * Method to Update the player timer.
     * @param seconds timer
     * @param playerType PlayerType
     * */
    void updatePlayerTimer(int seconds, PlayerType playerType);

    /**
     * Method to update the players labels score.
     * 
     * @param player
     *      player whose score to update
     * @param score
     *      the current score of the player
     */
    void updateScorePlayer(PlayerType player, int score);

    /**
     * Method to set the Observer.
     * @param observer Controller
     * */
    void setObserver(Observer observer);

    /**
     * Method to draw a unit in the grid.
     * @param units EnumMap<UnitViewType, List<Pair<Integer, Integer>>>
     * */
    void show(EnumMap<UnitViewType, List<Pair<Integer, Integer>>> units);

    /**
     * Method to change scene or close the program at the end of the game.
     * @param player name
     * */
    void winnerBoxResult(String player);

    /**
     * 
     * @param scores 
     */
    void drawBoxResult(String scores);

}
