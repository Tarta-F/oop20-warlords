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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundPosition;

public class GameModeSelection extends Region { 

    private MainMenu scenaMenu;
    private GameView scenaGame;

    //screen size
    final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    final int sw = (int) screen.getWidth();
    final int sh = (int) screen.getHeight();

    public Parent createContent() throws IOException {

        Pane pane = new Pane();  

        //background image
        Image backgroundimg  = new Image(this.getClass().getResourceAsStream("/GameSettings.png"));
        ImageView backG = new ImageView(backgroundimg);
        backG.setFitWidth(sw / ViewConstants.DIVISOR_1_5);
        backG.setFitHeight(sh / ViewConstants.DIVISOR_1_5);


        //scenario buttons 
        Button scenarioButtons;
        List<Button> listaScenario = new ArrayList<>();

        for (int i = 1; i < ViewConstants.N_BUTTON_5 - 1; i++) {
            scenarioButtons = new Button("SCENARIO: " + i);
            scenarioButtons.setPrefSize(sw / ViewConstants.DIVISOR_10, sh / ViewConstants.DIVISOR_15);
            scenarioButtons.setStyle("    -fx-text-fill: #FFFFFF;\r\n"
                      + "    -fx-background-radius: 6;\r\n"
                      + "    -fx-font-weight: bold;\r\n"
                      + "     -fx-background-color: linear-gradient(#000000, #696969);\r\n"
                      + "      -fx-font-size:" + sw / ViewConstants.DIVISOR_150 + ";");
            listaScenario.add(scenarioButtons);
        }


        //lane buttons
        Button laneButtons;
        List<Button> listaLane = new ArrayList<>();

        for (int i = 1; i < ViewConstants.N_BUTTON_6; i += 2) {
            laneButtons = new Button("LANE'S NUMBER: " + i);
            laneButtons.setPrefSize(sw / ViewConstants.DIVISOR_10, sh / ViewConstants.DIVISOR_15);
            laneButtons.setStyle("    -fx-text-fill: #FFFFFF;\r\n"
                      + "    -fx-background-radius: 6;\r\n"
                      + "    -fx-font-weight: bold;\r\n"
                      + "     -fx-background-color: linear-gradient(#000000, #696969);\r\n"
                      + "      -fx-font-size:" + sw / ViewConstants.DIVISOR_150 + ";");
            listaLane.add(laneButtons);

        } 


       // timer Buttons
        Button timerButtons;
        List<Button> listaTimer = new ArrayList<>();

        for (int i = ViewConstants.N_BUTTON_5; i < ViewConstants.N_BUTTON_16; i += ViewConstants.N_BUTTON_5) {
            timerButtons = new Button(i + " MINUTES");
            timerButtons.setPrefSize(sw / ViewConstants.DIVISOR_10, sh / ViewConstants.DIVISOR_15);
            timerButtons.setStyle("    -fx-text-fill: #FFFFFF;\r\n"
                    + "    -fx-background-radius: 6;\r\n"
                    + "    -fx-font-weight: bold;\r\n"
                    + "     -fx-background-color: linear-gradient(#000000, #696969);\r\n"
                    + "      -fx-font-size:" + sw / ViewConstants.DIVISOR_150 + ";");
            listaTimer.add(timerButtons);
            }

        //back button
        Button back = new Button("BACK");
        back.setPrefSize(sw / ViewConstants.DIVISOR_10, sh / ViewConstants.DIVISOR_15);
        back.setStyle("    -fx-text-fill: #000000;\r\n"
                + "    -fx-background-radius: 6;\r\n"
                + "    -fx-font-weight: bold;\r\n"
                + "     -fx-background-color: linear-gradient(#FFFFFF, #696969);\r\n"
                + "      -fx-font-size:" + sw / ViewConstants.DIVISOR_150 + ";");
        back.setOnAction(e ->{
            scenaMenu = new MainMenu();
            
            try {
                pane.getChildren().setAll(scenaMenu.createContent());
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        
        Button start = new Button("START");
        start.setPrefSize(sw / ViewConstants.DIVISOR_10, sh / ViewConstants.DIVISOR_15);
        start.setStyle("    -fx-text-fill: #000000;\r\n"
                + "    -fx-background-radius: 6;\r\n"
                + "    -fx-font-weight: bold;\r\n"
                + "     -fx-background-color: linear-gradient(#FFFFFF, #696969);\r\n"
                + "      -fx-font-size:" + sw / ViewConstants.DIVISOR_150 + ";");
       start.setOnAction(e ->{
           scenaGame = new GameView();
           
           try {
            pane.getChildren().setAll(scenaGame.createContent());
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
           
           
       }); 

        //label
        Label scenario = new Label("Scenario:");
        scenario.setAlignment(Pos.CENTER);
        scenario.setPrefSize(sw / ViewConstants.DIVISOR_10, sh / ViewConstants.DIVISOR_15);
        scenario.setStyle("    -fx-text-fill: #FFFFFF;\r\n"
                + "    -fx-background-radius: 6;\r\n"
                + "    -fx-font-weight: bold;\r\n"
                + "     -fx-background-color: rgba(0, 0, 0, 0.5);\r\n"
                + "      -fx-font-size:" + sw / ViewConstants.DIVISOR_150 + ";");


        Label lane = new Label("Number of lane:");
        lane.setAlignment(Pos.CENTER);
        lane.setPrefSize(sw / ViewConstants.DIVISOR_10, sh / ViewConstants.DIVISOR_15);
        lane.setStyle("    -fx-text-fill: #FFFFFF;\r\n"
                + "    -fx-background-radius: 6;\r\n"
                + "    -fx-font-weight: bold;\r\n"
                + "     -fx-background-color: rgba(0, 0, 0, 0.5);\r\n"
                + "      -fx-font-size:" + sw / ViewConstants.DIVISOR_150 + ";");


        Label timer = new Label("Timer:");
        timer.setAlignment(Pos.CENTER);
        timer.setPrefSize(sw / ViewConstants.DIVISOR_10, sh / ViewConstants.DIVISOR_15);
        timer.setStyle("    -fx-text-fill: #FFFFFF;\r\n"
                + "    -fx-background-radius: 6;\r\n"
                + "    -fx-font-weight: bold;\r\n"
                + "     -fx-background-color: rgba(0, 0, 0, 0.5);\r\n"
                + "      -fx-font-size:" + sw / ViewConstants.DIVISOR_150 + ";");



        //layout
        HBox scenarioBox = new HBox(sw / ViewConstants.DIVISOR_15);
        scenarioBox.setAlignment(Pos.CENTER);
        scenarioBox.getChildren().add(scenario);
        scenarioBox.getChildren().addAll(listaScenario);

        HBox laneBox = new HBox(sw / ViewConstants.DIVISOR_15);
        laneBox.setAlignment(Pos.CENTER);
        laneBox.getChildren().add(lane);
        laneBox.getChildren().addAll(listaLane);

        HBox timerBox = new HBox(sw / ViewConstants.DIVISOR_15);
        timerBox.setAlignment(Pos.CENTER);
        timerBox.getChildren().add(timer);
        timerBox.getChildren().addAll(listaTimer);

        HBox backStartBox = new HBox(sw / ViewConstants.DIVISOR_15);
        backStartBox.setPadding(new Insets(0, 0, 0, sw / ViewConstants.DIVISOR_30));
        backStartBox.getChildren().addAll(back, start);
        VBox vBox = new VBox(sh / ViewConstants.DIVISOR_15);
        vBox.setAlignment(Pos.CENTER);

        vBox.setPrefSize(sw / ViewConstants.DIVISOR_1_5, sh / ViewConstants.DIVISOR_1_5);
        vBox.getChildren().addAll(scenarioBox, laneBox, timerBox, backStartBox);
        pane.getChildren().add(backG);
        pane.getChildren().addAll(vBox);
        return pane;
    }


}

