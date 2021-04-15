package view;

import java.io.IOException;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import view.constants.ViewConstants;
import view.constants.ViewImages;

/**
 * 
 * This class implements the GameTutorial Pane.
 *
 */
public class GameTutorial extends Region implements ViewInterface { 

        private MainMenu sceneMenu;

        /**
         * Method to create the view of the current image. 
         * @return pane Pane
         * */
        public final Parent createContent() throws IOException {

        /**Pane. */
        final Pane pane = new Pane();


        /**Background. */
        final Image backgroundImg  = new Image(this.getClass().getResourceAsStream(ViewImages.GAME_TUTORIAL));
        final ImageView tutorialBackGround = new ImageView(backgroundImg);
        tutorialBackGround.setFitWidth(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_1_3));
        tutorialBackGround.setFitHeight(ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_1_3));


        /**Button MAIN MENU. */
        final Button mainMenu = new Button("MAIN MENU");
        mainMenu.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_10), 
                ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15));
        mainMenu.setStyle(Style.BUTTON_2);
        mainMenu.setOnAction(e -> {
            sceneMenu = new MainMenu();
            try {
                pane.getChildren().setAll(sceneMenu.createContent());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });


       /**Layout. */
       final HBox backMenu = new HBox();
       backMenu.setPadding(new Insets(0, 0, ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_30), 
               ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_2)));
       backMenu.getChildren().add(mainMenu);


       /**BorderPane sets and Pane gets. */
       final BorderPane borderPane = new BorderPane();
       borderPane.setBottom(backMenu);
       borderPane.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_1_3), 
               ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_1_3));
       pane.getChildren().add(tutorialBackGround);
       pane.getChildren().addAll(borderPane);

       return pane;
    }
}
