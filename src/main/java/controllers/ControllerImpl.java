package controllers;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import constants.GameConstants;
import model.FieldImpl;
import model.PlayerType;
import model.Unit;
import model.UnitImpl;
import model.UnitType;
import view.GameView;
import view.UnitViewType;

public final class ControllerImpl implements Controller {
    //private Pair<Pair<PlayerType, Integer>, Pair<PlayerType, Integer>> selectedLaneIndex;
    private int selectedLaneIndexP1 = 2;
    private int selectedLaneIndexP2 = 2;
    //private Pair<Integer, Integer> selectedUnitIndex;
    private int selectedUnitIndexP1 = 0;
    private int selectedUnitIndexP2 = 0;
    private long lastSpawnP1 = 0;
    private long lastSpawnP2 = 0;
    private final GameView gameView;
    private final FieldImpl field;
    //private Pane pane;
    //TODO timer dei player da mostrare alla view ->

//    public ControllerImpl(final GameView gameView) {
//        this.gameView = gameView;
//        this.gameView.setObserver(this);
    public ControllerImpl() {
        this.gameView = new GameView();
        this.gameView.setObserver(this);
//        try {
//            this.pane = new Pane();
//            pane.getChildren().setAll(gameView.createContent());
//           } catch (IOException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//           }
        this.field = new FieldImpl(15, 5);
        new Thread(new GameTimer(1, this.gameView)).start();
    }

    private UnitViewType convertUnit(final Unit modelUnit) {
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
    private Map<UnitViewType, Pair<Integer, Integer>> convertMap(final Map<Unit, Pair<Integer, Integer>> modelMap) {
        final Map<UnitViewType, Pair<Integer, Integer>> viewMap = new HashMap<>();
        modelMap.forEach((k, v) -> viewMap.put(this.convertUnit(k), v));
        return viewMap;
    }

    @Override
    public void controlNextLane(final PlayerType playerType) {
        final int currentIndex = playerType.equals(PlayerType.PLAYER1) ? this.selectedLaneIndexP1 : this.selectedLaneIndexP2;
        final int nextIndex = (currentIndex + 1) % GameConstants.FIVE_LANES;
        if (playerType.equals(PlayerType.PLAYER1)) {
            this.selectedLaneIndexP1 = nextIndex;
        }
        if (playerType.equals(PlayerType.PLAYER2)) {
            this.selectedLaneIndexP2 = nextIndex;
        }
        this.gameView.updateSelectLane(playerType, currentIndex, nextIndex); //in base a pType scelgo in che lista guardare
    }

    @Override
    public void controlPrevLane(final PlayerType playerType) {
        final int currentIndex = playerType.equals(PlayerType.PLAYER1) ? this.selectedLaneIndexP1 : this.selectedLaneIndexP2;
        int nextIndex;
        if (currentIndex == 0) {
            nextIndex = GameConstants.FIVE_LANES - 1;
        } else {
            nextIndex = (currentIndex - 1) % GameConstants.FIVE_LANES;
        }
        if (playerType.equals(PlayerType.PLAYER1)) {
            this.selectedLaneIndexP1 = nextIndex;
        }
        if (playerType.equals(PlayerType.PLAYER2)) {
            this.selectedLaneIndexP2 = nextIndex;
        }
        this.gameView.updateSelectLane(playerType, currentIndex, nextIndex);
    }

    @Override
    public void spawnUnit(final PlayerType playerType) {
        final long lastSpawn = playerType.equals(PlayerType.PLAYER1) ? this.lastSpawnP1 : this.lastSpawnP2;
        final int unitIndex = playerType.equals(PlayerType.PLAYER1) ? this.selectedUnitIndexP1 : this.selectedUnitIndexP2;
        final long timeWaited = System.currentTimeMillis() - lastSpawn;
        final UnitType unitToSpawn = UnitType.values()[unitIndex];
        if (timeWaited > unitToSpawn.getTimer()) { //probabilmente da castare a long (per correttezza (?))
            final int lane = playerType.equals(PlayerType.PLAYER1) ? this.selectedLaneIndexP1 : this.selectedLaneIndexP2;
            this.field.addUnit(lane, new UnitImpl(unitToSpawn, playerType));
            gameView.show(this.convertMap(this.field.getUnits()));
        }
    }

    @Override
    public void controlNextUnit(final PlayerType playerType) {
        final int currentIndex = playerType.equals(PlayerType.PLAYER1) ? this.selectedUnitIndexP1 : this.selectedUnitIndexP2;
        final int nextIndex = (currentIndex + 1) % GameConstants.THREE_LANES;
        if (playerType.equals(PlayerType.PLAYER1)) {
            this.selectedUnitIndexP1 = nextIndex;
        }
        if (playerType.equals(PlayerType.PLAYER2)) {
            this.selectedUnitIndexP2 = nextIndex;
        }
        this.gameView.updateSelectUnit(playerType, currentIndex, nextIndex);
    }

    @Override
    public void controlPrevUnit(final PlayerType playerType) {
        final int currentIndex = playerType.equals(PlayerType.PLAYER1) ? this.selectedUnitIndexP1 : this.selectedUnitIndexP2;
        int nextIndex;
        if (currentIndex == 0) {
            nextIndex = GameConstants.THREE_LANES - 1;
        } else {
            nextIndex = (currentIndex - 1) % GameConstants.THREE_LANES;
        }
        if (playerType.equals(PlayerType.PLAYER1)) {
            this.selectedUnitIndexP1 = nextIndex;
        }
        if (playerType.equals(PlayerType.PLAYER2)) {
            this.selectedUnitIndexP2 = nextIndex;
        }
        this.gameView.updateSelectUnit(playerType, currentIndex, nextIndex);
    }
    public GameView getView() {
        return this.gameView;
    }
}
