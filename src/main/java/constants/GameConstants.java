package constants;

import view.ScenarioViewType;

/**
 * This class contain the various constants that we use in the game.
 */
public final class GameConstants {

    /** Cell number for each lane. */
    public static final int CELLS_NUM = 20;

    /** The necessary score to win. */
    public static final int SCORE_TO_WIN = 5;

    /** Minimum minutes of game duration. */
    public static final int MIN_TIMER = 1;

    /** Difference (in minute) between different game duration settings. */
    public static final int TIMER_STEP = 2;

    /** Maximum minutes of game duration. */
    public static final int TIMER_MAX = 5;

    /**Minimum lane number. */
    public static final int MIN_LANE = 1;

    /** Max lane number. */
    public static final int MAX_LANE = 5;

    /** Difference between lane number settings. */
    public static final int LANE_STEP = 2;

    /**Default lane number. */
    public static final int DEFAULT_LANE = MAX_LANE;

    /**Default Scenario Type. */
    public static final ScenarioViewType DEFAULT_SCENARIO = ScenarioViewType.SCENARIO_1;

    /**Default game timer. */
    public static final int DEFAULT_TIMER = 3;

    private GameConstants() { }
}
