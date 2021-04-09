package view;

import java.io.IOException;
import constants.ViewConstants;
import constants.ViewImages;
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
 * This class implements the GameTutorial scene.
 *
 */
public class GameTutorial extends Region { 

        private MainMenu sceneMenu;

        /**
         * Method to create the view of the current image. 
         * @return pane Pane
         * */
        public final Parent createGameTutorial() throws IOException {

        final Pane pane = new Pane();

        /**Background. */
        final Image backgroundImg  = new Image(this.getClass().getResourceAsStream(ViewImages.GAME_TUTORIAL));
        final ImageView tutorialBackGround = new ImageView(backgroundImg);
        tutorialBackGround.setFitWidth(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_1_5));
        tutorialBackGround.setFitHeight(ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_1_5));

        /**Button mainMenu. */
        final Button mainMenu = new Button("MAIN MENU");
        mainMenu.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_10), ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15));
        mainMenu.setStyle(Style.BUTTON_2);
        mainMenu.setOnAction(e -> {
            sceneMenu = new MainMenu();
            try {
                pane.getChildren().setAll(sceneMenu.createMainMenu());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

       /**Layout. */
       final HBox backMenu = new HBox();
       backMenu.setPadding(new Insets(0, 0, ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_30), ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_2)));
       backMenu.getChildren().add(mainMenu);

       /**BorderPane and Pane gets. */
       final BorderPane borderPane = new BorderPane();
       borderPane.setBottom(backMenu);
       borderPane.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_1_5), ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_1_5));
       pane.getChildren().add(tutorialBackGround);
       pane.getChildren().addAll(borderPane);

       return pane;
    }
}
