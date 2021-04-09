package view;

import constants.ViewConstants;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;

/** 
 * Class that models the Field with a GridPane of the given dimensions.
 */
public final class GameFieldView {

    private final GridPane gridPane = new GridPane();

    private static final int CELL_W = (int) ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_27);
    private static final int CELL_H = (int) ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_10);

    private final int nRow;
    private final int nCols;

    private final Map<UnitViewType, Image> unitImageTable = new HashMap<>();

    public GameFieldView(final int nRow, final int nCols) {
        this.nRow = nRow;
        this.nCols = nCols;
        this.createGrid();
        this.gridPane.setAlignment(Pos.CENTER);

        final Image groundImage = new Image(this.getClass().getResourceAsStream("/Ground.png"));
        gridPane.setBackground(new Background(new BackgroundImage(groundImage, BackgroundRepeat.REPEAT, 
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }

    private void createGrid() {
        for (int i = 0; i < this.nCols; i++) {
            for (int j = 0; j < this.nRow; j++) {
                //TODO RIEMPO LA LISTA CON IMAGEVIEW VUOTI, DA CAMBIARE
                final ImageView cell = new ImageView();
                cell.setFitWidth(CELL_W);
                cell.setFitHeight(CELL_H);
                GridPane.setConstraints(cell, i, j);
                this.gridPane.getChildren().add(cell);
            }
        }
    }

    /**
     * Method that gives the image corresponding to the unit , if it has not yet been requested it will be drawn.
     * @param unit image requested
     * @return The image corresponding to the input
     */
    private Image callCachedImage(final UnitViewType unit) {
        if (!this.unitImageTable.containsKey(unit)) {
            this.unitImageTable.put(unit, new Image(this.getClass().getResourceAsStream(unit.getPath())));
        }
        return this.unitImageTable.get(unit);
    }

    /**
     * @return The Grid created by this Field
     */
    public GridPane getGrid() {
        return this.gridPane;
    }

    /**
     * Add the unit at the player at the given position in the grid.
     * @param unit UnitType to add
     * @param position in which to place the unit
     */
    public void add(final UnitViewType unit, final Pair<Integer, Integer> position) {
        final ImageView unitView = new ImageView(this.callCachedImage(unit)); 

        unitView.setFitWidth(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_27));
        unitView.setFitHeight(ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_10));

        GridPane.setConstraints(unitView, position.getLeft(), position.getRight());
        gridPane.getChildren().add(unitView);
    }

    /**
     * Clear the Field from all the units contained.
     */
    public void clear() {
        this.gridPane.getChildren().clear();
        this.createGrid();
    }

}
