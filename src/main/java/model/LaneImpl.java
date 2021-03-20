package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import utilities.counters.Counter;
import utilities.counters.CounterImpl;
import utilities.counters.LimitMultiCounter;
import utilities.counters.LimitMultiCounterImpl;

public class LaneImpl implements Lane {

    private final int lenght;
    private final Map<Unit, LimitMultiCounter> units;
    private final Map<PlayerType, Counter> scores;

    public LaneImpl(final int lenght) {
        this.lenght = lenght;
        this.units = new HashMap<>();
        this.scores = new HashMap<>();
        this.resetScore();
    }

    private void resetScore() {
        this.scores.put(PlayerType.PLAYER1, new CounterImpl());
        this.scores.put(PlayerType.PLAYER2, new CounterImpl());
    }

//    private boolean goalReached(final Unit unit) {
//        return this.units.get(unit) == 
//                (unit.getPlayer() == PlayerType.PLAYER1 ? 
//                 this.lenght - 1 : 0);
//    }

    private void score(final PlayerType player) {
        this.scores.get(player).increment();
    }

    private Optional<Unit> searchTarget(final Unit unit) {
        // TODO 
        return null;
    }


    private void move(final Unit unit) {
        this.units.get(unit).multiIncrement(unit.getStep());
    }

    /**
     * @param unit unit to be added
     */
    @Override
    public void addUnit(final Unit unit) {
        this.units.put(unit, new LimitMultiCounterImpl(this.lenght));
    }

    /**
     * @param position 
     * @return the set of the units in this position
     */
    @Override
    public Set<Unit> getUnitsAtPosition(final int position) {
        return this.getUnits().entrySet().stream()
                .filter(e -> e.getValue() == position)
                .map(e -> e.getKey())
                .collect(Collectors.toSet());
    }

    /**
     * @return a map of unit with the corresponding position in this lane
     */
    @Override
    public Map<Unit, Integer> getUnits() {
        Map<Unit, Integer> map = new HashMap<>();
        this.units.entrySet().forEach(e -> {
            final Unit unit = e.getKey();
            final int nSteps = e.getValue().getValue();
            map.put(unit, unit.getPlayer() == PlayerType.PLAYER1 ? nSteps : this.lenght - nSteps);
        });
        return map;
    }

    /**
     * @return the lenght of the lane
     */
    @Override
    public int getLenght() {
        return this.lenght;
    }

    /**
     * @param player the player whose score to get
     * @return the numbers of units that have received the enemy base by this lane, if there are any
     */
    @Override
    public Integer getScore(final PlayerType player) {
        return this.scores.get(player).getValue();
    }

    /**
     * Updates every unit in this lane with attack, score or move.
     */
    @Override
    public void update() {
        this.units.entrySet().forEach(e -> {
            final Unit unit = e.getKey();
            final Optional<Unit> target = this.searchTarget(unit);
            if (target.isPresent()) {
                unit.attack(target.get());
            } else if (this.units.get(unit).isOver()) {
                this.score(unit.getPlayer());
            } else {
                this.move(unit);
            }
        });
    }


}
