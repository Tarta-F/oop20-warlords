package view;

import java.io.IOException;

import javafx.scene.Parent;

/** 
 * This interface models all the Panes in the view.
 */
public interface ViewInterface {

    /**
     * Method to generate a PANE used for the current scene.
     * @return Pane created
     * */
    Parent createPane() throws IOException;

}
