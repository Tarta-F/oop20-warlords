package controllers;

import view.ScenarioViewType;

public final class GameSettingsController {

    private ScenarioViewType scenario;
    private int laneNumber;
    private int timerDuration;

    public GameSettingsController(final ScenarioViewType scenario, final int laneNumber, final int timerDuration) {
        this.scenario = scenario;
        this.laneNumber = laneNumber;
        this.timerDuration = timerDuration;
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
}
