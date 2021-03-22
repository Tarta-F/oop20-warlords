package model.test;

import static org.junit.Assert.assertEquals;

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
        assertEquals(Integer.valueOf(GameConstants.CELLS_NUM), lane.getUnits().get(unit2));

        lane.update();

        assertEquals(Integer.valueOf(0), lane.getScore(PlayerType.PLAYER1));
        assertEquals(Integer.valueOf(0), lane.getScore(PlayerType.PLAYER2));
        assertEquals(Integer.valueOf(1), lane.getUnits().get(unit1));
        assertEquals(Integer.valueOf(GameConstants.CELLS_NUM - 1), lane.getUnits().get(unit2));

    }
}
