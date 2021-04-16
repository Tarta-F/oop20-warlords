package controllers;

import java.util.EnumMap;
import java.util.Optional;

import constants.GameConstants;
import constants.PlayerType;
import model.FieldImpl;
import model.unit.UnitImpl;
import model.unit.UnitType;
import view.ScenarioViewType;
import view.game.GameViewImpl;

public final class ControllerImpl implements Controller {

    private final EnumMap<PlayerType, Long> lastSpawn = new EnumMap<>(PlayerType.class);
    private final EnumMap<PlayerType, Integer> selectedLane = new EnumMap<>(PlayerType.class);
    private final EnumMap<PlayerType, Integer> selectedUnit = new EnumMap<>(PlayerType.class);
    private final EnumMap<PlayerType, PlayerTimer> timers = new EnumMap<>(PlayerType.class);

    private final GameViewImpl gameView;
    private final FieldImpl field;
    private final int laneNumber;
    private Optional<PlayerType> winner;

    public ControllerImpl(final int laneNumber, final int mins, final ScenarioViewType scenario,
            final String player1Name, final String player2Name) {

        this.gameView = new GameViewImpl(laneNumber, scenario.getBackgroundPath(), scenario.getGroundPath(), player1Name, player2Name);
        this.gameView.setObserver(this);

        this.laneNumber = laneNumber;
        this.winner = Optional.empty();

        this.field = new FieldImpl(GameConstants.CELLS_NUM, laneNumber);
        new Thread(new GameTimer(mins, this.gameView)).start();

        for (final var player : PlayerType.values()) {
            this.lastSpawn.put(player, System.currentTimeMillis());
            this.selectedLane.put(player, laneNumber / 2);
            this.selectedUnit.put(player, 0);
            this.timers.put(player, new PlayerTimer(gameView, player));
        }
        this.timers.forEach((p, t) -> new Thread(t).start());

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

        //TODO PER PROVA
        this.update();
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
    public GameViewImpl getView() {
        return this.gameView;
    }

    @Override
    public void update() {
        this.field.update();
        this.gameView.show(Converter.convertMap(this.field.getUnits()));
        this.gameView.updateScorePlayer();

        //TODO PER PROVA
        System.out.println(isOver() ? getWinner().get() + " WON" : "");
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
