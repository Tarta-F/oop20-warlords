package controllers;

import view.GameModeSelection;
import view.ScenarioViewType;
import view.constants.ViewConstants;

public final class GameSettingsController {

    private ScenarioViewType scenario;
    private int laneNumber;
    private int timerDuration;
    private final GameModeSelection viewSettings;

    public GameSettingsController() {
        this.scenario = ScenarioViewType.SCENARIO_1;
        this.laneNumber = ViewConstants.DEFAULT_LANE;
        this.timerDuration = ViewConstants.DEFAULT_TIMER;
        this.viewSettings = new GameModeSelection(this);
    }

    public ScenarioViewType getScenario() {
        return this.scenario;
    }

    public int getLaneNumber() {
        return this.laneNumber;
    }

    public int getTimerDuration() {
        return this.timerDuration;
    }

    public void setScenario(final ScenarioViewType scenario) {
        this.scenario = scenario;
    }

    public void setLaneNumber(final int laneNumber) {
        this.laneNumber = laneNumber;
    }

    public void setTimerDuration(final int timerDuration) {
        this.timerDuration = timerDuration;
    }

    public GameModeSelection getView() {
        return this.viewSettings;
    }

    @Override
    public String toString() {
        return "SELECTED SCENARIO: " + this.scenario.getDescription() + "\n NUMBER OF LANES: " + this.laneNumber
                + "\n SELECTED TIMER: " + this.timerDuration + "MINS";
    }
}
