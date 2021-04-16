package model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Set;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constants.GameConstants;
import constants.PlayerType;
import model.Lane;
import model.LaneImpl;
import model.unit.Unit;
import model.unit.UnitImpl;
import model.unit.UnitType;

public class LaneTest {

    private Lane lane;
    private Unit unit1;
    private Unit unit2;
    private static final int CLASH_POSITION = 6;

    @BeforeEach
    public final void initLane() {
        lane = new LaneImpl(GameConstants.CELLS_NUM);
        unit1 = new UnitImpl(UnitType.SWORDSMEN, PlayerType.PLAYER1);
        unit2 = new UnitImpl(UnitType.SWORDSMEN, PlayerType.PLAYER2);

        lane.addUnit(unit1);
        lane.addUnit(unit2);
    }

    @Test
    public void testAddUnitAndGetLenght() {
        assertEquals(Integer.valueOf(0), lane.getUnits().get(unit1));
        assertEquals(Integer.valueOf(GameConstants.CELLS_NUM - 1), lane.getUnits().get(unit2));
    }

    @Test
    public void testMovement() {
        lane.update();
        lane.update();

        assertEquals(Integer.valueOf(2), lane.getUnits().get(unit1));
        assertEquals(Integer.valueOf(GameConstants.CELLS_NUM - 3), lane.getUnits().get(unit2));

        lane.update();

        assertEquals(Integer.valueOf(3), lane.getUnits().get(unit1));
        assertEquals(Integer.valueOf(GameConstants.CELLS_NUM - 4), lane.getUnits().get(unit2));
    }

    @Test
    public void testGetUnitsAtPosition() {
        assertEquals(Set.of(unit1), lane.getUnitsAtPosition(0));
        assertEquals(Set.of(unit2), lane.getUnitsAtPosition(GameConstants.CELLS_NUM - 1));

        lane.update();

        assertEquals(Set.of(unit1), lane.getUnitsAtPosition(1));
        assertEquals(Set.of(unit2), lane.getUnitsAtPosition(GameConstants.CELLS_NUM - 2));

        unit1 = new UnitImpl(UnitType.ARCHER, PlayerType.PLAYER1);
        unit2 = new UnitImpl(UnitType.SPEARMEN, PlayerType.PLAYER1);
        lane.addUnit(unit1);
        lane.addUnit(unit2);

        assertEquals(Set.of(unit1, unit2), lane.getUnitsAtPosition(0));

        try {
            lane.getUnitsAtPosition(GameConstants.CELLS_NUM);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isEmpty());
        }
    }

    @Test
    public void testAttackAndDespawn() {
        IntStream.range(0, CLASH_POSITION).forEach(c -> {
            assertEquals(Integer.valueOf(c), lane.getUnits().get(unit1));
            assertEquals(Integer.valueOf(GameConstants.CELLS_NUM - 1 - c), lane.getUnits().get(unit2));
            lane.update();
        });

        assertEquals(Integer.valueOf(CLASH_POSITION), lane.getUnits().get(unit1));
        assertEquals(Integer.valueOf(GameConstants.CELLS_NUM - 1 - CLASH_POSITION), lane.getUnits().get(unit2));

        lane.update();

        /*
         * Il comportamento delle unità da qui in poi è indefinito poichè non si sa quale si muoverà per prima e 
         *  di conseguenza attaccherà per seconda.
         */
        IntStream.range(0, CLASH_POSITION + 1).forEach(c -> {
            lane.update();
        });

        /*
         * A questo punto uno dei due deve essere morto. 
         */
        assertTrue(unit1.isAlive() ^ unit2.isAlive());
        assertTrue(lane.getUnits().containsKey(unit1) ^ lane.getUnits().containsKey(unit2));

    }

    @Test
    public void testScoreAndDespawn() {
        lane = new LaneImpl(GameConstants.CELLS_NUM);
        lane.addUnit(unit1);

        IntStream.range(0, GameConstants.CELLS_NUM).forEach(c -> {
            assertEquals(Integer.valueOf(c), lane.getUnits().get(unit1));
            lane.update();
        });

        assertEquals(Integer.valueOf(1), lane.getScore(PlayerType.PLAYER1));
        assertFalse(lane.getUnits().containsKey(unit1));

    }

}
