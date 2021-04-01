package model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constants.GameConstants;
import model.Field;
import model.FieldImpl;
import model.PlayerType;
import model.Unit;
import model.UnitImpl;
import model.UnitType;
import org.apache.commons.lang3.tuple.Pair;;

public class FieldTest {

    private Field field;

    @BeforeEach
    public final void initField() {
        this.field = new FieldImpl(GameConstants.CELLS_NUM, GameConstants.THREE_LANES);
    }

    @Test
    public void testAddUnit() {
        Unit unit1 = new UnitImpl(UnitType.SWORDSMEN, PlayerType.PLAYER1);
        Unit unit2 = new UnitImpl(UnitType.SWORDSMEN, PlayerType.PLAYER2);

        field.addUnit(0, unit1);
        field.addUnit(1, unit2);

        assertEquals(Pair.of(0, 0), field.getUnits().get(unit1));
        assertEquals(Pair.of(GameConstants.CELLS_NUM - 1, 1), field.getUnits().get(unit2));

        field.update();
        field.update();

        assertEquals(Pair.of(2, 0), field.getUnits().get(unit1));
        assertEquals(Pair.of(GameConstants.CELLS_NUM - 3, 1), field.getUnits().get(unit2));

        unit1 = new UnitImpl(UnitType.ARCHER, PlayerType.PLAYER1);
        unit2 = new UnitImpl(UnitType.SPEARMEN, PlayerType.PLAYER1);

        field.addUnit(1, unit1);
        field.addUnit(2, unit2);

        assertEquals(Pair.of(0, 1), field.getUnits().get(unit1));
        assertEquals(Pair.of(0, 2), field.getUnits().get(unit2));

        unit1 = new UnitImpl(UnitType.SWORDSMEN, PlayerType.PLAYER1);

        try {
            field.addUnit(4, unit1);
            fail();
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isEmpty());
        }

    }

    @Test
    public void getScore() {
        Unit unit1 = new UnitImpl(UnitType.SWORDSMEN, PlayerType.PLAYER1);
        Unit unit2 = new UnitImpl(UnitType.SWORDSMEN, PlayerType.PLAYER2);

        field.addUnit(0, unit1);
        field.addUnit(1, unit2);

        IntStream.range(0, field.getCellsNumber() - 1).forEach(c -> field.update());
        assertEquals(Pair.of(GameConstants.CELLS_NUM - 1, 0), field.getUnits().get(unit1));
        assertEquals(Pair.of(0, 1), field.getUnits().get(unit2));

        field.update();

        assertEquals(Integer.valueOf(1), field.getScore(PlayerType.PLAYER1).get());
        assertEquals(Integer.valueOf(1), field.getScore(PlayerType.PLAYER1).get());

    }

    @Test
    public void getUnits() {

    }


}
