package view;

import constants.ViewConstants;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import view.Exit;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * This class is the BattleField game view.
 */
public class GameView extends Application { 

    private Stage window;

    public static void main(final String[] args) {
        launch(args);
    }

    public final void start(final Stage primaryStage) throws Exception {

        /**Taking screen size*/
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();

         window = primaryStage;

        
         // background image that adapt to the monitor resolution
         Image gameBackG = new Image(this.getClass().getResourceAsStream("/GrassBackground.jpg"));
         //size
         BackgroundSize backgroundSize = new BackgroundSize(sw/1.5, sh/1.5, false, false, false, false);
         //position
         BackgroundImage backgroundImage = new BackgroundImage(gameBackG, null, null, BackgroundPosition.CENTER, backgroundSize);
         //new background
         Background gameBackground = new Background(backgroundImage);
         
         
         
         
         
         
         /**Call for method "closeProgram".*/
         window.setOnCloseRequest(e -> {
             e.consume();
             closeProgram();
         });


        /**Set of all images used.*/
        Image unit1Image  = new Image(this.getClass().getResourceAsStream("/SwordsmenUnit.png"));
        Image unit2Image  = new Image(this.getClass().getResourceAsStream("/SpearmenUnit.png"));
        Image unit3Image = new Image(this.getClass().getResourceAsStream("/ArcherUnit.png"));
        Image unit1SelectedImage = new Image(this.getClass().getResourceAsStream("/SelectedSwordsmenUnit.png"));
        Image unit2SelectedImage = new Image(this.getClass().getResourceAsStream("/SelectedSpearmenUnit.png"));
        Image unit3SelectedImage = new Image(this.getClass().getResourceAsStream("/SelectedArcherUnit.png"));
        Image arrowImageP1 = new Image(this.getClass().getResourceAsStream("/ArrowPlayer1.png"));
        Image arrowImageP2 = new Image(this.getClass().getResourceAsStream("/ArrowPlayer2.png"));
        Image arrowSelectedImageP1 = new Image(this.getClass().getResourceAsStream("/SelectedArrowPlayer1.png"));
        Image arrowSelectedImageP2 = new Image(this.getClass().getResourceAsStream("/SelectedArrowPlayer2.png"));
        Image groundImage = new Image(this.getClass().getResourceAsStream("/Ground.png"));


        /**Set of ImageView for player 1 units.*/
        ImageView unit1player1 = new ImageView(unit1SelectedImage);
        unit1player1.setFitWidth(sw / ViewConstants.DIVISOR_20);
        unit1player1.setFitHeight(sh / ViewConstants.DIVISOR_20);

        ImageView unit2player1 = new ImageView(unit2Image);
        unit2player1.setFitWidth(sw / ViewConstants.DIVISOR_20);
        unit2player1.setFitHeight(sh / ViewConstants.DIVISOR_20);

        ImageView unit3player1 = new ImageView(unit3Image);
        unit3player1.setFitWidth(sw / ViewConstants.DIVISOR_20);
        unit3player1.setFitHeight(sh / ViewConstants.DIVISOR_20);


        /**Set of ImageView for player 2 units.*/
        ImageView unit1player2 = new ImageView(unit1SelectedImage);
        unit1player2.setFitWidth(sw / ViewConstants.DIVISOR_20);
        unit1player2.setFitHeight(sh / ViewConstants.DIVISOR_20);

        ImageView unit2player2 = new ImageView(unit2Image);
        unit2player2.setFitWidth(sw / ViewConstants.DIVISOR_20);
        unit2player2.setFitHeight(sh / ViewConstants.DIVISOR_20);

        ImageView unit3player2 = new ImageView(unit3Image);
        unit3player2.setFitWidth(sw / ViewConstants.DIVISOR_20);
        unit3player2.setFitHeight(sh / ViewConstants.DIVISOR_20);



        /**Set of ImageView for player 1 arrows.*/
        ImageView arrow1p1 = new ImageView(arrowImageP1);
        arrow1p1.setFitWidth(sw / ViewConstants.DIVISOR_20);
        arrow1p1.setFitHeight(sh / ViewConstants.DIVISOR_20);

        ImageView arrow2p1 = new ImageView(arrowImageP1);
        arrow2p1.setFitWidth(sw / ViewConstants.DIVISOR_20);
        arrow2p1.setFitHeight(sh / ViewConstants.DIVISOR_20);

        ImageView arrow3p1 = new ImageView(arrowSelectedImageP1);
        arrow3p1.setFitWidth(sw / ViewConstants.DIVISOR_20);
        arrow3p1.setFitHeight(sh / ViewConstants.DIVISOR_20);

        ImageView arrow4p1 = new ImageView(arrowImageP1);
        arrow4p1.setFitWidth(sw / ViewConstants.DIVISOR_20);
        arrow4p1.setFitHeight(sh / ViewConstants.DIVISOR_20);

        ImageView arrow5p1 = new ImageView(arrowImageP1);
        arrow5p1.setFitWidth(sw / ViewConstants.DIVISOR_20);
        arrow5p1.setFitHeight(sh / ViewConstants.DIVISOR_20);


        
        ImageView arrow1p2;
        
        int n=5;
        
        List<ImageView> listp2 = new ArrayList<>();
        
        for (int i=0; i<n; i++){
         
            arrow1p2= new ImageView(arrowImageP2);
            arrow1p2.setFitWidth(sw / ViewConstants.DIVISOR_20);
            arrow1p2.setFitHeight(sh / ViewConstants.DIVISOR_20);
            listp2.add(arrow1p2);
            
        }
        
        listp2.get(2).setImage(arrowSelectedImageP2);
        
        
        
        /**Set of ImageView for player 2 arrows.
        ImageView arrow1p2 = new ImageView(arrowImageP2);
        arrow1p2.setFitWidth(sw / ViewConstants.DIVISOR_20);
        arrow1p2.setFitHeight(sh / ViewConstants.DIVISOR_20);

        ImageView arrow2p2 = new ImageView(arrowImageP2);
        arrow2p2.setFitWidth(sw / ViewConstants.DIVISOR_20);
        arrow2p2.setFitHeight(sh / ViewConstants.DIVISOR_20);

        ImageView arrow3p2 = new ImageView(arrowSelectedImageP2);
        arrow3p2.setFitWidth(sw / ViewConstants.DIVISOR_20);
        arrow3p2.setFitHeight(sh / ViewConstants.DIVISOR_20);

        ImageView arrow4p2 = new ImageView(arrowImageP2);
        arrow4p2.setFitWidth(sw / ViewConstants.DIVISOR_20);
        arrow4p2.setFitHeight(sh / ViewConstants.DIVISOR_20);

        ImageView arrow5p2 = new ImageView(arrowImageP2);
        arrow5p2.setFitWidth(sw / ViewConstants.DIVISOR_20);
        arrow5p2.setFitHeight(sh / ViewConstants.DIVISOR_20);
         */   



        /**Creation button Exit.*/
        Button exit = new Button("Exit");
        exit.setMinSize(sw / ViewConstants.DIVISOR_30, sh / ViewConstants.DIVISOR_30);
        exit.setOnAction(e -> closeProgram());
        exit.setStyle("    -fx-text-fill: #FFFFFF;\r\n"
                + "    -fx-background-radius: 6;\r\n"
                + "    -fx-font-weight: bold;\r\n"
                +"     -fx-background-color: linear-gradient(#000000, #696969);\r\n"
                +"      -fx-font-size:"+sw/150+";");
        
        /**Creation button Menu.*/
        Button menu = new Button("Menu");
        menu.setStyle("    -fx-text-fill: #FFFFFF;\r\n"
                + "    -fx-background-radius: 6;\r\n"
                + "    -fx-font-weight: bold;\r\n"
                +"     -fx-background-color: linear-gradient(#000000, #696969);\r\n"
                +"      -fx-font-size:"+sw/150+";");
        menu.setMinSize(sw / ViewConstants.DIVISOR_30, sh / ViewConstants.DIVISOR_30);
      



        //label temporanea(qui ci andrebbe il timer)
        Label timer = new Label("TIMER");
        timer.setStyle("    -fx-text-fill: #FFFFFF;\r\n"
                + "    -fx-background-radius: 6;\r\n"
                + "    -fx-font-weight: bold;\r\n"
                + "    -fx-background-color: rgba(0, 0, 0, 0.5);\r\n"
                +"      -fx-font-size:"+sw/150+";");

        timer.setPrefHeight(sh / ViewConstants.DIVISOR_20);
        timer.setPrefWidth(sw / ViewConstants.DIVISOR_15);
        timer.setAlignment(Pos.CENTER);


        //HP player1
        int HP1=8;
        Label player1 = new Label("PLAYER 1 HP: " + HP1);
        player1.setStyle("    -fx-text-fill: #FFFFFF;\r\n"
                + "    -fx-background-radius: 6;\r\n"
                + "    -fx-font-weight: bold;\r\n"
                + "    -fx-background-color: rgba(0, 0, 0, 0.5);\r\n"
                +"      -fx-font-size:"+sw/150+";");

        player1.setPrefHeight(sh / ViewConstants.DIVISOR_20);
        player1.setPrefWidth(sw / ViewConstants.DIVISOR_15);
        player1.setAlignment(Pos.CENTER);


        //HP player2
        int HP2=8;
        Label player2 = new Label("PLAYER 2 HP: " + HP2);
        player2.setStyle("    -fx-text-fill: #FFFFFF;\r\n"
                + "    -fx-background-radius: 6;\r\n"
                + "    -fx-font-weight: bold;\r\n"
                + "    -fx-background-color: rgba(0, 0, 0, 0.5);\r\n"
                +"      -fx-font-size:"+sw/150+";");

        player2.setPrefHeight(sh / ViewConstants.DIVISOR_20);
        player2.setPrefWidth(sw / ViewConstants.DIVISOR_15);
        player2.setAlignment(Pos.CENTER);


        //layout
        HBox topMenu = new HBox(sw / ViewConstants.DIVISOR_25);
        topMenu.setAlignment(Pos.CENTER);
        topMenu.getChildren().addAll(unit1player1, unit2player1, unit3player1, timer, unit1player2, unit2player2, unit3player2);
        topMenu.setPadding(new Insets(sh / ViewConstants.DIVISOR_30, 0, 0, 0));

        HBox bottomMenu = new HBox(sw / ViewConstants.DIVISOR_30);
        bottomMenu.getChildren().addAll(player1, menu, exit, player2);
        bottomMenu.setAlignment(Pos.CENTER);

        VBox leftMenu = new VBox(sh / ViewConstants.DIVISOR_20);
        leftMenu.setAlignment(Pos.CENTER);
        leftMenu.getChildren().addAll(arrow1p1, arrow2p1, arrow3p1, arrow4p1, arrow5p1);

        VBox rightMenu = new VBox(sh / ViewConstants.DIVISOR_20);
        rightMenu.setAlignment(Pos.CENTER);
        rightMenu.getChildren().addAll(listp2);



        /**Creation of the grid.*/
        GridPane gridPane = new GridPane();
        for (int i = 0; i < ViewConstants.GRID_COLUMNS; i++) {
            for (int j = 0; j < ViewConstants.GRID_LINES; j++) {
                ImageView ground = new ImageView(groundImage);
                ground.setFitWidth(sw / ViewConstants.DIVISOR_27);
                ground.setFitHeight(sh /ViewConstants.DIVISOR_10);
                GridPane.setConstraints(ground, i, j);
                gridPane.getChildren().add(ground);
            }
        }
        gridPane.setAlignment(Pos.CENTER);



        /**Set of the position of the elements.*/
        BorderPane borderpane = new BorderPane();
        borderpane.setId("background");
        borderpane.setTop(topMenu);
        borderpane.setLeft(leftMenu);
        borderpane.setBottom(bottomMenu);
        borderpane.setRight(rightMenu);
        borderpane.setCenter(gridPane);
        borderpane.setBackground(gameBackground);
        Scene scene = new Scene(borderpane, sw / ViewConstants.DIVISOR_1_5, sh / ViewConstants.DIVISOR_1_5);
        window.setScene(scene);
        window.show();
        window.setResizable(false);
    }

    /**Method to close the program with a confirm box.*/
    private void closeProgram() {
    Boolean answer = Exit.display("quitting", "Do you want to quit?");
    if (answer) {
          window.close();
        }
    }

}