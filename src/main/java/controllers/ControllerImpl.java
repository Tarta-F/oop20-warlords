package controllers;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    private int selectedLaneIndexP1;
    private int selectedLaneIndexP2;
    private int selectedUnitIndexP1;
    private int selectedUnitIndexP2;
    private long lastSpawnP1;
    private long lastSpawnP2;
    private final GameView gameView;
    private final FieldImpl field;
    private final int laneNumber;
    private final Optional<PlayerType> winner;
    private final PlayerTimer playerTimer;
//    public ControllerImpl(final GameView gameView) {
//        this.gameView = gameView;
//        this.gameView.setObserver(this);
    public ControllerImpl(final int laneNumber, final int mins) {
        this.lastSpawnP1 = 0;
        this.lastSpawnP2 = 0;
        this.gameView = new GameView(laneNumber);
        this.gameView.setObserver(this);
        this.laneNumber = laneNumber;
        this.selectedLaneIndexP1 = this.laneNumber / 2;
        this.selectedLaneIndexP2 = this.laneNumber / 2;
        this.winner = Optional.empty();
        this.playerTimer = new PlayerTimer(this.gameView);

//        try {
//            this.pane = new Pane();
//            pane.getChildren().setAll(gameView.createContent());
//           } catch (IOException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//           }

        this.field = new FieldImpl(GameConstants.CELLS_NUM, laneNumber);
        new Thread(new GameTimer(mins, this.gameView)).start();
        new Thread(playerTimer).start();
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

    private EnumMap<UnitViewType, List<Pair<Integer, Integer>>> convertMap(final Map<Unit, Pair<Integer, Integer>> modelMap) {
        final EnumMap<UnitViewType, List<Pair<Integer, Integer>>> viewMap = new EnumMap<>(UnitViewType.class);
        modelMap.forEach((k, v) -> {
            final UnitViewType unitView = this.convertUnit(k);
            if (!viewMap.containsKey(unitView)) {
                viewMap.put(unitView, new ArrayList<>());
            }
            viewMap.get(unitView).add(v);
        });
        return viewMap;
    }
    /** Utility function to update Spawn Timer related to the player.
     * @param playerType Player who want to spawn a troup
     */
    private void setSpawnTime(final PlayerType playerType) {
        if (playerType.equals(PlayerType.PLAYER1)) {
            this.lastSpawnP1 = System.currentTimeMillis();
        } else {
            this.lastSpawnP2 = System.currentTimeMillis();
        }
    }

    @Override
    public void controlNextLane(final PlayerType playerType) {
        final int currentIndex = playerType.equals(PlayerType.PLAYER1) ? this.selectedLaneIndexP1 : this.selectedLaneIndexP2;
        final int nextIndex = (currentIndex + 1) % this.laneNumber;
        if (playerType.equals(PlayerType.PLAYER1)) {
            this.selectedLaneIndexP1 = nextIndex;
        }
        if (playerType.equals(PlayerType.PLAYER2)) {
            this.selectedLaneIndexP2 = nextIndex;
        }
        this.gameView.updateSelectLane(playerType, currentIndex, nextIndex); //in base a pType scelgo in che lista guardare
        this.update();
    }

    @Override
    public void controlPrevLane(final PlayerType playerType) {
        final int currentIndex = playerType.equals(PlayerType.PLAYER1) ? this.selectedLaneIndexP1 : this.selectedLaneIndexP2;
        int nextIndex;
        if (currentIndex == 0) {
            nextIndex = this.laneNumber - 1;
        } else {
            nextIndex = (currentIndex - 1) % this.laneNumber;
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
            setSpawnTime(playerType);
            final int lane = playerType.equals(PlayerType.PLAYER1) ? this.selectedLaneIndexP1 : this.selectedLaneIndexP2;
            this.field.addUnit(lane, new UnitImpl(unitToSpawn, playerType));
            gameView.show(this.convertMap(this.field.getUnits()));
            this.playerTimer.resetTimer();
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

    public void update() {
        this.field.update();
        this.gameView.show(this.convertMap(this.field.getUnits()));
    }

    public boolean isOver() {
        return this.field.getScore(PlayerType.PLAYER1).get() == GameConstants.SCORE_WIN;
    }

    public Optional<PlayerType> getWinner() {
        return winner;
    }

    public int getScore(final PlayerType player) {
        return this.field.getScore(player).orElseGet(() -> Integer.valueOf(0));
    }
}
