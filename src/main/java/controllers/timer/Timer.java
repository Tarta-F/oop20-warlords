package controllers.timer;

public interface Timer extends Runnable {

    /**
     * Number of milliseconds in one seconds.
     */
    long ONE_SECOND = 1000L;

    void stop();

    void run();

    void reset();
}
