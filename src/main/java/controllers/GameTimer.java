package controllers;

import view.game.GameView;

public class GameTimer implements Runnable {

    private static final int SEC_IN_MIN = 60;
    private volatile int mins;
    private volatile int seconds;
    private volatile int totSec;
    private volatile boolean stop;
    private final GameView gameView;
    private final Controller controller;

    GameTimer(final int mins, final GameView gameView, final Controller controller) {
        this.mins = mins;
        this.seconds = 0;
        this.totSec = this.mins * SEC_IN_MIN;
        this.stop = false;
        this.gameView = gameView;
        this.controller = controller;
    }

    @Override
    public final void run() {
        while (totSec >= 0 && !stop) {
            try {
                this.seconds = totSec % SEC_IN_MIN;
                this.mins = (totSec - seconds) / SEC_IN_MIN;
                this.totSec--;  /*[warning spotbugs] but it's safe because there is only 1 thread that use this variable*/
                this.gameView.updateTimer(this.mins, this.seconds);
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        //TODO block/finish the game Funzione che guarda punteggio: vincitore o pareggio.
        this.controller.setTimerIsOver();
    }

    public final void stopTimer() {
        this.stop = true;
    }
}
