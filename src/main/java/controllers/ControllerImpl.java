package controllers;
//import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;

import constants.GameConstants;
import javafx.scene.layout.Pane;
import model.FieldImpl;
import model.PlayerType;
import model.UnitImpl;
import model.UnitType;
import view.GameView;

public final class ControllerImpl implements Controller {

    private int selectedLaneIndexP1;
    private int selectedLaneIndexP2;
    private int selectedUnitIndexP1;
    private int selectedUnitIndexP2;
    private final long lastSpawnP1;
    private final long lastSpawnP2;
    private final GameView gameView;
    private final FieldImpl field;
    private final int laneNumber;
    //private Pane pane;
    //TODO timer dei player da mostrare alla view ->

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
//        try {
//            this.pane = new Pane();
//            pane.getChildren().setAll(gameView.createContent());
//           } catch (IOException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//           }
        //System.out.println("NumLanes : " + laneNumber);
        this.field = new FieldImpl(GameConstants.CELLS_NUM, laneNumber);
        new Thread(new GameTimer(mins, this.gameView)).start();
    }

    @Override
    public void controlNextLane(final PlayerType playerType) {
        final int currentIndex = playerType.equals(PlayerType.PLAYER1) ? this.selectedLaneIndexP1 : this.selectedLaneIndexP2;
        final int nextIndex = (currentIndex + 1) % this.laneNumber;
        //this.selectedLaneIndexP1 = nextIndex;
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
            nextIndex = this.laneNumber - 1;
        } else {
            nextIndex = (currentIndex - 1) % this.laneNumber;
        }
        //this.selectedLaneIndexP1 = nextIndex;
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
            //TODO update variables: lastSpawnP1 || lastSpawnP2
          //gameView.show(Mappa con unità da stampare);  (?)
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
