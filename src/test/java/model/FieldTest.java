package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import constants.GameConstants;
import utilities.Pair;

public class FieldTest {

    @Test
    public void testAddUnit() {
        final Field f = new FieldImpl(GameConstants.CELLS_NUM, GameConstants.ONE_LANE);
        final Unit unit1 = new UnitImpl(UnitType.SWORDSMEN, PlayerType.PLAYER1);
        final Unit unit2 = new UnitImpl(UnitType.SWORDSMEN, PlayerType.PLAYER2);

        f.addUnit(0, unit1);
        f.addUnit(0, unit2);

        assertEquals(f.getUnits().get(unit1), new Pair<>(0, 0));
        assertEquals(f.getUnits().get(unit2), new Pair<>(GameConstants.CELLS_NUM, 0));
    }

//    @Test
//    public void getScore() {
//
//    }
//
//    @Test
//    public void getUnits() {
//
//    }


}
