package view;

import java.io.IOException;

import constants.GameConstants;
import view.constants.ViewConstants;
import view.sound.Music;
import view.constants.ResourcesConstants;
import view.constants.Style;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * GameTutorial scene implementation.
 */
public class GameTutorial extends Region implements ViewInterface { 

    private static final double BUTTONS_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_10);
    private static final double BUTTONS_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15);
    private static final double LAYOUT_PADDING_H_1 = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_30);
    private static final double LAYOUT_PADDING_W_2 = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_60);
    private static final double LAYOUT_PADDING_H_2 = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_8);
    private static final double BORDERPANE_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_1_3);
    private static final double BORDERPANE_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_1_3);
    private static final double LABEL_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_4);
    private static final double LABEL_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_2);

    private final ViewFactory factory = new ViewFactoryImpl();

    @Override
    public final Parent createPane() throws IOException {
        /*Pane. */
        final Pane pane = new Pane();

        /*ImageView TUTORIAL. */
        final Image tutorialImg  = new Image(this.getClass().getResourceAsStream(ResourcesConstants.GAME_TUTORIAL));
        final ImageView tutorialImgView = this.factory.createImageView(tutorialImg, BORDERPANE_W, BORDERPANE_H);

        /*Button MAIN MENU. */
        final Button mainMenu = this.factory.createButton("MAIN MENU", Style.BUTTON_2, BUTTONS_W, BUTTONS_H);
        mainMenu.setOnMouseClicked(e -> {
            final MainMenu sceneMenu = new MainMenu();
            try {
                Music.getMusic().playButtonSound();
                pane.getChildren().setAll(sceneMenu.createPane());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        /*Label*/
        final Label info = this.factory.createLabel("", Style.LABEL, LABEL_W, LABEL_H);
        info.setAlignment(Pos.TOP_CENTER);
        info.setText("\n\n"
                + "Battle info:\n\n"
                + "-When the unit icon is glowing it means it is selected.\n\n"
                + "-When the arrow icon is glowing it means you selected that\n"
                + "lane as unit's spawn.\n\n"
                + "-Under the unit icon there is the spawning time.\n\n"
                + "-When the spawning time reach 0 you can spawn the unit.\n\n"
                + "-Each player has its own Score and win the game when \n" + GameConstants.SCORE_TO_WIN + " is reached.\n\n"
                + "-In order to increase your score you need to reach\n"
                + "the enemy spawn with 1 of your unit.\n\n"
                + "-At the top there is the timer.\n\n"
                + "-When the timer reach 0 the battle will result in a win or a draw.\n\n"
                + "-You win if you have more HP than the enemy.\n\n"
                + "-You draw if you have the same HP as the enemy.");

        /*Layout. */
        final VBox infoMenu = new VBox();
        infoMenu.setPadding(new Insets(LAYOUT_PADDING_H_2, LAYOUT_PADDING_W_2, 0, 0));
        infoMenu.getChildren().add(info);

        final HBox backMenu = new HBox();
        backMenu.setAlignment(Pos.CENTER);
        backMenu.setPadding(new Insets(0, 0, LAYOUT_PADDING_H_1, 0));
        backMenu.getChildren().add(mainMenu);

       /*BorderPane sets and Pane gets. */
       final BorderPane borderPane = new BorderPane();
       borderPane.setBottom(backMenu);
       borderPane.setRight(infoMenu);
       borderPane.setPrefSize(BORDERPANE_W, BORDERPANE_H);
       pane.getChildren().add(tutorialImgView);
       pane.getChildren().addAll(borderPane);

       return pane;
    }

}
