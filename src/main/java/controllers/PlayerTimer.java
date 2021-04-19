package controllers;

import constants.PlayerType;
import view.game.GameView;

public class PlayerTimer implements Runnable {

    private volatile int totSec;
    private volatile boolean stop;
    private final PlayerType playerType;
    private final GameView gameView;

    PlayerTimer(final GameView gameView, final PlayerType playerType) {
        this.totSec = 0;
        stop = false;
        this.playerType = playerType;
        this.gameView = gameView;
    }

    @Override
    public final void run() {
        while (totSec >= 0 && !stop) {
            try {
                this.gameView.updatePlayerTimer(this.totSec, this.playerType);
                this.totSec++;
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    /** Reset the timer (restart from 00:00). */
    public final void resetTimer() {
        this.totSec = 0;
    }

    public final void stopTimer() {
        this.stop = true;
    }
}
