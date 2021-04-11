package view;

import constants.ViewConstants;
import constants.ViewImages;

import java.util.EnumMap;
import java.util.Optional;
import java.util.stream.IntStream;

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
public final class GameFieldViewImpl implements GameFieldView {

    private static final String MESSAGE_OVER_ROWS = "Image exceed row limits";

    private static final String MESSAGE_OVER_COLUMNS = "Image exceed column limits";

    private static final String MESSAGE_NEGATIVE_DIMENSIONS = "Impossible create a field with negative dimensions";

    private final GridPane gridPane = new GridPane();

    private static final int CELL_W = (int) ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_27);
    private static final int CELL_H = (int) ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_10);

    private final int nRow;
    private final int nCols;

    private final EnumMap<UnitViewType, Image> unitImageTable = new EnumMap<>(UnitViewType.class);

    public GameFieldViewImpl(final int nRow, final int nCols, final Optional<Image> scenario) {
        if (nRow < 0 || nCols < 0) {
            throw new IllegalArgumentException(MESSAGE_NEGATIVE_DIMENSIONS);
        }
        this.nRow = nRow;
        this.nCols = nCols;
        this.createGrid();
        this.gridPane.setAlignment(Pos.CENTER);
//        this.gridPane.setPrefSize(nCols * CELL_W, nRow * CELL_H);

        final BackgroundSize bgSize = new BackgroundSize(nCols * CELL_W, nRow * CELL_H, false, false, false, false);
        final BackgroundImage bgImage = 
                new BackgroundImage(scenario.orElseGet(() -> new Image(this.getClass().getResourceAsStream(ViewImages.GROUND_2))),
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER, bgSize);
        this.gridPane.setBackground(new Background(bgImage));
    }

    /**
     * Fills the Grid with the number of row and columns given by the fields of this objects.
     */
    private void createGrid() {
        IntStream.range(0, this.nCols).forEach(c -> {
            IntStream.range(0, this.nRow).forEach(r -> {
                final ImageView cell = new ImageView();
                cell.setFitWidth(CELL_W);
                cell.setFitHeight(CELL_H);
                GridPane.setConstraints(cell, c, r);
                this.gridPane.getChildren().add(cell);
            });
        });
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
     * If the value is negative or greater than or equal to limit, then throw IndexOutOfBoundsException 
     * with the given message.
     * @param value the value to check
     * @param limit the limit that value can assume
     * @param message the message to print if throws the exception
     * 
     */
    private void checkOutOfBounds(final Integer value, final Integer limit, final String message) {
        if (value < 0 || value > limit - 1) {
            throw new IndexOutOfBoundsException(message);
        }
    }

    @Override
    public GridPane getGrid() {
        return this.gridPane;
    }

    @Override
    public void add(final UnitViewType unit, final Pair<Integer, Integer> position) {
        final int x = position.getLeft();
        final int y = position.getRight();
        final ImageView unitView = new ImageView(this.callCachedImage(unit)); 

        this.checkOutOfBounds(x, nCols, MESSAGE_OVER_COLUMNS);
        this.checkOutOfBounds(y, nRow, MESSAGE_OVER_ROWS);

        unitView.setFitWidth(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_27));
        unitView.setFitHeight(ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_10));

        GridPane.setConstraints(unitView, x, y);
        gridPane.getChildren().add(unitView);
    }

    @Override
    public void clear() {
        this.gridPane.getChildren().clear();
        this.createGrid();
    }

}
