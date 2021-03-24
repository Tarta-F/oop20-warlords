package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private void score(final PlayerType player) {
        this.scores.get(player).increment();
    }

    private boolean isLegalPosition(final int position) {
        return (position >= 0 && position < this.lenght);
    }

    private Optional<Unit> searchTarget(final Unit unit) {
//        int n;
//        int pos = this.getUnits().get(unit);
//        int sign = unit.getPlayer() == PlayerType.PLAYER1 ? 1 : -1;
//
//        for (n = 0; n < unit.getRange(); n++) {
//            for (var ent : this.getUnitsAtPosition(pos + (sign * n))) {
//                if (ent.getPlayer() != unit.getPlayer()) {
//                    target = ent;
//                    break;
//                }
//            }
//            if (target != null) {
//                break;
//            }
//        }

//        Unit target = null;
//        int direction = unit.getPlayer() == PlayerType.PLAYER1 ? 1 : -1;
//        var list = Stream.iterate(this.getUnits().get(unit), i -> i + direction)
//                .limit(unit.getRange() + 1)  //  + 1 for the current position
//                .filter(this::isLegalPosition)
//                .sorted((a, b) -> a - b)
//                .collect(Collectors.toList());
//
//        Iterator<Unit> it;
//        for (int pos : list) {
//            it = this.getUnitsAtPosition(pos).stream()
//                    .filter(u -> u.getPlayer() != unit.getPlayer())
//                    .iterator();
//            if (it.hasNext()) {
//                target = it.next();
//                break;
//            }
//        }
//
//        return Optional.of(target);


        return Stream.iterate(this.getUnits().get(unit), i -> i + (unit.getPlayer() == PlayerType.PLAYER1 ? 1 : -1))
                .limit(unit.getRange() + 1)  //  + 1 for the current position
                .filter(this::isLegalPosition)
                .flatMap(p -> this.getUnitsAtPosition(p).stream())
                .filter(u -> u.getPlayer() != unit.getPlayer())
                .findFirst(); 
    }

//TODO     private void despawn(final Unit unit) {
//        this.units.remove(unit);
//    }

    private void move(final Unit unit) {
        this.units.get(unit).multiIncrement(unit.getStep());
    }

    /**
     * @param unit unit to be added
     */
    @Override
    public void addUnit(final Unit unit) {
        this.units.put(unit, new LimitMultiCounterImpl(this.lenght - 1));
    }

    /**
     * @param position 
     * @return the set of the units in this position
     * @throws IllegalArgumentException in case of the position is out of the lane
     */
    @Override
    public Set<Unit> getUnitsAtPosition(final int position) {
        if (!this.isLegalPosition(position)) {
            throw new IllegalArgumentException("The entered position is out of the lane limits");
        }
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
            if (unit.isAlive()) {
                final int nSteps = e.getValue().getValue();
                map.put(unit, unit.getPlayer() == PlayerType.PLAYER1 ? nSteps : this.lenght - nSteps - 1);
            }
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
     * PROBLEMI :
     * -TODO Quando una truppa muore o fa score deve essere rimossa ma non può essere fatto nel ciclo
     */
    @Override
    public void update() {
        this.units.entrySet().forEach(e -> {
            final Unit unit = e.getKey();

            /**
             * TODO
             * PER PROVA
             */
            if (unit.getHP() > 0) {
                final Optional<Unit> target = this.searchTarget(unit);
                if (target.isPresent()) {
                    unit.attack(target.get());
                } else if (this.units.get(unit).isOver()) {
                    this.score(unit.getPlayer());
                    /*
                     *TODO
                     * REMOVE UNIT / DESPANW
                     * 
                    this.despawn(unit);
                     */
                } else {
                    this.move(unit);
                }
            }
        });
    }


}
