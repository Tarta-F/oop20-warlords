package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.constants.ViewConstants;
import view.constants.ViewImages;

/**
 * 
 * Class that implements the confirm box EXIT.
 * 
 * */
public final class WinnerBox {

    private static boolean choice;
    private static final double LABEL_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_7);
    private static final double LABEL_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_20);
    private static final double BUTTONS_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_20);
    private static final double BUTTONS_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_20);
    private static final double SCENE_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_4);
    private static final double SCENE_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_4);
    private static final double LAYOUT_VBOX_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_60);
    private WinnerBox() {
        /**Not called. */
    }

    /**
     * Creation of the confirm box.
     * @param player String 
     * @return choice boolean
     * */
    public static boolean winner(final String player) {


        final Stage window = new Stage();

        /**Label. */
        final Label label = new Label();
        label.setText(player + " IS THE WINNER");
        label.setAlignment(Pos.CENTER);
        label.setPrefSize(LABEL_W, LABEL_H);
        label.setStyle(Style.LABEL);


        /**Buttons. */
        /**Button mainMenu. */
        final Button mainMenu = new Button("MAIN MENU");
        mainMenu.setStyle(Style.BUTTON_1);
        mainMenu.setPrefSize(BUTTONS_W,  BUTTONS_H);
        mainMenu.setOnAction(e -> {
            Music.buttonsMusic(ViewImages.BUTTON_SOUND);
            choice = true;
            window.close();
        });

        /**Button exit. */
        final Button exit = new Button("EXIT");
        exit.setStyle(Style.BUTTON_1);
        exit.setPrefSize(BUTTONS_W,  BUTTONS_H);
        exit.setOnAction(e -> {
            Music.buttonsMusic(ViewImages.BUTTON_SOUND);
            choice = false;
            window.close();
        });


        /**Layout. */
        final VBox layout = new VBox(LAYOUT_VBOX_H);
        layout.getChildren().addAll(label, mainMenu, exit);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: grey;");


        /**Scene and stage preferences. */
        final Scene scene = new Scene(layout, SCENE_W, SCENE_H);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("winner");
        window.setScene(scene);
        window.setResizable(false);
        window.showAndWait();

        return choice;
    }
}
