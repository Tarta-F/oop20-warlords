package constants;

/**
 * This class contain the various constants that we use in the game.
 */
public final class GameConstants {

    /** Lane number for testing. */
    public static final int ONE_LANE = 1;

    /** Lane number for easy mode. */
    public static final int THREE_LANES = 3;

    /** Lane number for normal mode. */
    public static final int FOUR_LANES = 4;

    /** Lane number for hard mode. */
    public static final int FIVE_LANES = 5;

    /**Default lane number. */
    public static final int DEFAULT_LANE = FIVE_LANES;

    /**Default game timer. */
    public static final int DEFAULT_TIMER = 5;

    /** Cell number for each lane. */
    public static final int CELLS_NUM = 20;

    /** The necessary score to win. */
    public static final int SCORE_TO_WIN = 2;

    private GameConstants() { }
}
