package view;

import java.io.IOException;
import view.constants.ViewConstants;
import view.constants.ResourcesConstants;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * This class implements the Main Menu of the view and is used as unique window for the view.
 *
 */
public final class MainMenu extends Application implements ViewInterface, ViewClose {

    private GameTutorial sceneTutorial;
    private GameModeSelection sceneGameModeSelection;
    private Scoreboard scenesScoreboard;
    private static final double BUTTONS_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_10);
    private static final double BUTTONS_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15);
    private static final double LAYOUT_PADDING_H_1 = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_30);
    private static final double LAYOUT_PADDING_W_1 = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_15);
    private static final double BORDERPANE_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_1_3);
    private static final double BORDERPANE_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_1_3);
    private static final double PANE_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_1_3);
    private static final double PANE_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_1_3);
    private static final double LOGO_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_4);
    private static final double LOGO_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_8);
    private static final double LOGO_UNIT_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_7);
    private static final double LOGO_UNIT_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_3);
    private static final double VBOX_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_20);

    @Override
     /**Method of the library JAVAFX used for the creation of the view. */
    public void start(final Stage primaryStage) throws Exception {

        /**Creation of the Stage, Scene and all their preferences. */
        Music.musicStart(ResourcesConstants.MUSIC);
        final Stage window = primaryStage;
        final Pane pane = new Pane(createPane());
        final Scene scene = new Scene(pane, PANE_W, PANE_H);
        window.setScene(scene);
        window.show();
        window.setResizable(false);
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram(pane);
        });
    }

    /**
     * Method to create the Pane of MainMenu.
     * @return pane Pane
     * */
    public Parent createPane() throws IOException {

        /**Pane. */
        final Pane pane = new Pane();

        /**Background and Image. */
        final Image backgroundImg  = new Image(this.getClass().getResourceAsStream(ResourcesConstants.MENU));
        final ImageView menuBackGround = ViewResolution.createImageView(backgroundImg, PANE_W, PANE_H);

        final Image logoImage  = new Image(this.getClass().getResourceAsStream(ResourcesConstants.LOGO));
        final ImageView logo = ViewResolution.createImageView(logoImage, LOGO_W, LOGO_H);

        final Image logoSpearmanImage  = new Image(this.getClass().getResourceAsStream(ResourcesConstants.P2_SPEARMAN));
        final ImageView logoSpearman = ViewResolution.createImageView(logoSpearmanImage, LOGO_UNIT_W, LOGO_UNIT_H);

        final Image logoArcherImage  = new Image(this.getClass().getResourceAsStream(ResourcesConstants.P1_ARCHER));
        final ImageView logoArcher = ViewResolution.createImageView(logoArcherImage, LOGO_UNIT_W, LOGO_UNIT_H);

        /**Buttons. */
        /**Button SCOREBOARD. */
        final Button scoreboard = new Button("SCOREBOARD");
        scoreboard.setStyle(Style.BUTTON_1);
        scoreboard.setPrefSize(BUTTONS_W, BUTTONS_H);
        scoreboard.setOnMouseClicked(e -> {
            scenesScoreboard = new Scoreboard();
            Music.buttonsMusic(ResourcesConstants.BUTTON_SOUND);
            try {
                pane.getChildren().setAll(scenesScoreboard.createPane());
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        /**Button VERSUS. */
        final Button versus = new Button("VERSUS");
        versus.setStyle(Style.BUTTON_1);
        versus.setPrefSize(BUTTONS_W, BUTTONS_H);
        versus.setOnMouseClicked(e -> {
            sceneGameModeSelection = new GameModeSelection();
            try {
                Music.buttonsMusic(ResourcesConstants.BUTTON_SOUND);
                pane.getChildren().setAll(sceneGameModeSelection.createPane());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        /**Button TUTORIALS. */
        final Button tutorials = new Button("TUTORIALS");
        tutorials.setStyle(Style.BUTTON_1);
        tutorials.setPrefSize(BUTTONS_W, BUTTONS_H);
        tutorials.setOnMouseClicked(e -> {

            try {
                Music.buttonsMusic(ResourcesConstants.BUTTON_SOUND);
                sceneTutorial = new GameTutorial();
                pane.getChildren().setAll(sceneTutorial.createPane());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        /**Button MUSIC. */
        final ToggleButton stopMusic = new ToggleButton("MUSIC ON/OFF");
        stopMusic.setStyle(Style.BUTTON_1);
        stopMusic.setPrefSize(BUTTONS_W, BUTTONS_H);
        stopMusic.setOnMouseClicked(e -> {
            if (stopMusic.isSelected()) {
                Music.buttonsMusic(ResourcesConstants.BUTTON_SOUND);
                Music.musicStop();
            } else {
                Music.buttonsMusic(ResourcesConstants.BUTTON_SOUND);
                Music.musicStart(ResourcesConstants.MUSIC);
            }
        });

        /**Button EXIT. */
        final Button exitMenu = new Button("EXIT");
        exitMenu.setStyle(Style.BUTTON_1);
        exitMenu.setPrefSize(BUTTONS_W, BUTTONS_H);
        exitMenu.setOnMouseClicked(e -> {
            Music.buttonsMusic(ResourcesConstants.BUTTON_SOUND);
            closeProgram(pane);
        });

        /**Layout. */
        final VBox menu = new VBox(VBOX_H);
        menu.setAlignment(Pos.CENTER);
        menu.getChildren().addAll(logo, versus, scoreboard, tutorials, stopMusic, exitMenu);
        menu.setPadding(new Insets(LAYOUT_PADDING_H_1, 0, LAYOUT_PADDING_H_1, 0));

        final VBox leftVBox = new VBox();
        leftVBox.setAlignment(Pos.CENTER);
        leftVBox.getChildren().add(logoArcher);
        leftVBox.setPadding(new Insets(0, 0, 0, LAYOUT_PADDING_W_1));

        final VBox rigthVBox = new VBox();
        rigthVBox.setAlignment(Pos.CENTER);
        rigthVBox.getChildren().add(logoSpearman);
        rigthVBox.setPadding(new Insets(0, LAYOUT_PADDING_W_1, 0, 0));

        /**BorderPane sets and Pane gets. */
        final BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(BORDERPANE_W, BORDERPANE_H);
        borderPane.setCenter(menu);
        borderPane.setLeft(leftVBox);
        borderPane.setRight(rigthVBox);
        pane.getChildren().add(menuBackGround);
        pane.getChildren().addAll(borderPane);

        return pane;
    }

    @Override
    /**Method for the shutdown of the program.
     * @param pane Pane
     * */
    public void closeProgram(final Pane pane) {
        final boolean answer = ConfirmBox.display("quitting", "Do you want to quit?", "YES", "NO", "");
        if (answer) {
            final Stage stage = (Stage) pane.getScene().getWindow();
            stage.close();
            }
        }

}
