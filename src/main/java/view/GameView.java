package view;

import constants.ViewConstants;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * This class is the BattleField game view.
 */
public class GameView extends Region { 

   private MainMenu scenaMenu;
    
    
    
    /**Taking screen size for the adaptation of the various elements of the view to the resolution of the screen.*/
    final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    final int sw = (int) screen.getWidth();
    final int sh = (int) screen.getHeight();


    private int counterLaneP1 = 2;
    private int counterLaneP2 = 2;
    private int counterUnitP1 = 0;
    private int counterUnitP2 = 0;

    private List<ImageView> listArrowP1 = new ArrayList<>();
    private List<ImageView> listArrowP2 = new ArrayList<>();
    private List<ImageView> listUnitP1 = new ArrayList<>();
    private List<ImageView> listUnitP2 = new ArrayList<>();



    public Parent createContent() throws IOException {



        Pane pane = new Pane();
        
        //background image
        Image backgroundImg  = new Image(this.getClass().getResourceAsStream("/GrassBackground.jpg"));
        ImageView gameBackGround = new ImageView(backgroundImg);
        gameBackGround.setFitWidth(sw / ViewConstants.DIVISOR_1_5);
        gameBackGround.setFitHeight(sh / ViewConstants.DIVISOR_1_5);

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
       
        BackgroundSize backgroundSize = new BackgroundSize(sw / ViewConstants.DIVISOR_1_5,sh / ViewConstants.DIVISOR_1_5, true, true, true, false);
    
        BackgroundImage backgroundImage = new BackgroundImage(groundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

        Background background = new Background(backgroundImage);
        
        
        
        
        ImageView unitP1 = new ImageView(unit1SelectedImage);
        unitP1.setFitWidth(sw / ViewConstants.DIVISOR_20);
        unitP1.setFitHeight(sh / ViewConstants.DIVISOR_20);
        listUnitP1.add(unitP1);
 
        unitP1 = new ImageView(unit2Image);
        unitP1.setFitWidth(sw / ViewConstants.DIVISOR_20);
        unitP1.setFitHeight(sh / ViewConstants.DIVISOR_20);
        listUnitP1.add(unitP1);
 
        unitP1 = new ImageView(unit3Image);
        unitP1.setFitWidth(sw / ViewConstants.DIVISOR_20);
        unitP1.setFitHeight(sh / ViewConstants.DIVISOR_20);
        listUnitP1.add(unitP1);
 

        ImageView unitP2 = new ImageView(unit1SelectedImage);
        unitP2.setFitWidth(sw / ViewConstants.DIVISOR_20);
        unitP2.setFitHeight(sh / ViewConstants.DIVISOR_20);
        listUnitP2.add(unitP2);
 
        unitP2 = new ImageView(unit2Image);
        unitP2.setFitWidth(sw / ViewConstants.DIVISOR_20);
        unitP2.setFitHeight(sh / ViewConstants.DIVISOR_20);
        listUnitP2.add(unitP2);
 
        unitP2 = new ImageView(unit3Image);
        unitP2.setFitWidth(sw / ViewConstants.DIVISOR_20);
        unitP2.setFitHeight(sh / ViewConstants.DIVISOR_20);
        listUnitP2.add(unitP2);


        /**List of ImageView arrows for the player 1*/
        ImageView arrow1P1;

        for (int i = 0; i < ViewConstants.N_ARROW; i++) {
            arrow1P1 = new ImageView(arrowImageP1);
            arrow1P1.setFitWidth(sw / ViewConstants.DIVISOR_20);
            arrow1P1.setFitHeight(sh / ViewConstants.DIVISOR_20);
            listArrowP1.add(arrow1P1);
        }
        listArrowP1.get(2).setImage(arrowSelectedImageP1);


        /**List of ImageView arrows for the player 1*/
        ImageView arrow1P2;

        for (int i = 0; i < ViewConstants.N_ARROW; i++) {
            arrow1P2 = new ImageView(arrowImageP2);
            arrow1P2.setFitWidth(sw / ViewConstants.DIVISOR_20);
            arrow1P2.setFitHeight(sh / ViewConstants.DIVISOR_20);
            listArrowP2.add(arrow1P2);
        }
        listArrowP2.get(2).setImage(arrowSelectedImageP2);



        /**Creation button Exit.*/
        Button exit = new Button("Exit");
        exit.setMinSize(sw / ViewConstants.DIVISOR_30, sh / ViewConstants.DIVISOR_30);
        exit.setOnAction(e -> closeProgram());
        exit.setStyle(Style.BOTTONI_1);

        /**Creation button Menu.*/
        Button menu = new Button("Menu");
        menu.setStyle(Style.BOTTONI_1);
        menu.setMinSize(sw / ViewConstants.DIVISOR_30, sh / ViewConstants.DIVISOR_30);
        menu.setOnAction(e ->{
            scenaMenu = new MainMenu();
            try {
                pane.getChildren().setAll(scenaMenu.createContent());
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });



        //label temporanea(qui ci andrebbe il timer)
        Label timer = new Label("TIMER");
        timer.setStyle(Style.LABEL);
        timer.setPrefHeight(sh / ViewConstants.DIVISOR_20);
        timer.setPrefWidth(sw / ViewConstants.DIVISOR_15);
        timer.setAlignment(Pos.CENTER);


        //HP player1
        int HP1=8;
        Label player1 = new Label("PLAYER 1 HP: " + HP1);
        player1.setStyle(Style.LABEL);

        player1.setPrefHeight(sh / ViewConstants.DIVISOR_20);
        player1.setPrefWidth(sw / ViewConstants.DIVISOR_15);
        player1.setAlignment(Pos.CENTER);


        //HP player2
        int HP2=8;
        Label player2 = new Label("PLAYER 2 HP: " + HP2);
        player2.setStyle(Style.LABEL);
        player2.setPrefHeight(sh / ViewConstants.DIVISOR_20);
        player2.setPrefWidth(sw / ViewConstants.DIVISOR_15);
        player2.setAlignment(Pos.CENTER);


        /**Layout of the GameView.*/
        HBox topMenu = new HBox(sw / ViewConstants.DIVISOR_25);
        topMenu.setAlignment(Pos.CENTER);
        topMenu.getChildren().addAll(listUnitP1);
        topMenu.getChildren().addAll(timer);
        topMenu.getChildren().addAll(listUnitP2);
        topMenu.setPadding(new Insets(sh / ViewConstants.DIVISOR_60, 0, sh /ViewConstants.DIVISOR_60, 0));

        HBox bottomMenu = new HBox(sw / ViewConstants.DIVISOR_30);
        bottomMenu.getChildren().addAll(player1, menu, exit, player2);
        bottomMenu.setAlignment(Pos.CENTER);
        bottomMenu.setPadding(new Insets(sh / ViewConstants.DIVISOR_60, 0, sh /ViewConstants.DIVISOR_60, 0));

        VBox leftMenu = new VBox(sh / ViewConstants.DIVISOR_20);
        leftMenu.setAlignment(Pos.CENTER);
        leftMenu.getChildren().addAll(listArrowP1);

        VBox rightMenu = new VBox(sh / ViewConstants.DIVISOR_20);
        rightMenu.setAlignment(Pos.CENTER);
        rightMenu.getChildren().addAll(listArrowP2);



        /**Creation of the grid.*/
        GridPane gridPane = new GridPane();
        gridPane.setBackground(background);
        for (int i = 0; i < ViewConstants.GRID_COLUMNS; i++) {
            for (int j = 0; j < ViewConstants.GRID_LINES; j++) {
                ImageView vuoto = new ImageView();
                vuoto.setFitWidth(sw / ViewConstants.DIVISOR_27);
                vuoto.setFitHeight(sh / ViewConstants.DIVISOR_10);
                GridPane.setConstraints(vuoto, i, j);
                gridPane.getChildren().add(vuoto);
            }
        }
        gridPane.setAlignment(Pos.CENTER);



        /**Set position of the various elements created.*/
        BorderPane borderpane = new BorderPane();
        borderpane.setTop(topMenu);
        borderpane.setLeft(leftMenu);
        borderpane.setBottom(bottomMenu);
        borderpane.setRight(rightMenu);
        borderpane.setCenter(gridPane);
        borderpane.setPrefSize(sw / ViewConstants.DIVISOR_1_5, sh / ViewConstants.DIVISOR_1_5);


        borderpane.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            switch (e.getCode()) {
            case W:
                if (counterLaneP1 == 0) {
                    listArrowP1.get(counterLaneP1).setImage(arrowImageP1);
                    counterLaneP1 = listArrowP1.size() - 1;
                    listArrowP1.get(counterLaneP1).setImage(arrowSelectedImageP1);
                } else {
                    listArrowP1.get(counterLaneP1).setImage(arrowImageP1);
                    counterLaneP1--;
                    listArrowP1.get(counterLaneP1).setImage(arrowSelectedImageP1);
                }
                break;
            case S:
                if (counterLaneP1 == 4) {
                    listArrowP1.get(counterLaneP1).setImage(arrowImageP1);
                    counterLaneP1 -= listArrowP1.size() - 1;
                    listArrowP1.get(counterLaneP1).setImage(arrowSelectedImageP1);
                } else {
                    listArrowP1.get(counterLaneP1).setImage(arrowImageP1);
                    counterLaneP1++;
                    listArrowP1.get(counterLaneP1).setImage(arrowSelectedImageP1);
                }
                break;
            case A:
                if (counterUnitP1 == 0) {
                    listUnitP1.get(counterUnitP1).setImage(unit1Image);
                    counterUnitP1 = listUnitP1.size() - 1;
                    listUnitP1.get(counterUnitP1).setImage(unit3SelectedImage);
                } else if (counterUnitP1 == 1) {
                    listUnitP1.get(counterUnitP1).setImage(unit2Image);
                    counterUnitP1--;
                    listUnitP1.get(counterUnitP1).setImage(unit1SelectedImage);
                } else {
                    listUnitP1.get(counterUnitP1).setImage(unit3Image);
                    counterUnitP1--;
                    listUnitP1.get(counterUnitP1).setImage(unit2SelectedImage);
                }
                break;
            case D:
                if (counterUnitP1 == 2) {
                    listUnitP1.get(counterUnitP1).setImage(unit3Image);
                    counterUnitP1 -= listUnitP1.size() - 1;
                    listUnitP1.get(counterUnitP1).setImage(unit1SelectedImage);
                } else if (counterUnitP1 == 1) {
                    listUnitP1.get(counterUnitP1).setImage(unit2Image);
                    counterUnitP1++;
                    listUnitP1.get(counterUnitP1).setImage(unit3SelectedImage);
                } else {
                    listUnitP1.get(counterUnitP1).setImage(unit1Image);
                    counterUnitP1++;
                    listUnitP1.get(counterUnitP1).setImage(unit2SelectedImage);
                }
                break;
            case UP:
                if (counterLaneP2 == 0) {
                    listArrowP2.get(counterLaneP2).setImage(arrowImageP2);
                    counterLaneP2 = listArrowP2.size() - 1;
                    listArrowP2.get(counterLaneP2).setImage(arrowSelectedImageP2);
                } else {
                    listArrowP2.get(counterLaneP2).setImage(arrowImageP2);
                    counterLaneP2--;
                    listArrowP2.get(counterLaneP2).setImage(arrowSelectedImageP2);
                }
                break;
            case DOWN:
                if (counterLaneP2 == 4) {
                    listArrowP2.get(counterLaneP2).setImage(arrowImageP2);
                    counterLaneP2 -= listArrowP2.size() - 1;
                    listArrowP2.get(counterLaneP2).setImage(arrowSelectedImageP2);
                } else {
                    listArrowP2.get(counterLaneP2).setImage(arrowImageP2);
                    counterLaneP2++;
                    listArrowP2.get(counterLaneP2).setImage(arrowSelectedImageP2);
                }
                break;
            case LEFT:
                if (counterUnitP2 == 0) {
                    listUnitP2.get(counterUnitP2).setImage(unit1Image);
                    counterUnitP2 = listUnitP2.size() - 1;
                    listUnitP2.get(counterUnitP2).setImage(unit3SelectedImage);
                } else if (counterUnitP2 == 1) {
                    listUnitP2.get(counterUnitP2).setImage(unit2Image);
                    counterUnitP2--;
                    listUnitP2.get(counterUnitP2).setImage(unit1SelectedImage);
                } else {
                    listUnitP2.get(counterUnitP2).setImage(unit3Image);
                    counterUnitP2--;
                    listUnitP2.get(counterUnitP2).setImage(unit2SelectedImage);
                }
                break;
            case RIGHT:
                if (counterUnitP2 == 2) {
                    listUnitP2.get(counterUnitP2).setImage(unit3Image);
                    counterUnitP2 -= listUnitP2.size() - 1;
                    listUnitP2.get(counterUnitP2).setImage(unit1SelectedImage);
                } else if (counterUnitP2 == 1) {
                    listUnitP2.get(counterUnitP2).setImage(unit2Image);
                    counterUnitP2++;
                    listUnitP2.get(counterUnitP2).setImage(unit3SelectedImage);
                } else {
                    listUnitP2.get(counterUnitP2).setImage(unit1Image);
                    counterUnitP2++;
                    listUnitP2.get(counterUnitP2).setImage(unit2SelectedImage);
                }
                break;
            default:
                break;
            }
        });
        pane.getChildren().add(gameBackGround);
        pane.getChildren().add(borderpane);
        return pane;
    }
    
    /**Method to close the program with a confirm box.*/
    private void closeProgram() {
    boolean answer = Exit.display("quitting", "Do you want to quit?");
    if (answer) {
        System.exit(0);
        }
    }


}
