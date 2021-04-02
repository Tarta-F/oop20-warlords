package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import constants.ViewConstants;
import constants.ViewImages;
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
            

        //background image
        Image backgroundImg  = new Image(this.getClass().getResourceAsStream(ViewImages.GAME_TUTORIAL));
        ImageView tutorialBackGround = new ImageView(backgroundImg);
        tutorialBackGround.setFitWidth(sw / ViewConstants.DIVISOR_1_5);
        tutorialBackGround.setFitHeight(sh / ViewConstants.DIVISOR_1_5);
       

        //back button
        Button mainMenu = new Button("MAIN MENU");
        mainMenu.setPrefSize(sw / ViewConstants.DIVISOR_10, sh / ViewConstants.DIVISOR_15);
        mainMenu.setStyle(Style.BOTTONI_2);
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
       backMenu.setPadding(new Insets(0, 0 ,sh/constants.ViewConstants.DIVISOR_30 , sw/constants.ViewConstants.DIVISOR_2));
       backMenu.getChildren().add(mainMenu);
        
       BorderPane borderPane = new BorderPane ();
       borderPane.setBottom(backMenu);
       borderPane.setPrefSize(sw / ViewConstants.DIVISOR_1_5, sh / ViewConstants.DIVISOR_1_5);
       pane.getChildren().add(tutorialBackGround);
       pane.getChildren().addAll(borderPane);
       return pane;

    }





}