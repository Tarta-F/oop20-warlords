package controllers;

import java.util.Optional;

import constants.GameConstants;
import model.FieldImpl;
import model.PlayerType;
import model.UnitImpl;
import model.UnitType;
import view.GameViewImpl;

public final class ControllerImpl implements Controller {

    private int selectedLaneIndexP1;
    private int selectedLaneIndexP2;
    private int selectedUnitIndexP1;
    private int selectedUnitIndexP2;
    private long lastSpawnP1;
    private long lastSpawnP2;
    private final GameViewImpl gameView;
    private final FieldImpl field;
    private final int laneNumber;
    private Optional<PlayerType> winner;
    private final PlayerTimer playerTimer;
    private final PlayerTimer playerTimer2;

    public ControllerImpl(final int laneNumber, final int mins, final String background, final String ground, 
            final String player1Name, final String player2Name) {
        this.lastSpawnP1 = 0;
        this.lastSpawnP2 = 0;

        this.gameView = new GameViewImpl(laneNumber, background, ground, player1Name, player2Name);
        this.gameView.setObserver(this);

        this.laneNumber = laneNumber;
        this.selectedLaneIndexP1 = this.laneNumber / 2;
        this.selectedLaneIndexP2 = this.laneNumber / 2;
        this.winner = Optional.empty();
        this.playerTimer = new PlayerTimer(this.gameView, PlayerType.PLAYER1);
        this.playerTimer2 = new PlayerTimer(this.gameView, PlayerType.PLAYER2);

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
        if (this.getScore(player) == GameConstants.SCORE_TO_WIN) {
            this.winner = Optional.ofNullable(player);
            return true;
        }
        return false;
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

    /**
     * Resets the given player respawn timer.
     * @param player to reset the timer
     */
    private void resetPlayerTimer(final PlayerType player) {
        if (player.equals(PlayerType.PLAYER1)) {
            this.playerTimer.resetTimer();
        } else {
            this.playerTimer2.resetTimer();
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
            gameView.show(Converter.convertMap(this.field.getUnits()));
            this.resetPlayerTimer(playerType);
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
    public GameViewImpl getView() {
        return this.gameView;
    }

    @Override
    public void update() {
        this.field.update();
        this.gameView.show(Converter.convertMap(this.field.getUnits()));

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
