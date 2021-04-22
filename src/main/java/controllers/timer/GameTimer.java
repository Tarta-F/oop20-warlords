package controllers;

import java.util.concurrent.atomic.AtomicLong;

import view.game.GameView;

public class GameTimer implements Runnable {

    private static final int SEC_IN_MIN = 60;
    private static final long SEC = 1000L;
    private volatile int mins;
    private volatile int seconds;
    private volatile AtomicLong totSec;
    private volatile boolean stop;
    private final GameView gameView;
    private final Controller controller;

    GameTimer(final int mins, final GameView gameView, final Controller controller) {
        this.mins = mins;
        this.seconds = 0;
        this.totSec = new AtomicLong(this.mins * SEC_IN_MIN);
        this.stop = false;
        this.gameView = gameView;
        this.controller = controller;
    }

    @Override
    public final void run() {
        while (this.totSec.get() >= 0 && !stop) {
            try {
                this.seconds = this.totSec.intValue() % SEC_IN_MIN;
                this.mins = (this.totSec.intValue() - seconds) / SEC_IN_MIN;
                this.totSec.decrementAndGet();
                this.gameView.updateTimer(this.mins, this.seconds);
                Thread.sleep(SEC);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        /* Notify the controller that timer countdown is over. */
        this.controller.setTimerIsOver();
    }

    public final void stopTimer() {
        this.stop = true;
    }
}
