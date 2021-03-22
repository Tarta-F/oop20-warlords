package view;

import java.awt.Dimension;
import java.awt.Toolkit;

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

public class GameView extends Application{ 

    Stage window;


    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
         

        //screen size
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();  
         
         window = primaryStage;
         int A = 20;
         
         window.setOnCloseRequest(e->{
             e.consume();                //fa gestire la chiusura al nostro metodo closeProgram
             closeProgram();
         });
         
         
         
         
         
         //set imageView
        
        Image unit1Image  = new Image(this.getClass().getResourceAsStream("/prova.jpg"));
        Image unit2Image  = new Image(this.getClass().getResourceAsStream("/prova.jpg"));
        Image unit3Image = new Image(this.getClass().getResourceAsStream("/prova.jpg"));
        Image arrowImageDX = new Image(this.getClass().getResourceAsStream("/freccia_destra.png"));
        Image arrowImageSX = new Image(this.getClass().getResourceAsStream("/freccia_sinistra.png"));
        Image groundImage = new Image(this.getClass().getResourceAsStream("/ground.jpg"));

      
        



        
        
        //units player1
        ImageView unit1player1 = new ImageView(unit1Image);
        unit1player1.setFitWidth(sw/A);
        unit1player1.setFitHeight(sh/A);
        
       
        ImageView unit2player1 = new ImageView(unit2Image);
        unit2player1.setFitWidth(sw/A);
        unit2player1.setFitHeight(sh/A);
        
        ImageView unit3player1 = new ImageView(unit3Image);
        unit3player1.setFitWidth(sw/A);
        unit3player1.setFitHeight(sh/A);
      
        //units player 2
        
        ImageView unit1player2 = new ImageView(unit1Image);
        unit1player2.setFitWidth(sw/A);
        unit1player2.setFitHeight(sh/A);
       
        ImageView unit2player2 = new ImageView(unit2Image);
        unit2player2.setFitWidth(sw/A);
        unit2player2.setFitHeight(sh/A);
        
        ImageView unit3player2 = new ImageView(unit3Image);
        unit3player2.setFitWidth(sw/A);
        unit3player2.setFitHeight(sh/A);
        
        //arrows player1
        ImageView arrow1p1 = new ImageView(arrowImageDX);
        arrow1p1.setFitWidth(sw/A);
        arrow1p1.setFitHeight(sh/A);
        
        ImageView arrow2p1 = new ImageView(arrowImageDX);
        arrow2p1.setFitWidth(sw/A);
        arrow2p1.setFitHeight(sh/A);
        
        ImageView arrow3p1 = new ImageView(arrowImageDX);
        arrow3p1.setFitWidth(sw/A);
        arrow3p1.setFitHeight(sh/A);
        
        ImageView arrow4p1 = new ImageView(arrowImageDX);
        arrow4p1.setFitWidth(sw/A);
        arrow4p1.setFitHeight(sh/A);
        
        ImageView arrow5p1 = new ImageView(arrowImageDX);
        arrow5p1.setFitWidth(sw/A);
        arrow5p1.setFitHeight(sh/A);
        
        
        //arrow player 2
        ImageView arrow1p2 = new ImageView(arrowImageSX);
        arrow1p2.setFitWidth(sw/A);
        arrow1p2.setFitHeight(sh/A);
        
        ImageView arrow2p2 = new ImageView(arrowImageSX);
        arrow2p2.setFitWidth(sw/A);
        arrow2p2.setFitHeight(sh/A);
        
        ImageView arrow3p2 = new ImageView(arrowImageSX);
        arrow3p2.setFitWidth(sw/A);
        arrow3p2.setFitHeight(sh/A);
        
        ImageView arrow4p2 = new ImageView(arrowImageSX);
        arrow4p2.setFitWidth(sw/A);
        arrow4p2.setFitHeight(sh/A);
        
        ImageView arrow5p2 = new ImageView(arrowImageSX);
        arrow5p2.setFitWidth(sw/A);
        arrow5p2.setFitHeight(sh/A);
        
        //button
        
        Button esci = new Button("Exit");
        esci.setMinSize(sw/30, sh/30);
        esci.setOnAction(e -> closeProgram());
       
        

        
        //label
        
        Label prova = new Label("zac negro");
        prova.setPrefHeight(sh/15);
        prova.setPrefWidth(sw/10);
        prova.setAlignment(Pos.CENTER);

        
        //layout        
        
        
        HBox topMenu = new HBox(sw/25);
        topMenu.setAlignment(Pos.CENTER);
        topMenu.getChildren().addAll(unit1player1, unit2player1, unit3player1, prova, unit1player2, unit2player2, unit3player2);
        topMenu.setPadding(new Insets(sh/30,0, 0,0));
        
        HBox bottomMenu = new HBox();
        bottomMenu.getChildren().add(esci);
        bottomMenu.setAlignment(Pos.BASELINE_RIGHT);
        
        VBox leftMenu = new VBox(sh/20);
        leftMenu.setAlignment(Pos.CENTER);
        leftMenu.getChildren().addAll(arrow1p1,arrow2p1,arrow3p1,arrow4p1,arrow5p1);
        
        VBox rightMenu = new VBox(sh/20);
        rightMenu.setAlignment(Pos.CENTER);
        rightMenu.getChildren().addAll(arrow1p2,arrow2p2,arrow3p2,arrow4p2,arrow5p2);
        
        GridPane gridPane = new GridPane();
        for (int i=0; i<14; i++) {
            for (int j=0; j<5; j++) {
                ImageView ground= new ImageView(groundImage);
                ground.setFitWidth(sw/25);
                ground.setFitHeight(sh/10);
                GridPane.setConstraints(ground, i, j);
                gridPane.getChildren().add(ground);
            }
        }
        gridPane.setAlignment(Pos.CENTER);
        
        BorderPane borderpane = new BorderPane();
        borderpane.setId("background");
        borderpane.setTop(topMenu);
        borderpane.setLeft(leftMenu);
        borderpane.setBottom(bottomMenu);
        borderpane.setRight(rightMenu);
        borderpane.setCenter(gridPane);
        Scene scene = new Scene(borderpane, sw/1.5, sh/1.5);
        scene.getStylesheets().addAll(this.getClass().getResource("background.css").toExternalForm());
        window.setScene(scene);      
       
        window.show();
        window.setResizable(false);
        
    }
    
  //metodo per chiudere correttamente attraverso un confirmBox
    private void closeProgram() {
    Boolean answer = Exit.display("quitting","do you want to quit?");
    if (answer){
          window.close();
        }
    }

}
    