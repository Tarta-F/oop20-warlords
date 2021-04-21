package view.game;

import java.util.EnumMap;
import java.util.stream.IntStream;
import org.apache.commons.lang3.tuple.Pair;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import view.UnitViewType;
import view.ViewFactory;
import view.ViewFactoryImpl;
import view.ViewResolution;
import view.constants.ViewConstants;

/** 
 * Basic implementation of {@link GameFieldView}.
 */
public final class GameFieldViewImpl implements GameFieldView {

    private static final String MESSAGE_OVER_ROWS = "Image exceed row limits";
    private static final String MESSAGE_OVER_COLUMNS = "Image exceed column limits";
    private static final String MESSAGE_NEGATIVE_DIMENSIONS = "Impossible create a field with negative dimensions";

    private final GridPane gridPane = new GridPane();

    private static final int CELL_W = (int) ViewResolution.screenResolutionWidth(30);
    private static final int CELL_H = (int) ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_10);

    private final int nRow;
    private final int nCols;

    private final Image ground;
    private final ViewFactory factory = new ViewFactoryImpl();

    private final EnumMap<UnitViewType, Image> unitImageTable = new EnumMap<>(UnitViewType.class);

    /**
     * Creates a {@link GameFieldView} with the given dimensions and background.
     * 
     * @param nRow
     *      number of rows of the field
     * @param nCols
     *      number of columns of the field
     * @param ground
     *      path for the file used as field background
     * @throws IllegalArgumentException
     *       if one of the dimension given is negative
     */
    public GameFieldViewImpl(final int nRow, final int nCols, final String ground) { //final Optional<Image> scenario
        if (nRow < 0 || nCols < 0) {
            throw new IllegalArgumentException(MESSAGE_NEGATIVE_DIMENSIONS);
        }
        this.nRow = nRow;
        this.nCols = nCols;
        this.createGrid();
        this.gridPane.setAlignment(Pos.CENTER);
        this.ground = new Image(this.getClass().getResourceAsStream(ground));

    }

    /**
     * Fills the {@link GridPane} with the number of {@link #nRow} and {@link #nCols}.
     */
    private void createGrid() {
        IntStream.range(0, this.nCols).forEach(c -> {
            IntStream.range(0, this.nRow).forEach(r -> {
                final ImageView cell = new ImageView(ground);
                cell.setFitWidth(CELL_W);
                cell.setFitHeight(CELL_H);
                GridPane.setConstraints(cell, c, r);
                this.gridPane.getChildren().add(cell);
            });
        });
    }

    /**
     * Method that gives the {@link Image} corresponding to the {@link UnitViewType} if it has not yet been 
     * requested it will be created.
     * 
     * @param unit
     *       unit requested
     * @return
     *       the {@link Image} corresponding to the input
     */
    private Image callCachedImage(final UnitViewType unit) {
        this.unitImageTable.putIfAbsent(unit, new Image(this.getClass().getResourceAsStream(unit.getPath())));
        return this.unitImageTable.get(unit);
    }

    /**
     * If the value is negative or greater than or equal to limit, then throw IndexOutOfBoundsException 
     * with the given message.
     * 
     * @param value 
     *      the value to check
     * @param limit 
     *      the limit that value can assume
     * @param message 
     *      the message to print if throws the exception
     * @throws IndexOutOfBoundsException 
     *      if the value doesn't fit in the limits
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
        this.checkOutOfBounds(x, nCols, MESSAGE_OVER_COLUMNS);
        this.checkOutOfBounds(y, nRow, MESSAGE_OVER_ROWS);

        final ImageView unitView = this.factory.createImageView(this.callCachedImage(unit), CELL_W, CELL_H); 
        GridPane.setConstraints(unitView, x, y);
        gridPane.getChildren().add(unitView);
    }

    @Override
    public void clear() {
        this.gridPane.getChildren().clear();
        this.createGrid();
    }

}
