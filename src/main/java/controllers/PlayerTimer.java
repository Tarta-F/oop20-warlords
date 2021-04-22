package controllers;

import java.util.concurrent.atomic.AtomicLong;

import constants.PlayerType;
import view.game.GameView;

public class PlayerTimer implements Runnable {

    private volatile AtomicLong totSec;
    private volatile boolean stop;
    private final PlayerType playerType;
    private final GameView gameView;

    PlayerTimer(final GameView gameView, final PlayerType playerType) {
        this.totSec = new AtomicLong(0L);
        stop = false;
        this.playerType = playerType;
        this.gameView = gameView;
    }

    @Override
    public final void run() {
        while (this.totSec.get() >= 0 && !stop) {
            try {
                Thread.sleep(1000);
                this.gameView.updatePlayerTimer((int) this.totSec.incrementAndGet(), this.playerType);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    /* Reset the timer (restart from 00:00). */
    public final void resetTimer() {
        this.totSec.set(0L);
    }

    public final void stopTimer() {
        this.stop = true;
    }
}
