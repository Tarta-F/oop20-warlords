package model;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;

import constants.PlayerType;
import model.unit.Unit;
import utilities.counters.Counter;
import utilities.counters.CounterImpl;
import utilities.counters.LimitMultiCounter;
import utilities.counters.LimitMultiCounterImpl;

/** 
 * Basic implementation of {@link Lane}.
 */
public class LaneImpl implements Lane {

    private final int lenght;
    private final Map<Unit, LimitMultiCounter> units;
    private final Map<PlayerType, Counter> scores;

    private static final String MESSAGE_OUT_OF_LANE = "The entered position is out of the limits";

    /**
     * Creates a {@link Lane} of the specified lenght.
     * 
     * @param lenght
     *      the lenght of the lane
     */
    public LaneImpl(final int lenght) {
        this.lenght = lenght;
        this.units = new HashMap<>();
        this.scores = new EnumMap<>(PlayerType.class);
        this.scores.put(PlayerType.PLAYER1, new CounterImpl());
        this.scores.put(PlayerType.PLAYER2, new CounterImpl());
    }

    /**
     * Increase the score of the {@link PlayerType}.
     * 
     * @param player
     *      the {@link PlayerType} whose score to increase
     */
    private void score(final PlayerType player) {
        this.scores.get(player).increment();
    }

    /**
     * @param position
     *      a position of this {@link Lane} to verify
     * @return
     *      true if the position is within the limits (0 and {@link LaneImpl#lenght}
     */
    private boolean isLegalPosition(final int position) {
        return position >= 0 && position < this.lenght;
    }

    /**
     * Search a {@link Unit} target for the given one.
     * 
     * @param unit
     *      the {@link Unit} for which to search the target
     * @return
     *      a {@link Optional} that cointains the target if there's any
     */
    private Optional<Unit> searchTarget(final Unit unit) {
        return Stream.iterate(this.getUnits().get(unit), i -> i + (unit.getPlayer().equals(PlayerType.PLAYER1) ? 1 : -1))
                .limit(unit.getRange() + 1)  //  + 1 for the current position
                .filter(this::isLegalPosition)
                .flatMap(p -> this.getUnitsAtPosition(p).stream())
                .filter(u -> u.getPlayer() != unit.getPlayer())
                .findFirst(); 
    }

    /**
     * Move the given {@link Unit} of the quantity of its {@link Unit#getStep()}.
     * 
     * @param unit
     *      the {@link Unit} to move
     */
    private void move(final Unit unit) {
        this.units.get(unit).multiIncrement(unit.getStep());
    }

    /**
     * To subclass this method safely a {@link Unit} needs to be added to this object.
     */
    @Override
    public void addUnit(final Unit unit) {
        this.units.put(unit, new LimitMultiCounterImpl(this.lenght - 1));
    }

    @Override
    public final Set<Unit> getUnitsAtPosition(final int position) {
        if (!this.isLegalPosition(position)) {
            throw new IndexOutOfBoundsException(MESSAGE_OUT_OF_LANE);
        }
        return this.getUnits().entrySet().stream()
                .filter(e -> e.getValue() == position)
                .map(Entry::getKey)
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public final Map<Unit, Integer> getUnits() {
        return this.units.entrySet().stream()
                .filter(e -> e.getKey().isAlive())
                .map(e -> Pair.of(e.getKey(), e.getValue().getCount()))
                .map(p -> p.getLeft().getPlayer().equals(PlayerType.PLAYER1) 
                        ? p : Pair.of(p.getLeft(), this.lenght - p.getRight() - 1))
                .collect(Collectors.toUnmodifiableMap(Pair::getLeft, Pair::getRight));
    }

    @Override
    public final int getLenght() {
        return this.lenght;
    }

    @Override
    public final int getScore(final PlayerType player) {
        return this.scores.get(player).getCount();
    }

    @Override
    public final void resetScore() {
        for (final PlayerType player : PlayerType.values()) {
            this.scores.get(player).reset();
        }
    }

    /**
     * To subclass this method safely dead {@link Unit}s need to be removed.
     */
    @Override
    public void update() {
        final Iterator<Entry<Unit, LimitMultiCounter>> unitsIterator = this.units.entrySet().iterator();
        Entry<Unit, LimitMultiCounter> unit;
        while (unitsIterator.hasNext()) {
            unit = unitsIterator.next();
            if (!unit.getKey().isAlive()) {
                unitsIterator.remove();
                continue;
            }
            final Optional<Unit> target = this.searchTarget(unit.getKey());
            if (target.isPresent()) {
                unit.getKey().attack(target.get());
            } else if (unit.getValue().isOver()) {
                this.score(unit.getKey().getPlayer());
                unitsIterator.remove();
            } else {
                this.move(unit.getKey());
            }
        }

    }


}
