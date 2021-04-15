package controllers;

import constants.PlayerType;
import view.game.GameViewImpl;

public class PlayerTimer implements Runnable {

    private static final int SEC_IN_MIN = 60;
    private volatile int mins;
    private volatile int seconds;
    private volatile int totSec;
    private final PlayerType playerType;
    private final GameViewImpl gameView;

    PlayerTimer(final GameViewImpl gameView, final PlayerType playerType) {
        this.mins = 0;
        this.seconds = 0;
        this.totSec = 0;
        this.playerType = playerType;
        this.gameView = gameView;
    }

    @Override
    public final void run() {
        while (totSec >= 0) {
            try {
                this.seconds = totSec % SEC_IN_MIN;
                this.mins = (totSec - seconds) / SEC_IN_MIN;
                this.totSec++;
                this.gameView.updatePlayerTimer(this.seconds, this.playerType);
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    /** Reset the timer (restart from 00:00). */
    public final void resetTimer() {
        this.totSec = 0;
        this.mins = 0;
        this.seconds = 0;
    }
}
