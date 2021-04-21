package controllers;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Optional;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import constants.GameConstants;
import constants.PlayerType;
import controllers.io.IOController;
import controllers.io.IOControllerImpl;
import model.Field;
import model.FieldImpl;
import model.Score;
import model.ScoreImpl;
import model.unit.UnitImpl;
import model.unit.UnitType;
import view.game.GameView;
import view.ScenarioViewType;
import view.game.GameViewImpl;

public final class ControllerImpl implements Controller {

    private static final long REFRESH_RATE = 500;
    private boolean timerIsOver;
    private final int laneNumber;
    private final EnumMap<PlayerType, Long> lastSpawn = new EnumMap<>(PlayerType.class);
    private final EnumMap<PlayerType, Integer> selectedLane = new EnumMap<>(PlayerType.class);
    private final EnumMap<PlayerType, Integer> selectedUnit = new EnumMap<>(PlayerType.class);
    private final EnumMap<PlayerType, PlayerTimer> timers = new EnumMap<>(PlayerType.class);
    private final GameView gameView;
    private final Field field;
    private Optional<PlayerType> winner;
    private final ScheduledThreadPoolExecutor thrEx;
    private final GameLoopImpl gameLoop;
    private final GameTimer gameTimer;
    private final Score score;
    private final IOController ioContr;
    private final String player1Name;
    private final String player2Name;

    public ControllerImpl(final int laneNumber, final int mins, final ScenarioViewType scenario,
            final String player1Name, final String player2Name) {

        this.gameView = new GameViewImpl(laneNumber, scenario.getBackgroundPath(), scenario.getGroundPath(), player1Name, player2Name);
        this.gameView.setObserver(this);
        this.laneNumber = laneNumber;
        this.winner = Optional.empty();
        this.field = new FieldImpl(GameConstants.CELLS_NUM, laneNumber);
        this.gameTimer = new GameTimer(mins, this.gameView);
        this.score = new ScoreImpl(player1Name, player2Name);
        this.ioContr = new IOControllerImpl();
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        new Thread(this.gameTimer).start();
        this.initPlayers();
        this.timers.forEach((p, t) -> new Thread(t).start());
        /* Instance GameLoop */
        this.gameLoop = new GameLoopImpl(this);
        this.thrEx = new ScheduledThreadPoolExecutor(1);
        this.startLoop();
    }

    /**
     * Utility method that inizialize variables for each Player.
     */
    private void initPlayers() {
        for (final var player : PlayerType.values()) {
            this.lastSpawn.put(player, System.currentTimeMillis());
            this.selectedLane.put(player, laneNumber / 2);
            this.selectedUnit.put(player, 0);
            this.timers.put(player, new PlayerTimer(gameView, player));
        }
    }
    /**
     * Repeat the {@link #update()} after {@link #REFRESH_RATE} seconds.
     */
    private void startLoop() {
        this.thrEx.scheduleWithFixedDelay(gameLoop, 0, REFRESH_RATE, TimeUnit.MILLISECONDS);
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
        this.lastSpawn.put(playerType, System.currentTimeMillis());
    }

    /**
     * Resets the given player respawn timer.
     * @param player to reset the timer
     */
    private void resetPlayerTimer(final PlayerType player) {
        this.timers.get(player).resetTimer();
    }

    @Override
    public Optional<PlayerType> getWinner() {
        return this.winner;
    }

    @Override
    public int getScore(final PlayerType player) {
        return this.field.getScore(player).orElseGet(() -> Integer.valueOf(0));
    }

    @Override
    public GameView getView() {
        return this.gameView;
    }

    @Override
    public void setTimerIsOver() {
        this.timerIsOver = true;
    }

    @Override
    public void spawnUnit(final PlayerType player) {
      final int unitIndex = this.selectedUnit.get(player);
      final long timeWaited = System.currentTimeMillis() - this.lastSpawn.get(player);
      final UnitType unitToSpawn = UnitType.values()[unitIndex];
      if (timeWaited > unitToSpawn.getTimer()) {
        this.setSpawnTime(player);
        final int lane = this.selectedLane.get(player);
        this.field.addUnit(lane, new UnitImpl(unitToSpawn, player));
        gameView.show(Converter.convertMap(this.field.getUnits()));
        this.resetPlayerTimer(player);
      }
    }

    @Override
    public void controlNextLane(final PlayerType player) {
        final int currentIndex = this.selectedLane.get(player);
        final int nextIndex = (currentIndex + 1) % this.laneNumber;
        this.selectedLane.put(player, nextIndex);
        this.gameView.updateSelectLane(player, currentIndex, nextIndex);
    }

    @Override
    public void controlPrevLane(final PlayerType player) {
        final int currentIndex = this.selectedLane.get(player);
        final int nextIndex = currentIndex == 0 ? this.laneNumber - 1 : (currentIndex - 1) % this.laneNumber;
        this.selectedLane.put(player, nextIndex);
        this.gameView.updateSelectLane(player, currentIndex, nextIndex);
    }

    @Override
    public void controlNextUnit(final PlayerType player) {
        final int currentIndex = this.selectedUnit.get(player);
        final int nextIndex = (currentIndex + 1) % UnitType.getUnitNumber();
        this.selectedUnit.put(player, nextIndex);
        this.gameView.updateSelectUnit(player, currentIndex, nextIndex);
    }

    @Override
    public void controlPrevUnit(final PlayerType player) {
        final int currIndex = this.selectedUnit.get(player);
        final int nextIndex = currIndex == 0 ? UnitType.getUnitNumber() - 1 : (currIndex - 1) % UnitType.getUnitNumber();
        this.selectedUnit.put(player, nextIndex);
        this.gameView.updateSelectUnit(player, currIndex, nextIndex);
    }

    @Override
    public void update() {
        this.field.update();
        this.gameView.show(Converter.convertMap(this.field.getUnits()));
        this.gameView.updateScorePlayer();
        if (this.isOver()) {
            this.gameView.winnerBoxResult(this.gameView.getPlayerName(getWinner().get()));
            this.stopGame();
            try {
                this.ioContr.writeNewScore(new ScoreImpl(this.player1Name, this.player2Name, 0, 2));
            } catch (IOException e) {
                e.printStackTrace();
            }
                //System.out.println(isOver() ? getWinner().get() + " WON" : "");
        }
    }

    @Override
    public boolean isOver() {
        return this.hasWin(PlayerType.PLAYER1) || this.hasWin(PlayerType.PLAYER2) || this.timerIsOver;
    }

    @Override
    public void stopGame() {
        this.timers.forEach((p, t) -> t.stopTimer());
        this.gameTimer.stopTimer();
        this.thrEx.shutdown();
    }

    @Override
    public void timeOut() {
        // TODO Auto-generated method stub
    }
}
/*else if ("00:00".equals(this.gameView.getTimer())) {
if (this.getScore(PlayerType.PLAYER1) < this.getScore(PlayerType.PLAYER2)) {
    this.gameView.winnerBoxResult(this.gameView.getPlayerName(PlayerType.PLAYER2));
} else if (this.getScore(PlayerType.PLAYER1) == this.getScore(PlayerType.PLAYER2)) {
    this.gameView.winnerBoxResult("DRAW!");
} else {
    this.gameView.winnerBoxResult(this.gameView.getPlayerName(PlayerType.PLAYER1));
}
}*/

