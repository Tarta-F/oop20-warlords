package controllers.settings;

import constants.GameConstants;
import view.GameModeSelection;
import view.ScenarioViewType;

public final class SettingsControllerImpl implements SettingsController {

    private ScenarioViewType scenario;
    private int laneNumber;
    private int timerDuration;
    private final GameModeSelection viewSettings;
    private String player1Name;
    private String player2Name;

    public SettingsControllerImpl() {
        this.scenario = GameConstants.DEFAULT_SCENARIO;
        this.laneNumber = GameConstants.DEFAULT_LANE;
        this.timerDuration = GameConstants.DEFAULT_TIMER;
        this.player1Name = "Player 1";
        this.player2Name = "Player 2";
        this.viewSettings = new GameModeSelection(this);
    }

    @Override
    public ScenarioViewType getScenario() {
        return this.scenario;
    }

    @Override
    public int getLaneNumber() {
        return this.laneNumber;
    }

    @Override
    public int getTimerDuration() {
        return this.timerDuration;
    }

    @Override
    public void setScenario(final ScenarioViewType scenario) {
        this.scenario = scenario;
    }

    @Override
    public void setLaneNumber(final int laneNumber) {
        this.laneNumber = laneNumber;
    }

    @Override
    public void setTimerDuration(final int timerDuration) {
        this.timerDuration = timerDuration;
    }

    @Override
    public GameModeSelection getView() {
        return this.viewSettings;
    }
    
    @Override
    public String getPlayer1Name() {
        return this.player1Name;
    }

    @Override
    public String getPlayer2Name() {
        return this.player2Name;
    }

    @Override
    public void setPlayer1Name(final String playerName) {
        this.player1Name = playerName;
    }

    @Override
    public void setPlayer2Name(final String playerName) {
        this.player2Name = playerName;
    }

    @Override
    public String toString() {
        return "SELECTED SCENARIO: " + this.scenario.getDescription() + "\n NUMBER OF LANES: " + this.laneNumber
                + "\n SELECTED TIMER: " + this.timerDuration + "MINS";
    }
}
