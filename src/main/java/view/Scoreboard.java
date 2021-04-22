package view;

import java.io.IOException;
import java.util.List;
import controllers.io.IOController;
import controllers.io.IOControllerImpl;
import view.constants.ViewConstants;
import view.sound.Music;
import view.constants.ResourcesConstants;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private static final double LAYOUT_PADDING_H_1 = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_30);
    private static final double BORDERPANE_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_1_3);
    private static final double BORDERPANE_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_1_3);
    private static final double LISTVIEW_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_2);
    private static final double LISTVIEW_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_2);
    private static final double LABEL_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_7);
    private static final double LABEL_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15);

    private final IOController ioController;
    private final ViewFactory factory;

    public Scoreboard() {
        this.ioController = new IOControllerImpl();
        this.factory = new ViewFactoryImpl();
    }

    @Override
    public final Parent createPane() throws IOException {

        /* Gettings scores from I/O Controller */
        final List<String> resultsList = this.ioController.readScore();

        /*Pane. */
        final Pane pane = new Pane();

        /*Background. */
        final Image backgroundImg  = new Image(this.getClass().getResourceAsStream(ResourcesConstants.GAME_SETTINGS));
        final ImageView scoreboardBackground = this.factory.createImageView(backgroundImg, BORDERPANE_W, BORDERPANE_H);

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

        /*Label. */
        final Label results = this.factory.createLabel("RESULTS", Style.LABEL, LABEL_W, LABEL_H);

        /*Creating scoreboard table */
        final ListView<String> scoreboard = new ListView<>();
        /* Inserting scores in the Table */
        resultsList.forEach(res -> scoreboard.getItems().add(res));
        scoreboard.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        scoreboard.setMaxSize(LISTVIEW_W, LISTVIEW_H);
        scoreboard.setStyle(Style.FONT);
        scoreboard.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> e.consume());

        /*Layout. */
        final HBox backMenu = new HBox();
        backMenu.setAlignment(Pos.CENTER);
        backMenu.setPadding(new Insets(0, 0, LAYOUT_PADDING_H_1, 0));
        backMenu.getChildren().add(mainMenu);

        final HBox topMenu = new HBox();
        topMenu.setAlignment(Pos.CENTER);
        topMenu.setPadding(new Insets(LAYOUT_PADDING_H_1, 0, 0, 0));
        topMenu.getChildren().add(results);

        /*BorderPane sets and Pane gets. */
        final BorderPane borderPane = new BorderPane();
        borderPane.setTop(topMenu);
        borderPane.setBottom(backMenu);
        borderPane.setCenter(scoreboard);

        borderPane.setPrefSize(BORDERPANE_W, BORDERPANE_H);
        pane.getChildren().add(scoreboardBackground);
        pane.getChildren().addAll(borderPane);

        return pane;
    }

}
