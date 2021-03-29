package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import constants.ViewConstants;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
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

public class GameTutorial extends Region { 

        
        private MainMenu scenaMenu;
    
        //screen size
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();



        public Parent createContent() throws IOException {
       
         Pane pane = new Pane();   
            
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
        mainMenu.setOnAction(e -> {
            scenaMenu = new MainMenu();
            
            try {
                pane.getChildren().setAll(scenaMenu.createContent());
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        
        //layout
       HBox backMenu = new HBox();
       //backMenu.setAlignment(Pos.CENTER);
       backMenu.setPadding(new Insets(0, 0 ,sh/constants.ViewConstants.DIVISOR_30 , sw/constants.ViewConstants.DIVISOR_2));
       backMenu.getChildren().add(mainMenu);
        
       BorderPane borderPane = new BorderPane ();
       borderPane.setBottom(backMenu);
       borderPane.setBackground(background);
       borderPane.setPrefSize(sw / ViewConstants.DIVISOR_1_5, sh / ViewConstants.DIVISOR_1_5);
       pane.getChildren().addAll(borderPane);
       return pane;

    }





}