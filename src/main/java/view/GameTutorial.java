package view;

import java.io.IOException;

import view.constants.ViewConstants;
import view.constants.ViewImages;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

/**
 * 
 * This class implements the GameTutorial Pane.
 *
 */
public class GameTutorial extends Region implements ViewInterface { 

    private MainMenu sceneMenu;
    private static final double BUTTONS_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_10);
    private static final double BUTTONS_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15);
    private static final double LAYOUT_PADDING_W_1 = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_2);
    private static final double LAYOUT_PADDING_H_1 = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_30);
    private static final double BORDERPANE_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_1_3);
    private static final double BORDERPANE_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_1_3);

    @Override
    public final Parent createPane() throws IOException {

        /**Pane. */
        final Pane pane = new Pane();

        /**Background. */
        final Image backgroundImg  = new Image(this.getClass().getResourceAsStream(ViewImages.GAME_TUTORIAL));
        final ImageView tutorialBackground = ViewResolution.createImageView(backgroundImg, BORDERPANE_W, BORDERPANE_H);


        /**Button MAIN MENU. */
        final Button mainMenu = new Button("MAIN MENU");
        mainMenu.setPrefSize(BUTTONS_W, BUTTONS_H);
        mainMenu.setStyle(Style.BUTTON_2);
        mainMenu.setOnAction(e -> {
            sceneMenu = new MainMenu();
            try {
                Music.buttonsMusic(ViewImages.BUTTON_SOUND);
                pane.getChildren().setAll(sceneMenu.createPane());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });


       /**Layout. */
       final HBox backMenu = new HBox();
       backMenu.setPadding(new Insets(0, 0, LAYOUT_PADDING_H_1, LAYOUT_PADDING_W_1));
       backMenu.getChildren().add(mainMenu);


       /**BorderPane sets and Pane gets. */
       final BorderPane borderPane = new BorderPane();
       borderPane.setBottom(backMenu);
       borderPane.setPrefSize(BORDERPANE_W, BORDERPANE_H);
       pane.getChildren().add(tutorialBackground);
       pane.getChildren().addAll(borderPane);

       return pane;
    }
}
