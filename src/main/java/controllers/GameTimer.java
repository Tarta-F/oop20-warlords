package controllers;

import view.GameView;

public class GameTimer implements Runnable {

    private static final int SEC_IN_MIN = 60;
    private volatile int mins;
    private volatile int seconds;
    private volatile int totSec;
    private final GameView gameView;

    GameTimer(final int mins, final GameView gameView) {
        this.mins = mins;
        this.seconds = 0;
        this.totSec = this.mins * SEC_IN_MIN;
        this.gameView = gameView;
    }

    @Override
    public final void run() {
        while (totSec >= 0) {
            try {
                seconds = totSec % SEC_IN_MIN;
                mins = (totSec - seconds) / SEC_IN_MIN;
                totSec--;  /*[warning spotbugs] but it's safe because there is only 1 timer*/
                this.gameView.updateTimer(mins, seconds);
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        //TODO block/finish the game
    }
}
