package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import constants.ViewConstants;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import view.Exit;
import javafx.scene.layout.*;

public class GameTutorial extends Application { 

    private Stage window;

    public static void main(final String[] args) {
        launch(args);
    }


    public final void start(final Stage primaryStage) throws Exception {

        //screen size
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();

        window = primaryStage;

        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
         });

        
        // background image that adapt to the monitor resolution
        Image gameSettingsImage = new Image(this.getClass().getResourceAsStream("/GameSettingsTUTORIAL.png"));
        //size
        BackgroundSize backgroundSize = new BackgroundSize(sw/ViewConstants.DIVISOR_1_5, sh/ViewConstants.DIVISOR_1_5, false, false, false, false);
        //position
        BackgroundImage backgroundImage = new BackgroundImage(gameSettingsImage, null, null, BackgroundPosition.CENTER, backgroundSize);
        //new background
        Background background = new Background(backgroundImage);


       

        //back button
        Button mainMenu = new Button("MAIN MENU");
        mainMenu.setPrefSize(sw / ViewConstants.DIVISOR_10, sh / ViewConstants.DIVISOR_15);
        mainMenu.setStyle("    -fx-text-fill: #000000;\r\n"
                + "    -fx-background-radius: 6;\r\n"
                + "    -fx-font-weight: bold;\r\n"
                + "     -fx-background-color: linear-gradient(#FFFFFF, #696969);\r\n"
                + "      -fx-font-size:"+sw/constants.ViewConstants.DIVISOR_150+";");
     
        
        //layout
       HBox backMenu = new HBox();
       //backMenu.setAlignment(Pos.CENTER);
       backMenu.setPadding(new Insets(0, 0 ,sh/constants.ViewConstants.DIVISOR_30 , sw/constants.ViewConstants.DIVISOR_2));
       backMenu.getChildren().add(mainMenu);
        
       BorderPane borderPane = new BorderPane ();
       borderPane.setBottom(backMenu);
       borderPane.setBackground(background);
        Scene sceneTutorials = new Scene(borderPane,sw/ ViewConstants.DIVISOR_1_5, sh / ViewConstants.DIVISOR_1_5 );
        window.setScene(sceneTutorials);
        window.show();
        window.setResizable(false);

    }

    /**Method to close the program with a confirm box.*/
    private void closeProgram() {
    boolean answer = Exit.display("quitting", "Do you want to quit?");
    if (answer) {
          window.close();
        }
    }




}