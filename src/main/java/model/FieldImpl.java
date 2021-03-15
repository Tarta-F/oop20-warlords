package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import utilities.Pair;

/**
 * IDEA: USARE SINGLETON PER FIELD, DATO CHE CE NE SARÀ UNA SOLA ISTANZA.
 *
 */
public class FieldImpl implements Field {

    private final List<Lane> lanes;
    private final Map<PlayerType, Integer> score;

    public FieldImpl(final int cellsNumber, final int laneNumber) {
        this.lanes = Stream.generate(() -> cellsNumber)
                .limit(laneNumber)
                .map(LaneImpl::new)
                .collect(Collectors.toList());
        this.score = new HashMap<>();
        this.resetScore();
    }

    private void resetScore() {
        this.score.put(PlayerType.PLAYER1, 0);
        this.score.put(PlayerType.PLAYER2, 0);
    }

    private void scored(final PlayerType player) {
        this.score.put(player, this.score.get(player) + 1);
    }

    /**
     * @param laneIndex the lane in wich insert the unit
     * @param unit the unit to be inserted
     */
    @Override
    public void addUnit(final int laneIndex, final Unit unit) {
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
        return Optional.of(this.score.get(player));
    }

    /**
     * @return a map of the units with their corresponding position, rappresented by a pair (y= lane number)
     */
    @Override
    public Map<Unit, Pair<Integer, Integer>> getUnits() {
        final Map<Unit, Pair<Integer, Integer>> units = new HashMap<>();
        this.lanes.forEach(l -> l.getUnits().entrySet().forEach(e -> units.put(e.getKey(), new Pair<>(e.getValue(), lanes.indexOf(l)))));
        return units;
    }


}
