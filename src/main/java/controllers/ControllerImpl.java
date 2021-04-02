package controllers;
//import org.apache.commons.lang3.tuple.Pair;

import constants.GameConstants;
import model.FieldImpl;
import model.PlayerType;
import model.UnitImpl;
import model.UnitType;
import view.GameView;

public class ControllerImpl implements Controller {
    //private Pair<Pair<PlayerType, Integer>, Pair<PlayerType, Integer>> selectedLaneIndex;
    private int selectedLaneIndexP1 = 2;
    private int selectedLaneIndexP2 = 2;
    //private Pair<Integer, Integer> selectedUnitIndex;
    private int selectedUnitIndexP1 = 0;
    private int selectedUnitIndexP2 = 0;
    private long lastSpawnP1 = 0;
    private long lastSpawnP2 = 0;
    private GameView gameView;
    private FieldImpl field;
    //TODO timer dei player da mostrare alla view ->
    public ControllerImpl() {
        //this.selectedLaneIndex = Pair.of(Pair.of(PlayerType.PLAYER1, 2), Pair.of(PlayerType.PLAYER2, 2));
        this.gameView = new GameView();
        this.field = new FieldImpl(15, 5);
    }
//    private void selectNextLane(PlayerType playerType) {
//        final Pair<PlayerType, Integer> util = playerType.equals(PlayerType.PLAYER1) ? this.selectedLaneIndex.getLeft() : this.selectedLaneIndex.getRight();
//        final int index = util.getRight();
//        final int next = (index + 1) % GameConstants.FIVE_LANES;
//        //this.selectedLaneIndex = Pair.of(playerType, next);  //update pair with the next index
//    }
//    private void selectPrevLane(PlayerType playerType) {
//        final Pair<PlayerType, Integer> util = playerType.equals(PlayerType.PLAYER1) ? this.selectedLaneIndex.getLeft() : this.selectedLaneIndex.getRight();
//        final int index = util.getRight();
//        int next;
//        if (index == 0) {
//            next = GameConstants.FIVE_LANES - 1;
//        } else {
//            next = (index - 1) % GameConstants.FIVE_LANES;
//        }
//        //util = Pair.of(playerType, next); //errato
//    }
    @Override
    public void controlLaneUp(final PlayerType playerType) {
        final int currentIndex = playerType.equals(PlayerType.PLAYER1) ? this.selectedLaneIndexP1 : this.selectedLaneIndexP2;
        final int nextIndex = (currentIndex + 1) % GameConstants.FIVE_LANES;
        this.selectedLaneIndexP1 = nextIndex;
      //TODO this.gameView.updateSelectLane(playerType, index, next); //in base a pType scelgo in che lista guardare
    }

    @Override
    public void controlLaneDown(final PlayerType playerType) {
        final int currentIndex = playerType.equals(PlayerType.PLAYER1) ? this.selectedLaneIndexP1 : this.selectedLaneIndexP2;
        int nextIndex;
        if (currentIndex == 0) {
            nextIndex = GameConstants.FIVE_LANES - 1;
        } else {
            nextIndex = (currentIndex - 1) % GameConstants.FIVE_LANES;
        }
        this.selectedLaneIndexP1 = nextIndex;
        //TODO
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
          //gameView.show(Mappa con unità da stampare);  (?)
        }
    }

    @Override
    public void controlNextUnit(final PlayerType playerType) {
        final int currentIndex = playerType.equals(PlayerType.PLAYER1) ? this.selectedUnitIndexP1 : this.selectedUnitIndexP2;
        final int nextIndex = (currentIndex + 1) % GameConstants.FIVE_LANES;
        this.selectedUnitIndexP1 = nextIndex;
        //TODO this.gameView.updateSelectUnit(playerType, index, next);
    }

    @Override
    public void controlPrevUnit(final PlayerType playerType) {
        final int currentIndex = playerType.equals(PlayerType.PLAYER1) ? this.selectedUnitIndexP1 : this.selectedUnitIndexP2;
        int nextIndex;
        if (currentIndex == 0) {
            nextIndex = GameConstants.FIVE_LANES - 1;
        } else {
            nextIndex = (currentIndex - 1) % GameConstants.FIVE_LANES;
        }
        this.selectedUnitIndexP1 = nextIndex;
        //TODO this.gameView.updateSelectUnit(playerType, index, next);
    }

}
