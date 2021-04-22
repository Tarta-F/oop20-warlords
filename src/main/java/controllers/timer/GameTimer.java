package controllers.timer;

import controllers.Controller;
import view.game.GameView;

public class GameTimer extends AbstractTimer {

    private static final int SEC_IN_MIN = 60;
    private volatile int mins;
    private volatile int seconds;
    private final Controller controller;

    public GameTimer(final int mins, final GameView gameView, final Controller controller) {
        super(gameView);
        super.setTotSec(mins * SEC_IN_MIN);
        this.mins = mins;
        this.seconds = 0;
        this.controller = controller;
    }

    @Override
    protected final void cycle() {
        this.seconds = super.getIntTotSec() % SEC_IN_MIN;
        this.mins = (super.getIntTotSec() - seconds) / SEC_IN_MIN;
        super.getTotSec().decrementAndGet();
        this.getGameView().updateTimer(this.mins, this.seconds);
    }

    @Override
    protected final void timeOut() {
        super.stop();
        this.controller.setTimerIsOver();
    }

}
