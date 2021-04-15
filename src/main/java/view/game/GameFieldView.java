package view.game;

import org.apache.commons.lang3.tuple.Pair;

import javafx.scene.layout.GridPane;
import view.UnitViewType;

/** 
 * This interface models the GameField used in the View using a GridPane.
 *
 */
public interface GameFieldView {

    /**
     * @return The Grid created by this Field
     */
    GridPane getGrid();

    /**
     * Add the unit at the player at the given position in the grid.
     * @param unit UnitType to add
     * @param position in which to place the unit
     */
    void add(UnitViewType unit, Pair<Integer, Integer> position);

    /**
     * Clear the Field from all the units contained.
     */
    void clear();
}
