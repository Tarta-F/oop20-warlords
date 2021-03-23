package application;

import javafx.application.Application;
import view.MainMenu;

/**
 * This class represents the Launcher of the system, to bypass JAVA 11 modules constraints.
 */
public final class Launcher {

    private Launcher() { }

    /**
     * @param args unused
     */
    public static void main(final String[] args) {
        Application.launch(MainMenu.class);
    }
}
