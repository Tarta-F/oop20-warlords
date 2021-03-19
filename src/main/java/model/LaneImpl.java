package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import utilities.Counter;

public class LaneImpl implements Lane {

    private final int lenght;
    private final Map<Unit, Integer> units;
    private final Map<PlayerType, Counter> scores;

    public LaneImpl(final int lenght) {
        this.lenght = lenght;
        this.units = new HashMap<>();
        this.scores = new HashMap<>();
        this.resetScore();
    }

    private void resetScore() {
        this.scores.put(PlayerType.PLAYER1, new Counter());
        this.scores.put(PlayerType.PLAYER2, new Counter());
    }

    private int getGoal(final PlayerType player) {
        return player == PlayerType.PLAYER1 ? this.lenght - 1 : 0;
    }

    private void score(final PlayerType player) {
        this.scores.get(player).increment();
    }

    private Optional<Unit> searchTarget(final Unit unit) {
        return null;
    }

    /**
     * @param u unit to be added
     */
    @Override
    public void addUnit(final Unit u) {
        this.units.put(u, u.getPlayer() == PlayerType.PLAYER1 ? 0 : this.lenght - 1);
    }

    /**
     * @param position 
     * @return the set of the units in this position
     */
    @Override
    public Set<Unit> getUnitsAtPosition(final int position) {
        return this.units.entrySet().stream()
                .filter(e -> e.getValue() == position)
                .map(e -> e.getKey())
                .collect(Collectors.toSet());
    }

    /**
     * @return a map of unit with the corresponding position in this lane
     */
    @Override
    public Map<Unit, Integer> getUnits() {
        return this.units;
    }

    /**
     * @return the lenght of lane
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
     * Updates every unit in this lane with walk, attack or despawn.
     */
    @Override
    public void update() {
        this.units.entrySet().forEach(e -> {
            final Unit unit = e.getKey();
            final Optional<Unit> target = this.searchTarget(unit);
            if (target.isEmpty()) {
//                this.score(unit.getPlayer());
//            } else if (unit.getTarget().isPresent()) {
//                unit.getTarget().get().damage(unit.getDamage());
//            } else {
//                unit.walk();
            }
        });
    }

}
