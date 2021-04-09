package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import com.sun.javafx.collections.MappingChange.Map;

import constants.ViewConstants;
import constants.ViewImages;
import controllers.ControllerImpl;
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
    
    private int scenario = 1;
    private int laneNumber = 5;
    private int timerDuration = 5;
//    Label lane;
//    Label timer;
    Label settingsSelected = new Label();
    //manca "scenario"

    //screen size
    final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    final int sw = (int) screen.getWidth();
    final int sh = (int) screen.getHeight();

    public final Parent createContent() throws IOException {

        Pane pane = new Pane();

        //background image
        Image backgroundimg  = new Image(this.getClass().getResourceAsStream(ViewImages.GAME_SETTINGS));
        ImageView backG = new ImageView(backgroundimg);
        backG.setFitWidth(sw / ViewConstants.DIVISOR_1_5);
        backG.setFitHeight(sh / ViewConstants.DIVISOR_1_5);

        //scenario buttons
        Button scenarioButtons;
        List<Button> listaScenario = new ArrayList<>();

        for (int i = 1; i < ViewConstants.N_BUTTON_5 - 1; i++) {
            scenarioButtons = new Button("SCENARIO: " + i);
            scenarioButtons.setPrefSize(sw / ViewConstants.DIVISOR_10, sh / ViewConstants.DIVISOR_15);
            scenarioButtons.setStyle(Style.BUTTON_1);
            listaScenario.add(scenarioButtons);
        }

        //lane buttons
        //Button laneButtons;
        List<Button> listaLane = new ArrayList<>();
        HashMap<Button, Integer> listLane = new HashMap<Button, Integer>();

        for (int i = 1; i < ViewConstants.N_BUTTON_6; i += 2) {
            Button laneButtons = new Button("LANE'S NUMBER: " + i);
            laneButtons.setPrefSize(sw / ViewConstants.DIVISOR_10, sh / ViewConstants.DIVISOR_15);
            laneButtons.setStyle(Style.BUTTON_1);
            listLane.put(laneButtons, i);
            laneButtons.setOnAction(e -> {
                this.laneNumber = listLane.get(laneButtons);
                //updateNumLane();
                updateSettings();
                System.out.println(this.laneNumber); /* qui non vedo la variabile del for -> utilizzo Map ? */
            });
            listaLane.add(laneButtons);
        }

       // timer Buttons
        //Button timerButtons;
        List<Button> listaTimer = new ArrayList<>();
        final HashMap<Button, Integer> listTimer = new HashMap<>();

        for (int i = ViewConstants.N_BUTTON_5; i < ViewConstants.N_BUTTON_16; i += ViewConstants.N_BUTTON_5) {
            Button timerButtons;
            timerButtons = new Button(i + " MINUTES");
            timerButtons.setPrefSize(sw / ViewConstants.DIVISOR_10, sh / ViewConstants.DIVISOR_15);
            timerButtons.setStyle(Style.BUTTON_1);
            listTimer.put(timerButtons, i);
            timerButtons.setOnAction(e -> {
                this.timerDuration = listTimer.get(timerButtons);
                //updateTime();
                updateSettings();
                System.out.println(this.timerDuration);
            });
            listaTimer.add(timerButtons);
            }

        //back button
        Button back = new Button("BACK");
        back.setPrefSize(sw / ViewConstants.DIVISOR_10, sh / ViewConstants.DIVISOR_15);
        back.setStyle(Style.BUTTON_2);
        back.setOnAction(e -> {
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
        start.setStyle(Style.BUTTON_2);
        start.setOnAction(e -> {
//            scenaGame = new GameView();
            //ControllerImpl contr = new ControllerImpl();
            ControllerImpl contr = new ControllerImpl(this.laneNumber, this.timerDuration);
           try {
            pane.getChildren().setAll(contr.getView().createContent());
           } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
           }
       });

        //label
        Label scenario = new Label("Scenario:");
        scenario.setAlignment(Pos.CENTER);
        scenario.setPrefSize(sw / ViewConstants.DIVISOR_10, sh / ViewConstants.DIVISOR_15);
        scenario.setStyle(Style.LABEL);

        Label lane = new Label("Number of lane:");
        //updateNumLane();
        updateSettings();
        lane.setAlignment(Pos.CENTER);
        lane.setPrefSize(sw / ViewConstants.DIVISOR_10, sh / ViewConstants.DIVISOR_15);
        lane.setStyle(Style.LABEL);

        Label timer = new Label("Timer:");
        //updateTime();
        updateSettings();
        timer.setAlignment(Pos.CENTER);
        timer.setPrefSize(sw / ViewConstants.DIVISOR_10, sh / ViewConstants.DIVISOR_15);
        timer.setStyle(Style.LABEL);

        settingsSelected.setAlignment(Pos.CENTER);
        settingsSelected.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_4), ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_10));
        settingsSelected.setStyle(Style.LABEL);
        settingsSelected.setText("SELECTED SCENARIO: " + this.scenario + "\n NUMBER OF LANES: " + this.laneNumber +
                "\n SELECTED TIMER: " + this.timerDuration + "MINS");
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
        backStartBox.getChildren().addAll(back, start, settingsSelected);
        VBox vBox = new VBox(sh / ViewConstants.DIVISOR_15);
        vBox.setAlignment(Pos.CENTER);

        vBox.setPrefSize(sw / ViewConstants.DIVISOR_1_5, sh / ViewConstants.DIVISOR_1_5);
        vBox.getChildren().addAll(scenarioBox, laneBox, timerBox, backStartBox);
        pane.getChildren().add(backG);
        pane.getChildren().addAll(vBox);
        return pane;
    }
    private void updateSettings() {
        this.settingsSelected.setText("SELECTED SCENARIO: " + this.scenario + "\n NUMBER OF LANES: " + this.laneNumber +
                "\n SELECTED TIMER: " + this.timerDuration + "MINS");
    }
//    private void updateNumLane() {
//        this.lane.setText("Number of lane: " + this.laneNumber);
//    }
//    private void updateTime() {
//        this.timer.setText("Timer: " + this.timerDuration + " mins");
//    }
    
 }
