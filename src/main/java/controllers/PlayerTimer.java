package controllers;

import view.GameView;

public class PlayerTimer implements Runnable {

    private static final int SEC_IN_MIN = 60;
    private volatile int mins;
    private volatile int seconds;
    private volatile int totSec;
    private final GameView gameView;

    PlayerTimer(final GameView gameView) {
        this.mins = 0;
        this.seconds = 0;
        this.totSec = 0;
        this.gameView = gameView;
    }

    @Override
    public final void run() {
        // TODO Auto-generated method stub
        while (totSec >= 0) {
            try {
                this.seconds = totSec % SEC_IN_MIN;
                this.mins = (totSec - seconds) / SEC_IN_MIN;
                this.totSec++;
                this.gameView.updatePlayerTimer(this.mins, this.seconds);
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    public final void resetTimer() {
        this.totSec = 0;
        this.mins = 0;
        this.seconds = 0;
    }
}
