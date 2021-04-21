package model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constants.PlayerType;
import model.unit.Unit;
import model.unit.UnitConstants;
import model.unit.UnitImpl;
import model.unit.UnitType;

/**
 * Test Class for {@link UnitImpl}.
 * */
public class UnitTest {

    private Unit unit1;
    private Unit unit2;
    private Unit unit3;

    @BeforeEach
    public final void initUnits() {
        unit1 = new UnitImpl(UnitType.SWORDSMEN, PlayerType.PLAYER1);
        unit2 = new UnitImpl(UnitType.SPEARMEN, PlayerType.PLAYER2);
        unit3 = new UnitImpl(UnitType.ARCHER, PlayerType.PLAYER1);
    }

    @Test
    public void testGetUnitStatistics() {
        assertEquals(Integer.valueOf(UnitConstants.SWORDSMEN_HP), unit1.getHp());
        assertEquals(Integer.valueOf(UnitConstants.SPEARMEN_HP), unit2.getHp());
        assertEquals(Integer.valueOf(UnitConstants.ARCHER_HP), unit3.getHp());

        assertEquals(Integer.valueOf(UnitConstants.SWORDSMEN_DMG), unit1.getDamage());
        assertEquals(Integer.valueOf(UnitConstants.SPEARMEN_DMG), unit2.getDamage());
        assertEquals(Integer.valueOf(UnitConstants.ARCHER_DMG), unit3.getDamage());

        assertEquals(Integer.valueOf(UnitConstants.SWORDSMEN_RANGE), unit1.getRange());
        assertEquals(Integer.valueOf(UnitConstants.SPEARMEN_RANGE), unit2.getRange());
        assertEquals(Integer.valueOf(UnitConstants.ARCHER_RANGE), unit3.getRange());

        assertEquals(Integer.valueOf(UnitConstants.SWORDSMEN_TIMER), unit1.getWaitingTime());
        assertEquals(Integer.valueOf(UnitConstants.SPEARMEN_TIMER), unit2.getWaitingTime());
        assertEquals(Integer.valueOf(UnitConstants.ARCHER_TIMER), unit3.getWaitingTime());

        assertEquals(Integer.valueOf(UnitConstants.SWORDSMEN_STEP), unit1.getStep());
        assertEquals(Integer.valueOf(UnitConstants.SPEARMEN_STEP), unit2.getStep());
        assertEquals(Integer.valueOf(UnitConstants.ARCHER_STEP), unit3.getStep());

        assertEquals(PlayerType.PLAYER1, unit1.getPlayer());
        assertEquals(PlayerType.PLAYER2, unit2.getPlayer());
        assertEquals(PlayerType.PLAYER1, unit3.getPlayer());
 
        assertEquals(UnitType.SWORDSMEN, unit1.getUnitType());
        assertEquals(UnitType.SPEARMEN, unit2.getUnitType());
        assertEquals(UnitType.ARCHER, unit3.getUnitType());

        assertTrue(unit1.isAlive());
        assertTrue(unit2.isAlive());
        assertTrue(unit3.isAlive());
    }

    @Test
    public void testAttackGetDamageAndAlive() {
        /*unit1 attack unit2, unit2 health should be: his maximum life - the unit1 damage. */
        unit1.attack(unit2);
        assertEquals(Integer.valueOf(UnitConstants.SPEARMEN_HP - UnitConstants.SWORDSMEN_DMG), unit2.getHp());

        /*unit3 take damage, unit3 take damage equal to his maximum health, at this point unit3 should be dead*/
        unit3.damage(UnitConstants.ARCHER_HP);
        assertFalse(unit3.isAlive());
    }

}
