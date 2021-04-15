package view;

import java.io.IOException;
import constants.ViewConstants;
import constants.ViewImages;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
public final class MainMenu extends Application {

    private GameTutorial sceneTutorial;
    private GameModeSelection sceneGameModeSelection;
    private static final double BUTTONS_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_10);
    private static final double BUTTONS_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15);
    private static final double LAYOUT_PADDING_H_1 = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_30);
    private static final double LAYOUT_PADDING_W_1 = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_15);
    private static final double BORDERPANE_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_1_3);
    private static final double BORDERPANE_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_1_3);
    public static final double PANE_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_1_3);
    public static final double PANE_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_1_3);
    private static final double LOGO_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_4);
    private static final double LOGO_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_8);
    private static final double LOGO_UNIT_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_7);
    private static final double LOGO_UNIT_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_3);
    
    
    @Override
     /**Method of the library JAVAFX used for the creation of the view. */
    public void start(final Stage primaryStage) throws Exception {
        /**Creation of the Stage, Scene and all their preferences. */
        final Stage window = primaryStage;
        final Pane pane = new Pane(createMainMenu());
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
     * Method to create the view of the current image.
     * @return borderPane borderPane
     * */
    public Parent createMainMenu() throws IOException {

        /**Pane. */
        final Pane pane = new Pane();

        /**Background and Image. */
        final Image backgroundImg  = new Image(this.getClass().getResourceAsStream(ViewImages.MENU));
        final ImageView menuBackGround = ViewResolution.createImageView(backgroundImg, PANE_W, PANE_H);

        final Image logoImage  = new Image(this.getClass().getResourceAsStream(ViewImages.LOGO));
        final ImageView logo = ViewResolution.createImageView(logoImage, LOGO_W, LOGO_H);


        final Image logoSpearmanImage  = new Image(this.getClass().getResourceAsStream(ViewImages.P2_SPEARMAN));
        final ImageView logoSpearman = ViewResolution.createImageView(logoSpearmanImage, LOGO_UNIT_W, LOGO_UNIT_H);

        final Image logoArcherImage  = new Image(this.getClass().getResourceAsStream(ViewImages.P1_ARCHER));
        final ImageView logoArcher = ViewResolution.createImageView(logoArcherImage, LOGO_UNIT_W, LOGO_UNIT_H);


        /**Buttons. */
        /**Button CAMPAIGN. */
        final Button campaign = new Button("CAMPAIGN");
        campaign.setStyle(Style.BUTTON_1);
        campaign.setPrefSize(BUTTONS_W, BUTTONS_H);

        /**Button VERSUS. */
        final Button versus = new Button("VERSUS");
        versus.setStyle(Style.BUTTON_1);
        versus.setPrefSize(BUTTONS_W, BUTTONS_H);
        versus.setOnAction(e -> {
            sceneGameModeSelection = new GameModeSelection();
            try {
                pane.getChildren().setAll(sceneGameModeSelection.createGameModeSelection());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        /**Button TUTORIALS. */
        final Button tutorials = new Button("TUTORIALS");
        tutorials.setStyle(Style.BUTTON_1);
        tutorials.setPrefSize(BUTTONS_W, BUTTONS_H);
        tutorials.setOnAction(e -> {
            try {
                sceneTutorial = new GameTutorial();
                pane.getChildren().setAll(sceneTutorial.createGameTutorial());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        /**Button EXIT. */
        final Button exitMenu = new Button("EXIT");
        exitMenu.setStyle(Style.BUTTON_1);
        exitMenu.setPrefSize(BUTTONS_W, BUTTONS_H);
        exitMenu.setOnAction(e -> closeProgram(pane));


        /**Layout. */
        final VBox menu = new VBox(ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15));
        menu.setAlignment(Pos.CENTER);
        menu.getChildren().addAll(logo, campaign, versus, tutorials, exitMenu);
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

    /**Method for the shutdown of the program.
     * @param pane Pane
     * */
    public void closeProgram(final Pane pane) {
        final boolean answer = Exit.display("quitting", "Do you want to quit?");
        if (answer) {
            final Stage stage = (Stage) pane.getScene().getWindow();
            stage.close();
            }
        }

}
