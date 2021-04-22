package controllers.timer;

import java.util.concurrent.atomic.AtomicLong;

import view.game.GameView;

public abstract class AbstractTimer implements Timer {
    private volatile AtomicLong totSec;
    private volatile boolean stop;
    private final GameView gameView;

    public AbstractTimer(final GameView gameView) {
        this.stop = false;
        this.gameView = gameView;
        this.totSec = new AtomicLong();
    }

    protected abstract void cycle();

    protected abstract void timeOut();

    protected final AtomicLong getTotSec() {
        return this.totSec;
    }
    protected final int getIntTotSec() {
        return this.totSec.intValue();
    }

    protected final void setTotSec(final long newValue) {
        this.totSec.set(newValue);
    }

    protected final boolean isStop() {
        return stop;
    }

    protected final GameView getGameView() {
        return gameView;
    }

    @Override
    public final void stop() {
        this.stop = true;
    }

    @Override
    public final void run() {
        while (this.totSec.get() >= 0 && !stop) {
            try {
                cycle();
                Thread.sleep(Timer.ONE_SECOND);
//                TimeUnit.SECONDS.convert(this.totSec.get(), TimeUnit.MINUTES);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        timeOut();
    }

    @Override
    public final void reset() {
        this.totSec.set(0L);
    }
}
