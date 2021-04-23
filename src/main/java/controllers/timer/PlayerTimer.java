package controllers.timer;

import constants.PlayerType;
import view.game.GameView;

public class PlayerTimer extends AbstractTimer {

    private final PlayerType playerType;

    public PlayerTimer(final GameView gameView, final PlayerType playerType) {
        super(gameView);
        this.playerType = playerType;
    }

    @Override
    protected final void cycle() {
        super.getGameView().updatePlayerTimer((int) super.getTotSec().getAndIncrement(), this.playerType);

    }

    @Override
    protected final void timeOut() {
        super.stop();
    }
}
