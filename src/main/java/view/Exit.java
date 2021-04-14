package view;

import constants.ViewConstants;

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
public final class Exit {

    private static boolean answer;

    private Exit() {
        /**Not called. */
    }

    /**
     * Creation of the confirm box.
     * @param title String 
     * @param message String
     * @return answer boolean
     * */
    public static boolean display(final String title, final String message) {

        /**Stage. */
        final Stage window = new Stage();

        /**Label. */
        final Label label = new Label();
        label.setText(message);
        label.setAlignment(Pos.CENTER);
        label.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_7), 
                ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_20));
        label.setStyle(Style.LABEL);


        /**Buttons. */
        /**Button YES. */
        final Button yesButton = new Button("YES");
        yesButton.setStyle(Style.BUTTON_1);
        yesButton.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_25), 
                ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_25));
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        /**Button NO. */
        final Button noButton = new Button("NO");
        noButton.setStyle(Style.BUTTON_1);
        noButton.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_25), 
                ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_25));
        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });


        /**Layout. */
        final VBox layout = new VBox(ViewConstants.DIVISOR_10);
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: grey;");


        /**Scene and stage preferences. */
        final Scene scene = new Scene(layout, ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_4), 
                ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_4));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setScene(scene);
        window.setResizable(false);
        window.showAndWait();

        return answer;
    }
}
