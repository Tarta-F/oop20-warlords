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
    private Optional<PlayerType> winner;
    private final PlayerTimer playerTimer;
    private final PlayerTimer playerTimer2;
//    public ControllerImpl(final GameView gameView) {
//        this.gameView = gameView;
//        this.gameView.setObserver(this);
//    private String background;
//    private String ground;

    public ControllerImpl(final int laneNumber, final int mins, final String background, final String ground) {
        this.lastSpawnP1 = 0;
        this.lastSpawnP2 = 0;

        this.gameView = new GameView(laneNumber, background, ground);
        this.gameView.setObserver(this);

        this.laneNumber = laneNumber;
        this.selectedLaneIndexP1 = this.laneNumber / 2;
        this.selectedLaneIndexP2 = this.laneNumber / 2;
        this.winner = Optional.empty();
        this.playerTimer = new PlayerTimer(this.gameView, PlayerType.PLAYER1);
        this.playerTimer2 = new PlayerTimer(this.gameView, PlayerType.PLAYER2);
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
        new Thread(playerTimer2).start();
    }

    /**
     * Check if the given player has win, if yes the player is setted as the winner.
     * @param player to check the victory
     * @return true if the player has won
     */
    private boolean hasWin(final PlayerType player) {
        if (this.getScore(player) == GameConstants.SCORE_WIN) {
            this.winner = Optional.ofNullable(player);
            return true;
        }
        return false;
    }

    /**
     * Converts the istance of Unit class from the model, to the corresponding UnitViewType.
     * @param modelUnit the unit to be converted
     * @return the UnitViewType of the given unit
     */
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

    /**
     * Converts the given map from model to the corrisponding EnumMap that has to be printed on the View.
     * @param modelMap the map of the units and their positions
     * @return the EnumMap for the View
     */
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

        //TODO PER PROVA
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
        if (timeWaited > unitToSpawn.getTimer()) {
            setSpawnTime(playerType);
            final int lane = playerType.equals(PlayerType.PLAYER1) ? this.selectedLaneIndexP1 : this.selectedLaneIndexP2;
            this.field.addUnit(lane, new UnitImpl(unitToSpawn, playerType));
            gameView.show(this.convertMap(this.field.getUnits()));
            this.resetPlayerTimer(playerType);
        }
    }

    private void resetPlayerTimer(final PlayerType playerType) {
        if (playerType.equals(PlayerType.PLAYER1)) {
            this.playerTimer.resetTimer();
        } else {
            this.playerTimer2.resetTimer();
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

    @Override
    public GameView getView() {
        return this.gameView;
    }

    @Override
    public void update() {
        this.field.update();
        this.gameView.show(this.convertMap(this.field.getUnits()));

        //TODO PER PROVA
        System.out.println(getScore(PlayerType.PLAYER1));
        System.out.println(getScore(PlayerType.PLAYER2));
        System.out.println(isOver() ? getWinner().get() + " HA VINTO" : "");
    }

    @Override
    public boolean isOver() {
        return this.hasWin(PlayerType.PLAYER1) || this.hasWin(PlayerType.PLAYER2);
    }

    @Override
    public Optional<PlayerType> getWinner() {
        return this.winner;
    }

    @Override
    public int getScore(final PlayerType player) {
        return this.field.getScore(player).orElseGet(() -> Integer.valueOf(0));
    }
}
