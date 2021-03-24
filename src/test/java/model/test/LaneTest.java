package model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;

import constants.GameConstants;
import model.Lane;
import model.LaneImpl;
import model.PlayerType;
import model.Unit;
import model.UnitImpl;
import model.UnitType;

public class LaneTest {

    private Lane lane = null;
    private static final int CLASH_POSITION = 6;

    @Before
    public void initLane() {
        lane = new LaneImpl(GameConstants.CELLS_NUM);
    }

    @Test
    public void testAddUnit() {
        final Unit unit1 = new UnitImpl(UnitType.SWORDSMEN, PlayerType.PLAYER1);
        final Unit unit2 = new UnitImpl(UnitType.SWORDSMEN, PlayerType.PLAYER2);

        lane.addUnit(unit1);
        lane.addUnit(unit2);

        assertEquals(Integer.valueOf(0), lane.getUnits().get(unit1));
        assertEquals(Integer.valueOf(GameConstants.CELLS_NUM - 1), lane.getUnits().get(unit2));
    }

    @Test
    public void testGetUnitsAtPosition() {
        final Unit unit1 = new UnitImpl(UnitType.SWORDSMEN, PlayerType.PLAYER1);
        final Unit unit2 = new UnitImpl(UnitType.SWORDSMEN, PlayerType.PLAYER2);

        lane.addUnit(unit1);
        lane.addUnit(unit2);

        assertEquals(Set.of(unit1), lane.getUnitsAtPosition(0));
        assertEquals(Set.of(unit2), lane.getUnitsAtPosition(GameConstants.CELLS_NUM - 1));
    }

    @Test
    public void testMovement() {
        final Unit unit1 = new UnitImpl(UnitType.SWORDSMEN, PlayerType.PLAYER1);
        final Unit  unit2 = new UnitImpl(UnitType.SWORDSMEN, PlayerType.PLAYER2);

        lane.addUnit(unit1);
        lane.addUnit(unit2);

        lane.update();
        lane.update();

        assertEquals(Integer.valueOf(2), lane.getUnits().get(unit1));
        assertEquals(Integer.valueOf(GameConstants.CELLS_NUM - 3), lane.getUnits().get(unit2));
    }

    @Test
    public void testAttack() {
        final Unit unit1 = new UnitImpl(UnitType.SWORDSMEN, PlayerType.PLAYER1);
        final Unit  unit2 = new UnitImpl(UnitType.SWORDSMEN, PlayerType.PLAYER2);

        lane.addUnit(unit1);
        lane.addUnit(unit2);

        IntStream.range(0, CLASH_POSITION).forEach(c -> {
            assertEquals(Integer.valueOf(c), lane.getUnits().get(unit1));
            assertEquals(Integer.valueOf(GameConstants.CELLS_NUM - 1 - c), lane.getUnits().get(unit2));
            lane.update();
        });

        assertEquals(Integer.valueOf(CLASH_POSITION), lane.getUnits().get(unit1));
        assertEquals(Integer.valueOf(GameConstants.CELLS_NUM - 1 - CLASH_POSITION), lane.getUnits().get(unit2));

        lane.update();

//        assertEquals(UnitType.SWORDSMEN.getHealth() - UnitType.SWORDSMEN.getDamage(), unit2.getHP());
//        assertEquals(UnitType.SWORDSMEN.getHealth() - UnitType.SWORDSMEN.getDamage(), unit1.getHP());
        assertEquals(Integer.valueOf(CLASH_POSITION + 1), lane.getUnits().get(unit1));                              // UNIT 1 SI MUOVE 
        assertEquals(Integer.valueOf(GameConstants.CELLS_NUM - 1 - CLASH_POSITION), lane.getUnits().get(unit2));    // UNIT 2 TROVA UNIT 1 E ATTACCA, QUINDI NON SI MUOVE

        lane.update();

        assertEquals(UnitType.SWORDSMEN.getHealth() - UnitType.SWORDSMEN.getDamage(), unit2.getHP());
        assertEquals(UnitType.SWORDSMEN.getHealth() - 2 * UnitType.SWORDSMEN.getDamage(), unit1.getHP());
        assertEquals(Integer.valueOf(CLASH_POSITION + 1), lane.getUnits().get(unit1));                                  // UNIT 1 NON SI MUOVE PERCHè ATTACCA
        assertEquals(Integer.valueOf(GameConstants.CELLS_NUM - 1 - CLASH_POSITION), lane.getUnits().get(unit2));    // UNIT 2 SI MUOVE

        lane.update();

        assertEquals(UnitType.SWORDSMEN.getHealth() - 2 * UnitType.SWORDSMEN.getDamage(), unit2.getHP());
        assertEquals(UnitType.SWORDSMEN.getHealth() - 3 * UnitType.SWORDSMEN.getDamage(), unit1.getHP());

        lane.update();

        assertEquals(UnitType.SWORDSMEN.getHealth() - 3 * UnitType.SWORDSMEN.getDamage(), unit2.getHP());
        assertEquals(UnitType.SWORDSMEN.getHealth() - 4 * UnitType.SWORDSMEN.getDamage(), unit1.getHP());

        lane.update();

        assertEquals(UnitType.SWORDSMEN.getHealth() - 4 * UnitType.SWORDSMEN.getDamage(), unit2.getHP());
        assertEquals(UnitType.SWORDSMEN.getHealth() - 5 * UnitType.SWORDSMEN.getDamage(), unit1.getHP());

        lane.update();

        assertEquals(UnitType.SWORDSMEN.getHealth() - 5 * UnitType.SWORDSMEN.getDamage(), unit2.getHP());
        assertEquals(UnitType.SWORDSMEN.getHealth() - 6 * UnitType.SWORDSMEN.getDamage(), unit1.getHP());

        lane.update();

        assertEquals(UnitType.SWORDSMEN.getHealth() - 6 * UnitType.SWORDSMEN.getDamage(), unit2.getHP());
        assertEquals(UnitType.SWORDSMEN.getHealth() - 7 * UnitType.SWORDSMEN.getDamage(), unit1.getHP());

        lane.update();

        assertEquals(UnitType.SWORDSMEN.getHealth() - 7 * UnitType.SWORDSMEN.getDamage(), unit2.getHP());
        assertEquals(UnitType.SWORDSMEN.getHealth() - 8 * UnitType.SWORDSMEN.getDamage(), unit1.getHP());


        assertFalse(lane.getUnits().containsKey(unit1));
        lane.update();
        assertFalse(lane.getUnits().containsKey(unit1));
//
//        assertEquals(UnitType.SWORDSMEN.getHealth() - 8 * UnitType.SWORDSMEN.getDamage(), unit2.getHP());
//        assertEquals(0, unit1.getHP());
    }

//  assertEquals(Integer.valueOf(0), lane.getScore(PlayerType.PLAYER1));
//  assertEquals(Integer.valueOf(0), lane.getScore(PlayerType.PLAYER2));
}
