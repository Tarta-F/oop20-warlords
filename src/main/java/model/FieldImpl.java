package model;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.tuple.Pair;
import constants.PlayerType;
import model.unit.Unit;

/**
 * Basic implementation of {@link Field}.
 */
public class FieldImpl implements Field {

    private final List<Lane> lanes;
    private final int cellsNumber;
    private final int laneNumber;

    private static final String MESSAGE_OUT_OF_FIELD = "The entered lane is out of the limits";

    /**
     * Creates a {@link Field} from the given dimensions.
     * 
     * @param cellsNumber
     *                  the number of cells for each lane
     * @param laneNumber
     *                  the number of lanes
     */
    public FieldImpl(final int cellsNumber, final int laneNumber) {
        this.cellsNumber = cellsNumber;
        this.laneNumber = laneNumber;
        this.lanes = Stream.generate(() -> cellsNumber)
                .limit(laneNumber)
                .map(LaneImpl::new)
                .collect(Collectors.toList());
    }

    /**
     * To subclass this method safely a {@link Unit} needs to be added to the specified {@link Lane} number.
     */
    @Override
    public void addUnit(final int laneIndex, final Unit unit) {
        if (laneIndex < 0 || laneIndex >= this.lanes.size()) {
            throw new IndexOutOfBoundsException(MESSAGE_OUT_OF_FIELD);
        }
        this.lanes.get(laneIndex).addUnit(unit);
    }

    @Override
    public final List<Lane> getLanes() {
        return Collections.unmodifiableList(this.lanes);
    }

    @Override
    public final int getScore(final PlayerType player) {
        return this.lanes.stream()
                .map(l -> l.getScore(player))
                .reduce(Integer::sum).orElse(0).intValue();
    }

    @Override
    public final Map<Unit, Pair<Integer, Integer>> getUnits() {
        return this.lanes.stream()
                .flatMap(l -> l.getUnits().entrySet().stream()
                        .map(e -> Pair.of(e.getKey(), Pair.of(e.getValue(), lanes.indexOf(l)))))
                .collect(Collectors.toUnmodifiableMap(Pair::getLeft, Pair::getRight));
    }

    @Override
    public final void update() {
        this.lanes.forEach(Lane::update);
    }

    @Override
    public final int getLaneNumber() {
        return this.laneNumber;
    }

    @Override
    public final int getCellsNumber() {
        return this.cellsNumber;
    }

    @Override
    public final void resetScore() {
        this.lanes.forEach(Lane::resetScore);
    }

}
