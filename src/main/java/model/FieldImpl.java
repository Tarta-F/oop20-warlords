package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import utilities.Pair;

public class FieldImpl implements Field {

    private final List<Lane> lanes;

    public FieldImpl(final int cellsNumber, final int laneNumber) {
        this.lanes = Stream.generate(() -> cellsNumber)
                .limit(laneNumber)
                .map(LaneImpl::new)
                .collect(Collectors.toList());
    }

    /**
     * @param laneIndex the lane in wich insert the unit
     * @param unit the unit to be inserted
     */
    @Override
    public void addUnit(final int laneIndex, final Unit unit) {
        if (laneIndex < 0 || laneIndex >= this.lanes.size()) {
            throw new IllegalArgumentException("The lane selected doesn't exist.");
        }
        this.lanes.get(laneIndex).addUnit(unit);
    }

    /**
     * @return a list of the lanes of the field
     */
    @Override
    public List<Lane> getLanes() {
        return this.lanes;
    }

    /**
     * @param player the player whose score to get
     * @return the numbers of units that have received the enemy base, if there are any
     */
    @Override
    public Optional<Integer> getScore(final PlayerType player) {
        return this.lanes.stream()
                .map(l -> l.getScore(player))
                .reduce((s1, s2) -> s1 + s2);
    }

    /**
     * @return a map of the units with their corresponding position, rappresented by a pair (y= lane number)
     */
    @Override
    public Map<Unit, Pair<Integer, Integer>> getUnits() {
        final Map<Unit, Pair<Integer, Integer>> units = new HashMap<>();
        this.lanes.forEach(l -> l.getUnits().entrySet()
                .forEach(e -> units.put(e.getKey(), new Pair<>(e.getValue(), lanes.indexOf(l)))));
        return units;
    }

    /**
     * Update all the lanes.
     */
    public void update() {
        this.lanes.forEach(l -> l.update());
    }


}
