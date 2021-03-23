package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu extends Application{ 

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
        
        //ViewConstants.DIVISOR
        
        //image
        Image logoImage  = new Image(this.getClass().getResourceAsStream("/logo.png"));
        ImageView logo = new ImageView(logoImage);
        logo.setFitWidth(sw/4);
        logo.setFitHeight(sh/8);
        
        Image logoSpearmanImage  = new Image(this.getClass().getResourceAsStream("/spearman.png"));
        ImageView logoSpearman = new ImageView(logoSpearmanImage);
        logoSpearman.setFitWidth(sw/7);
        logoSpearman.setFitHeight(sh/3);
        
        
        Image logoArcherImage  = new Image(this.getClass().getResourceAsStream("/archer.png"));
        ImageView logoArcher = new ImageView(logoArcherImage);
        logoArcher.setFitWidth(sw/8);
        logoArcher.setFitHeight(sh/3);
        
        
        
        //buttons
        
        Button campaign = new Button("CAMPAIGN");
        campaign.setPrefSize(sw/15, sh/15);
        campaign.setId("menuButton");
        Button versus = new Button("VERSUS");
        versus.setPrefSize(sw/15, sh/15);
        versus.setId("menuButton");
        Button tutorials = new Button("TUTORIALS");
        tutorials.setPrefSize(sw/15, sh/15);
        tutorials.setId("menuButton");
        Button exit = new Button("EXIT");
        exit.setPrefSize(sw/15, sh/15);
        exit.setId("menuButton");
        
        
        
        //layout

        
        
        VBox menu = new VBox(sh/15);
        menu.setAlignment(Pos.CENTER);
        menu.getChildren().addAll(logo,campaign, versus, tutorials,exit);
        menu.setPadding(new Insets(sh/30, 0, sh/30, 0));
        
       
        VBox sinistra = new VBox();
        sinistra.setAlignment(Pos.CENTER);
        sinistra.getChildren().add(logoArcher);
        sinistra.setPadding(new Insets(0, 0, 0, sw/15));
        
        VBox destra = new VBox();
        destra.setAlignment(Pos.CENTER);
        destra.getChildren().add(logoSpearman);
        destra.setPadding(new Insets(0, sw/15, 0, 0));
        
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(menu);
        borderPane.setLeft(sinistra);
        borderPane.setRight(destra);
        borderPane.setId("menu");
        
        Scene scene = new Scene(borderPane,  sw/1.5, sh/1.5);
        scene.getStylesheets().addAll(this.getClass().getResource("background.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
        
        
    
    
    
    }

}