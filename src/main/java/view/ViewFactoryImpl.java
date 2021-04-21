package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 */
public final class ViewFactoryImpl implements ViewFactory {

    public Button createButton(final String message, final String style, final double width, final double height) {
        final Button button = new Button(message);
        button.setStyle(style);
        button.setPrefSize(width, height);
        button.setAlignment(Pos.CENTER);
        return button;
    }

    public Label createLabel(final String message, final String style, final double width, final double height) {
        final Label label = new Label(message);
        label.setStyle(style);
        label.setPrefSize(width, height);
        label.setAlignment(Pos.CENTER);
        return label;
    }

    public VBox createVBox(final double width) {
        final VBox vBox = new VBox(width);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

    public HBox createHBox(final double height, final double padding) {
        final HBox hBox = createHBox(height);
        hBox.setPadding(new Insets(padding, 0, padding, 0));
        return hBox;
    }

    public HBox createHBox(final double height) {
        final HBox hBox = new HBox(height);
        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }

    public ToggleButton createToggleButton(final String message, final String style, final double width, final double height) {
        final ToggleButton button = new ToggleButton(message);
        button.setStyle(style);
        button.setPrefSize(width, height);
        button.setAlignment(Pos.CENTER);
        return button;
    }

    public  ImageView createImageView(final Image image, final double width, final double height) {
        final ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        return imageView;
    }
}
