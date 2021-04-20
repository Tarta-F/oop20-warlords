package view.game;

import org.apache.commons.lang3.tuple.Pair;

import javafx.scene.layout.GridPane;
import view.UnitViewType;

/** 
 * This interface models the GameField used in the {@link GameView} using a {@link GridPane}.
 */
public interface GameFieldView {

    /**
     * @return 
     *      the Grid created by this Field
     */
    GridPane getGrid();

    /**
     * Add the unit at the given position in the grid.
     * 
     * @param unit 
     *      {@link UnitViewType} to add
     * @param position 
     *      position in which to place the unit
     */
    void add(UnitViewType unit, Pair<Integer, Integer> position);

    /**
     * Clear the this field from all the units contained.
     */
    void clear();

}
