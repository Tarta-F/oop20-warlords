package controllers.settings;

import view.GameModeSelection;
import view.ScenarioViewType;

public interface SettingsController {

    /**
     * @return the {@link ScenarioViewType} currently selected.
     */
    ScenarioViewType getScenario();

    /**
     * @return The number of lane currently selected.
     */
    int getLaneNumber();

    /**
     * @return The game timer duration currently selected.
     */
    int getTimerDuration();

    /**
     * Set the current selected scenario at the input value.
     * @param scenario
     */
    void setScenario(ScenarioViewType scenario);

    /**
     * Set the current lane's number at the input value.
     * @param laneNumber 
     */
    void setLaneNumber(int laneNumber);

    /**
     * Set the current game timer duration at the input value.
     * @param timerDuration int
     */
    void setTimerDuration(int timerDuration);

    /**
     * @return the related view.
     */
    GameModeSelection getView();

}
