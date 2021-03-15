package model;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import utilities.Pair;

public class LaneImpl implements Lane {

    private final int lenght;

    public LaneImpl(final int lenght) {
        this.lenght = lenght;
    }

    @Override
    public void addUnit(final Unit u) {
        // TODO Auto-generated method stub

    }

    /**
     * @param position 
     * @return the set of the units in this position
     */
    @Override
    public Set<Unit> getUnitsAtPosition(final int position) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return a map of unit with the corresponding position in this lane
     */
    @Override
    public Map<Unit, Integer> getUnits() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return the lenght of lane
     */
    @Override
    public int getLenght() {
        return this.lenght;
    }

    @Override
    public Optional<Integer> getScore(final PlayerType player) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

}
