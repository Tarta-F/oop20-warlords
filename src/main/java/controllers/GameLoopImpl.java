package controllers;

public class GameLoopImpl implements Runnable {

    private final Controller controller;

    public GameLoopImpl(final Controller controller) {
        this.controller = controller;
    }

    @Override
    public final void run() {
        if (!controller.isOver()) {
            controller.update();
        }
    }

}
