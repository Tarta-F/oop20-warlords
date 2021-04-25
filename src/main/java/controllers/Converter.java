package controllers;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import constants.PlayerType;
import model.unit.Unit;
import model.unit.UnitType;
import view.game.UnitViewType;

/**
 * This class offers static methods for {@link Controller} class.
 */
public final class Converter {

    private Converter() {
        /* Not used. */
    }

    /**
     * Converts the istance of {@link Unit} class from the model, to the corresponding {@link UnitViewType}.
     * 
     * @param modelUnit 
     *      the {@link Unit} to be converted
     * @return 
     *      the {@link UnitViewType} of the input one
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
     * Converts the given {@link Map} from model to the corrisponding {@link EnumMap} that has to be printed on the {@link GameView}.
     * 
     * @param modelMap 
     *      the {@link Map} of the {@link Unit} and their positions
     * @return 
     *      the {@link Map} for the {@link GameView}
     */
    public static Map<UnitViewType, List<Pair<Integer, Integer>>> convertMap(final Map<Unit, Pair<Integer, Integer>> modelMap) {
        final Map<UnitViewType, List<Pair<Integer, Integer>>> viewMap = new EnumMap<>(UnitViewType.class);
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
