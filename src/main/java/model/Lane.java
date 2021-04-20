package model;

import java.util.Map;
import java.util.Set;

import constants.PlayerType;
import model.unit.Unit;

/**
 * Route where {@link Unit} walk and fight to reach the opponent {@link PlayerType}.
 */
public interface Lane {

    /**
     * @param unit 
     *      {@link Unit} to be added
     */
    void addUnit(Unit unit);

    /**
     * @param position 
     *      the position for which the {@link Unit}l are required
     * @return 
     *      the {@link Set} of the {@link Unit} in this position
     * @throws 
     *      {@link IllegalArgumentException} in case of the position is out of the lane
     */
    Set<Unit> getUnitsAtPosition(int position);

    /**
     * @return 
     *      a map of {@link Unit} with the corresponding position in this lane
     */
    Map<Unit, Integer> getUnits();

    /**
     * @return 
     *      the lenght of the lane
     */
    int getLenght();

    /**
     * @param player 
     *      the player whose score to get
     * @return 
     *      the numbers of units that have received the enemy base by this lane, if there are any
     */
    Integer getScore(PlayerType player);

    /**
     * Updates every {@link Unit} in this lane with attack, score or move.
     */
    void update();

    /**
     * Reset the score of the players earned in this {@link Lane}.
     */
    void resetScore(); 

}
