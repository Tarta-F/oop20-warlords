package controllers.settings;

import constants.GameConstants;
import view.GameModeSelection;
import view.ScenarioViewType;

public final class SettingsControllerImpl implements SettingsController {

    private ScenarioViewType scenario;
    private int laneNumber;
    private int timerDuration;
    private final GameModeSelection viewSettings;

    public SettingsControllerImpl() {
        this.scenario = GameConstants.DEFAULT_SCENARIO;
        this.laneNumber = GameConstants.DEFAULT_LANE;
        this.timerDuration = GameConstants.DEFAULT_TIMER;
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
    public String toString() {
        return "SELECTED SCENARIO: " + this.scenario.getDescription() + "\n NUMBER OF LANES: " + this.laneNumber
                + "\n SELECTED TIMER: " + this.timerDuration + "MINS";
    }
}
