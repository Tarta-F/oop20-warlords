package controllers;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import model.PlayerType;
import model.Unit;
import model.UnitType;
import view.UnitViewType;

/**
 * This class offers static methods for Controller classes.
 */
public final class Converter {

    private Converter() {
    }

    /**
     * Converts the istance of Unit class from the model, to the corresponding UnitViewType.
     * @param modelUnit the unit to be converted
     * @return the UnitViewType of the given unit
     */
    private static UnitViewType convertUnit(final Unit modelUnit) {
        final UnitType modelType = modelUnit.getUnitType();
        final PlayerType player = modelUnit.getPlayer();
        switch (modelType) {
            case SWORDSMEN:
                return player.equals(PlayerType.PLAYER1) ? UnitViewType.SWORDSMEN_PLAYER1 : UnitViewType.SWORDSMEN_PLAYER2;
            case SPEARMEN:
                return player.equals(PlayerType.PLAYER1) ? UnitViewType.SPEARMEN_PLAYER1 : UnitViewType.SPEARMEN_PLAYER2;
            case ARCHER:
                return player.equals(PlayerType.PLAYER1) ? UnitViewType.ARCHER_PLAYER1 : UnitViewType.ARCHER_PLAYER2;
            default:
                return null;
        }
    }

    /**
     * Converts the given map from model to the corrisponding EnumMap that has to be printed on the View.
     * @param modelMap the map of the units and their positions
     * @return the EnumMap for the View
     */
    public static EnumMap<UnitViewType, List<Pair<Integer, Integer>>> convertMap(final Map<Unit, Pair<Integer, Integer>> modelMap) {
        final EnumMap<UnitViewType, List<Pair<Integer, Integer>>> viewMap = new EnumMap<>(UnitViewType.class);
        modelMap.forEach((k, v) -> {
            final UnitViewType unitView = convertUnit(k);
            if (!viewMap.containsKey(unitView)) {
                viewMap.put(unitView, new ArrayList<>());
            }
            viewMap.get(unitView).add(v);
        });
        return viewMap;
    }
}
