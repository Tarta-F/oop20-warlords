package controllers;

public class GameLoopImpl implements GameLoop {

    private final Controller controller;

    public GameLoopImpl(final Controller controller) {
        this.controller = controller;
    }

    @Override
    public final void startGame() {
        while (controller.isOver()) {
            controller.update();
        }
    }

}
