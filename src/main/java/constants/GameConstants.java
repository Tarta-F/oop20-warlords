package constants;

/**
 * This class contain the various constants that we use in the game.
 */
public final class GameConstants {

    /** Lane number for practice. */
    public static final int ONE_LANE = 1;

    /** Lane number for easy mode. */
    public static final int THREE_LANES = 3;

    /** Lane number for hard mode. */
    public static final int FIVE_LANES = 5;

    /**Default lane number. */
    public static final int DEFAULT_LANE = FIVE_LANES;

    /**Default game timer. */
    public static final int DEFAULT_TIMER = 3;

    /** Cell number for each lane. */
    public static final int CELLS_NUM = 20;

    /** The necessary score to win. */
    public static final int SCORE_TO_WIN = 2;

    /** Minimum minutes of game duration. */
    public static final int MIN_TIMER = 1;

    /** Difference (in minute) between different game duration settings. */
    public static final int TIMER_STEP = 2;

    /** Maximum minutes of game duration. */
    public static final int TIMER_MAX = 5;

    private GameConstants() { }
}
