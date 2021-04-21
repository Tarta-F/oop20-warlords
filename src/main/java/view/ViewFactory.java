package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Factory to create view elements.
 * 
 */
public interface ViewFactory {

    /**
     * @param message
     *      content of {@link Button}'s label
     * @param style
     *      CSS code style
     * @param width
     *      width of the {@link Button}
     * @param height
     *      height of the {@link Button}
     * @return
     *      a {@link Button} with the input settings
     */
    Button createButton(String message, String style, double width, double height);

    /**
     * @param message
     *      content of the {@link Label}
     * @param style
     *      CSS code style
     * @param width
     *      width of the {@link Label}
     * @param height
     *      height of the {@link Label}
     * @return
     *      a {@link Label} with the input settings
     */
    Label createLabel(String message, String style, double width, double height);

    /**
     * @param width
     *      width of the {@link VBox}
     * @return
     *      an empty {@link VBox} with the given widht
     */
    VBox createVBox(double width);

    /**
     * @param height
     *      width of the {@link HBox}
     * @param padding
     *      top and bottom offset 
     * @return
     *      an empty {@link HBox} with the given height
     */
    HBox createHBox(double height, double padding);

    /**
     * @param height
     *      width of the {@link HBox}
     * @return
     *      an empty {@link HBox} with the given height
     */
    HBox createHBox(double height);

    /**
     * @param message
     *      content of {@link ToggleButton}'s label
     * @param style
     *      CSS code style
     * @param width
     *      width of the {@link ToggleButton}
     * @param height
     *      height of the {@link ToggleButton}
     * @return
     *      a {@link ToggleButton} with the input settings
     */
    ToggleButton createToggleButton(String message, String style, double width, double height);

    /**
     * @param image
     *      image from which create the {@link ImageView}
     * @param width
     *      width of the {@link ImageView}
     * @param height
     *      height of the {@link ImageView}
     * @return
     *      a {@link ImageView} with the given dimensions
     */
    ImageView createImageView(Image image, double width, double height);

}
