package view;

import java.io.IOException;

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
import view.constants.ViewConstants;
import view.constants.ViewImages;

/**
 *
 * This class implements the Main Menu of the view and is used as unique window for the view.
 *
 */
public final class MainMenu extends Application implements ViewInterface, ViewClose {

    private GameTutorial sceneTutorial;
    private GameModeSelection sceneGameModeSelection;

    @Override
    /**Method of the library JAVAFX used for the creation of the view. */
    public void start(final Stage primaryStage) throws Exception {

        /**Creation of the Stage, Scene and all their preferences. */
        final Stage window = primaryStage;
        final Pane pane = new Pane(createContent());
        final Scene scene = new Scene(pane, ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_1_3), 
                ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_1_3));
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
    public Parent createContent() throws IOException {

        /**Pane. */
        final Pane pane = new Pane();


        /**Background and Image. */
        final Image backgroundImg  = new Image(this.getClass().getResourceAsStream(ViewImages.MENU));
        final ImageView menuBackGround = new ImageView(backgroundImg);
        menuBackGround.setFitWidth(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_1_3));
        menuBackGround.setFitHeight(ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_1_3));

        final Image logoImage  = new Image(this.getClass().getResourceAsStream(ViewImages.LOGO));
        final ImageView logo = new ImageView(logoImage);
        logo.setFitWidth(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_4));
        logo.setFitHeight(ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_8));

        final Image logoSpearmanImage  = new Image(this.getClass().getResourceAsStream(ViewImages.P2_SPEARMAN));
        final ImageView logoSpearman = new ImageView(logoSpearmanImage);
        logoSpearman.setFitWidth(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_7));
        logoSpearman.setFitHeight(ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_3));

        final Image logoArcherImage  = new Image(this.getClass().getResourceAsStream(ViewImages.P1_ARCHER));
        final ImageView logoArcher = new ImageView(logoArcherImage);
        logoArcher.setFitWidth(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_7));
        logoArcher.setFitHeight(ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_3));


        /**Buttons. */
        /**Button CAMPAIGN. */
        final Button campaign = new Button("CAMPAIGN");
        campaign.setStyle(Style.BUTTON_1);
        campaign.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_15), 
                ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15));

        /**Button VERSUS. */
        final Button versus = new Button("VERSUS");
        versus.setStyle(Style.BUTTON_1);
        versus.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_15), 
                ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15));
        versus.setOnAction(e -> {
            sceneGameModeSelection = new GameModeSelection();
            try {
                pane.getChildren().setAll(sceneGameModeSelection.createContent());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        /**Button TUTORIAL. */
        final Button tutorial = new Button("TUTORIAL");
        tutorial.setStyle(Style.BUTTON_1);
        tutorial.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_15), 
                ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15));
        tutorial.setOnAction(e -> {
            try {
                sceneTutorial = new GameTutorial();
                pane.getChildren().setAll(sceneTutorial.createContent());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        /**Button EXIT. */
        final Button exitMenu = new Button("EXIT");
        exitMenu.setStyle(Style.BUTTON_1);
        exitMenu.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_15), 
                ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15));
        exitMenu.setOnAction(e -> closeProgram(pane));


        /**Layout. */
        final VBox menu = new VBox(ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15));
        menu.setAlignment(Pos.CENTER);
        menu.getChildren().addAll(logo, campaign, versus, tutorial, exitMenu);
        menu.setPadding(new Insets(ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_30), 0, 
                ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_30), 0));

        final VBox leftVBox = new VBox();
        leftVBox.setAlignment(Pos.CENTER);
        leftVBox.getChildren().add(logoArcher);
        leftVBox.setPadding(new Insets(0, 0, 0, ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_15)));

        final VBox rigthVBox = new VBox();
        rigthVBox.setAlignment(Pos.CENTER);
        rigthVBox.getChildren().add(logoSpearman);
        rigthVBox.setPadding(new Insets(0, ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_15), 0, 0));


        /**BorderPane sets and Pane gets. */
        final BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_1_3), 
                ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_1_3));
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
