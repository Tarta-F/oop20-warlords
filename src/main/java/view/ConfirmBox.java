package view;

import view.constants.ViewConstants;
import view.constants.ViewImages;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

/**
 * 
 * Class that implements the confirm box EXIT.
 * 
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
        /**Not called. */
    }

    /**
     * Creation of the confirm box.
     * @param title String 
     * @param message String
     * @return answer boolean
     * */
    public static boolean display(final String title, final String message) {


        final Stage window = new Stage();

        /**Label. */
        final Label label = new Label();
        label.setText(message);
        label.setAlignment(Pos.CENTER);
        label.setPrefSize(LABEL_W, LABEL_H);
        label.setStyle(Style.LABEL);


        /**Buttons. */
        /**Button YES. */
        final Button yesButton = new Button("YES");
        yesButton.setStyle(Style.BUTTON_1);
        yesButton.setPrefSize(BUTTONS_W,  BUTTONS_H);
        yesButton.setOnAction(e -> {
            Music.buttonsMusic(ViewImages.BUTTON_SOUND);
            answer = true;
            window.close();
        });

        /**Button NO. */
        final Button noButton = new Button("NO");
        noButton.setStyle(Style.BUTTON_1);
        noButton.setPrefSize(BUTTONS_W,  BUTTONS_H);
        noButton.setOnAction(e -> {
            Music.buttonsMusic(ViewImages.BUTTON_SOUND);
            answer = false;
            window.close();
        });


        /**Layout. */
        final VBox layout = new VBox(LAYOUT_VBOX_H);
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: grey;");


        /**Scene and stage preferences. */
        final Scene scene = new Scene(layout, SCENE_W, SCENE_H);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setScene(scene);
        window.setResizable(false);
        window.showAndWait();

        return answer;
    }
}
