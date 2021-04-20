package view;

import view.constants.ViewConstants;
import view.sound.Music;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

/**
 * Confirm Box implementation.
 * */
public final class ConfirmBox {

    private static boolean answer;
    private static final double LABEL_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_7);
    private static final double LABEL_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_20);
    private static final double BUTTONS_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_25);
    private static final double BUTTONS_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_25);
    private static final double SCENE_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_4);
    private static final double SCENE_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_4);
    private static final double LAYOUT_VBOX_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_60);

    private ConfirmBox() {
        /*Not called. */
    }

    /**
     * Creation of the confirm box.
     * @param title String 
     * @param message String
     * @param button1 type
     * @param button2 type
     * @param player name
     * @return answer boolean
     * */
    public static boolean display(final String title, final String message, final String button1, final String button2, final String player) {
        /*Stage. */
        final Stage window = new Stage();

        /*Label. */
        final Label label = new Label(player + message);
        label.setAlignment(Pos.CENTER);
        label.setPrefSize(LABEL_W, LABEL_H);
        label.setStyle(Style.LABEL);

        /*Buttons. */
        /*Button YES or MAIN MENU. */
        final Button yesButton = new Button(button1);
        yesButton.setStyle(Style.BUTTON_1);
        yesButton.setPrefSize(BUTTONS_W,  BUTTONS_H);
        yesButton.setOnMouseClicked(e -> {
            Music.getMusic().playButtonSound();
            answer = true;
            window.close();
        });

        /*Button NO or QUIT. */
        final Button noButton = new Button(button2);
        noButton.setStyle(Style.BUTTON_1);
        noButton.setPrefSize(BUTTONS_W,  BUTTONS_H);
        noButton.setOnMouseClicked(e -> {
            Music.getMusic().playButtonSound();
            answer = false;
            window.close();
        });

        /*Layout. */
        final VBox layout = new VBox(LAYOUT_VBOX_H);
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle(Style.LAYOUT);

        /*Scene and stage preferences. */
        final Scene scene = new Scene(layout, SCENE_W, SCENE_H);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setScene(scene);
        window.setResizable(false);
        window.showAndWait();

        return answer;
    }

}
