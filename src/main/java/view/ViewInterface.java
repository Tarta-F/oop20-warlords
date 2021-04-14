package view;

import java.io.IOException;

import javafx.scene.Parent;

/** 
 * This interface models all the Panes in the view.
 *
 */
public interface ViewInterface {

    /**
     * @return the Pane created for the scene.
     * */
    Parent createContent() throws IOException;

}
