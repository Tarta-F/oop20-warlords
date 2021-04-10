package view;

import constants.ViewConstants;

import java.util.EnumMap;

import org.apache.commons.lang3.tuple.Pair;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/** 
 * Class that models the Field with a GridPane of the given dimensions.
 */
public final class GameFieldViewImpl implements GameFieldView {

    private final GridPane gridPane = new GridPane();

    private static final int CELL_W = (int) ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_27);
    private static final int CELL_H = (int) ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_10);

    private final int nRow;
    private final int nCols;

    private final EnumMap<UnitViewType, Image> unitImageTable = new EnumMap<>(UnitViewType.class);

    public GameFieldViewImpl(final int nRow, final int nCols) {
        this.nRow = nRow;
        this.nCols = nCols;
        this.createGrid();
        this.gridPane.setAlignment(Pos.CENTER);
//        this.gridPane.setPrefSize(nCols * CELL_W, nRow * CELL_H);

        final Image groundImage = new Image(this.getClass().getResourceAsStream("/Ground.png"));
        //TODO
        final BackgroundSize bgsize = new BackgroundSize(nCols * CELL_W, CELL_H, false, false, false, false);
//        gridPane.setBackground(new Background(new BackgroundImage(groundImage, BackgroundRepeat.NO_REPEAT, 
//                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, bgsize)));
        gridPane.setBackground(new Background(new BackgroundFill(Color.BROWN, null, null)));
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
    @Override
    public GridPane getGrid() {
        return this.gridPane;
    }

    /**
     * Add the unit at the player at the given position in the grid.
     * @param unit UnitType to add
     * @param position in which to place the unit
     */
    @Override
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
    @Override
    public void clear() {
        this.gridPane.getChildren().clear();
        this.createGrid();
    }

}
