package model.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import constants.GameConstants;
import model.Field;
import model.FieldImpl;
import model.PlayerType;
import model.Unit;
import model.UnitImpl;
import model.UnitType;
import utilities.Pair;

public class FieldTest {

    private Field field;

    @Before
    public void initField() {
        this.field = new FieldImpl(GameConstants.CELLS_NUM, GameConstants.THREE_LANES);
    }

    @Test
    public void testAddUnit() {
        final Unit unit1 = new UnitImpl(UnitType.SWORDSMEN, PlayerType.PLAYER1);
        final Unit unit2 = new UnitImpl(UnitType.SWORDSMEN, PlayerType.PLAYER2);

        field.addUnit(0, unit1);
        field.addUnit(1, unit2);

        assertEquals(new Pair<>(0, 0), field.getUnits().get(unit1));
        assertEquals(new Pair<>(GameConstants.CELLS_NUM, 1), field.getUnits().get(unit2));

        field.update();
        field.update();

        assertEquals(new Pair<>(2, 0), field.getUnits().get(unit1));
        assertEquals(new Pair<>(GameConstants.CELLS_NUM - 2, 1), field.getUnits().get(unit2));
    }

    @Test
    public void getScore() {
    }

//    @Test
//    public void getUnits() {
//
//    }


}
