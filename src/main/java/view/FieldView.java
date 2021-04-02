package view;

import constants.ViewConstants;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import model.PlayerType;
import model.UnitType;
import javafx.scene.layout.GridPane;

/** 
 * Class that models the Field with a GridPane of the given dimensions.
 */
public final class FieldView {

    private final GridPane gridPane = new GridPane();

    /**Taking screen size for the adaptation of the various elements of the view to the resolution of the screen.*/
    private static final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int SW = (int) SCREEN.getWidth();
    private static final int SH = (int) SCREEN.getHeight();

    private static final int CELL_W = SW / ViewConstants.DIVISOR_27;
    private static final int CELL_H = SH / ViewConstants.DIVISOR_10;

    private final Table<UnitType, PlayerType, Image> unitImageTable = HashBasedTable.create();

    public FieldView(final int nRow, final int nCols) {

        /**Creation of the grid.*/
        for (int i = 0; i < nCols; i++) {
            for (int j = 0; j < nRow; j++) {
                final ImageView ground = new ImageView();
                ground.setFitWidth(CELL_W);
                ground.setFitHeight(CELL_H);
                GridPane.setConstraints(ground, i, j);
                this.gridPane.getChildren().add(ground);
            }
        }
        this.gridPane.setAlignment(Pos.CENTER);

        // BACKGROUND
        final ImageView background = new ImageView(new Image(this.getClass().getResourceAsStream("/Ground.png")));
        background.setFitWidth(nCols * CELL_W);
        background.setFitHeight(nRow * CELL_H);

        this.gridPane.getChildren().add(background);

    }

    /**
     * Method that gives the image corresponding to the unit and the player needed, if it has not yet been requested
     *  it will be drawn.
     * @param unit image requested
     * @param player that owns the unit
     * @return The image corresponding to the input
     */
    private Image callCachedImage(final UnitType unit, final PlayerType player) {
        if (!this.unitImageTable.contains(unit, player)) {
            this.unitImageTable.put(unit, player, new Image(this.getClass().getResourceAsStream("/archer.png")));
        }
        return this.unitImageTable.get(unit, player);
    }

    /**
     * @return The Grid created by this Field
     */
    public GridPane getGrid() {
        return this.gridPane;
    }

    /**
     * Add the unit at the player at the given position in the grid.
     * @param unit unittype to add
     * @param player that owns the unit
     * @param position in which to place the unit
     */
    public void add(final UnitType unit, final PlayerType player, final Pair<Integer, Integer> position) {
        final ImageView unitView = new ImageView(this.callCachedImage(unit, player)); 

        unitView.setFitWidth(CELL_W);
        unitView.setFitHeight(CELL_H);

        GridPane.setConstraints(unitView, position.getLeft(), position.getRight());
        gridPane.getChildren().add(unitView);
    }

    /**
     * Clear the Field from all the units contained.
     */
    public void clear() {
        this.gridPane.getChildren().clear();
    }

}
