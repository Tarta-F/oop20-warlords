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
    /**
     * @return the player 1 default name.
     * */
    String getPlayer1Name();

    /**
     * @return the player 2 default name.
     * */
    String getPlayer2Name();

    /**
     * @param playerName 1 new name.
     * */
    void setPlayer1Name(String playerName);

    /**
     * @param playerName 2 new name.
     * */
    void setPlayer2Name(String playerName);
}
