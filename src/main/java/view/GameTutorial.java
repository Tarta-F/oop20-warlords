package view;

import java.io.IOException;

import view.constants.ViewConstants;
import view.sound.Music;
import view.constants.ResourcesConstants;

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
 * GameTutorial scene implementation.
 */
public class GameTutorial extends Region implements ViewInterface { 

    private static final double BUTTONS_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_10);
    private static final double BUTTONS_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15);
    private static final double LAYOUT_PADDING_W_1 = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_2);
    private static final double LAYOUT_PADDING_H_1 = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_30);
    private static final double BORDERPANE_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_1_3);
    private static final double BORDERPANE_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_1_3);

    @Override
    public final Parent createPane() throws IOException {
        /*Pane. */
        final Pane pane = new Pane();

        /*Background. */
        final Image backgroundImg  = new Image(this.getClass().getResourceAsStream(ResourcesConstants.GAME_TUTORIAL));
        final ImageView tutorialBackground = ViewResolution.createImageView(backgroundImg, BORDERPANE_W, BORDERPANE_H);

        /*Button MAIN MENU. */
        final Button mainMenu = ViewFactory.createButton("MAIN MENU", Style.BUTTON_2, BUTTONS_W, BUTTONS_H);
        mainMenu.setOnMouseClicked(e -> {
            final MainMenu sceneMenu = new MainMenu();
            try {
                Music.getMusic().playButtonSound();
                pane.getChildren().setAll(sceneMenu.createPane());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

       /*Layout. */
       final HBox backMenu = new HBox();
       backMenu.setPadding(new Insets(0, 0, LAYOUT_PADDING_H_1, LAYOUT_PADDING_W_1));
       backMenu.getChildren().add(mainMenu);

       /*BorderPane sets and Pane gets. */
       final BorderPane borderPane = new BorderPane();
       borderPane.setBottom(backMenu);
       borderPane.setPrefSize(BORDERPANE_W, BORDERPANE_H);
       pane.getChildren().add(tutorialBackground);
       pane.getChildren().addAll(borderPane);

       return pane;
    }

}
