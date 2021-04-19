package view;

import java.io.IOException;

import view.constants.ViewConstants;
import view.constants.ResourcesConstants;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

/**
 * GameTutorial scene implementation.
 */
public class Scoreboard extends Region implements ViewInterface { 

    private static final double BUTTONS_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_10);
    private static final double BUTTONS_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15);
    private static final double LAYOUT_PADDING_W_1 = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_2);
    private static final double LAYOUT_PADDING_H_1 = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_30);
    private static final double BORDERPANE_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_1_3);
    private static final double BORDERPANE_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_1_3);
    private static final double LISTVIEW_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_2);
    private static final double LISTVIEW_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_2);

    @Override
    public final Parent createPane() throws IOException {
        /*Pane. */
        final Pane pane = new Pane();

        /*Background. */
        final Image backgroundImg  = new Image(this.getClass().getResourceAsStream(ResourcesConstants.GAME_SETTINGS));
        final ImageView scoreboardBackground = ViewResolution.createImageView(backgroundImg, BORDERPANE_W, BORDERPANE_H);

        /*Button MAIN MENU. */
        final Button mainMenu = new Button("MAIN MENU");
        mainMenu.setPrefSize(BUTTONS_W, BUTTONS_H);
        mainMenu.setStyle(Style.BUTTON_2);
        mainMenu.setOnMouseClicked(e -> {
            final MainMenu sceneMenu = new MainMenu();
            try {
                Music.buttonsMusic(ResourcesConstants.BUTTON_SOUND);
                pane.getChildren().setAll(sceneMenu.createPane());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        /*ListView. */
        final ListView<String> listView = new ListView<>();
        listView.getItems().addAll("prova", "prova2");
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.setMaxSize(LISTVIEW_W, LISTVIEW_H);
        listView.setStyle(Style.LABEL);

        /*Layout. */
        final HBox backMenu = new HBox();
        backMenu.setPadding(new Insets(0, 0, LAYOUT_PADDING_H_1, LAYOUT_PADDING_W_1));
        backMenu.getChildren().add(mainMenu);

        /*BorderPane sets and Pane gets. */
        final BorderPane borderPane = new BorderPane();
        borderPane.setBottom(backMenu);
        borderPane.setCenter(listView);
        borderPane.setPrefSize(BORDERPANE_W, BORDERPANE_H);
        pane.getChildren().add(scoreboardBackground);
        pane.getChildren().addAll(borderPane);

        return pane;
    }

}
