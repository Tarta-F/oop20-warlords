package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public interface ViewFactory {

    Button createButton(String message, String style, double width, double height);

    Label createLabel(String message, String style, double width, double height);

    VBox createVBox(double width);

    HBox createHBox(double height, double padding);

    HBox createHBox(double height);

    ToggleButton createToggleButton(String message, String style, double width, double height);

    ImageView createImageView(Image image, double width, double height);
}
