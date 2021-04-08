package view;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import constants.ViewConstants;
import constants.ViewImages;
import controllers.ControllerImpl;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * 
 * This class implements the GameModeSelection scene.
 *
 */
public class GameModeSelection extends Region {

    private MainMenu scenaMenu;
    private GameView scenaGame;
    private int scenarioSelezionato = 1;
    private int laneSelezionate = 1;
    private int timerSelezionato = 300;


    public final Parent createGameModeSelection() throws IOException {

        final Pane pane = new Pane();
        final Label settingsSelected = new Label();

        //background image
        final Image backgroundImg  = new Image(this.getClass().getResourceAsStream(ViewImages.GAME_SETTINGS));
        final ImageView backGround = new ImageView(backgroundImg);
        backGround.setFitWidth(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_1_5));
        backGround.setFitHeight(ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_1_5));

        //scenario buttons
        Button scenarioButtons;
        final List<Button> listaScenario = new ArrayList<>();

        for (int i = 1; i < ViewConstants.N_BUTTON_5 - 1; i++) {
            scenarioButtons = new Button("SCENARIO: " + i);
            scenarioButtons.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_10), ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15));
            scenarioButtons.setStyle(Style.BUTTON_1);
            listaScenario.add(scenarioButtons);
        }

        listaScenario.get(0).setOnAction(e -> {
            scenarioSelezionato = 1;
            System.out.println(scenarioSelezionato);
            settingsSelected.setText("SELECTED SCENARIO: " + scenarioSelezionato + "\n SELECTED LANES: " + laneSelezionate + "\n SELECTED TIMER: " + timerSelezionato + "S");
        });
        listaScenario.get(1).setOnAction(e -> {
            scenarioSelezionato = 2;
            System.out.println(scenarioSelezionato);
            settingsSelected.setText("SELECTED SCENARIO: " + scenarioSelezionato + "\n SELECTED LANES: " + laneSelezionate + "\n SELECTED TIMER: " + timerSelezionato + "S");
        });
        listaScenario.get(2).setOnAction(e -> {
            scenarioSelezionato = 3;
            System.out.println(scenarioSelezionato);
            settingsSelected.setText("SELECTED SCENARIO: " + scenarioSelezionato + "\n SELECTED LANES: " + laneSelezionate + "\n SELECTED TIMER: " + timerSelezionato + "S");
        });

         //lane buttons
        Button laneButtons;
        final List<Button> listaLane = new ArrayList<>();

        for (int i = 1; i < ViewConstants.N_BUTTON_6; i += 2) {
            laneButtons = new Button("LANE'S NUMBER: " + i);
            laneButtons.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_10), ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15));
            laneButtons.setStyle(Style.BUTTON_1);
            listaLane.add(laneButtons);
        }
        listaLane.get(0).setOnAction(e -> {
            laneSelezionate = 1;
            System.out.println(laneSelezionate);
            settingsSelected.setText("SELECTED SCENARIO: " + scenarioSelezionato + "\n SELECTED LANES: " + laneSelezionate + "\n SELECTED TIMER: " + timerSelezionato + "S");
        });
        listaLane.get(1).setOnAction(e -> {
            laneSelezionate = 3;
            System.out.println(laneSelezionate);
            settingsSelected.setText("SELECTED SCENARIO: " + scenarioSelezionato + "\n SELECTED LANES: " + laneSelezionate + "\n SELECTED TIMER: " + timerSelezionato + "S");
        });
        listaLane.get(2).setOnAction(e -> {
            laneSelezionate = 5;
            System.out.println(laneSelezionate);
            settingsSelected.setText("SELECTED SCENARIO: " + scenarioSelezionato + "\n SELECTED LANES: " + laneSelezionate + "\n SELECTED TIMER: " + timerSelezionato + "S");
        });

       // timer Buttons
        Button timerButtons;
        final List<Button> listaTimer = new ArrayList<>();

        for (int i = ViewConstants.N_BUTTON_5; i < ViewConstants.N_BUTTON_16; i += ViewConstants.N_BUTTON_5) {
            timerButtons = new Button(i + " MINUTES");
            timerButtons.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_10), ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15));
            timerButtons.setStyle(Style.BUTTON_1);
            listaTimer.add(timerButtons);
            }
        listaTimer.get(0).setOnAction(e -> {
            timerSelezionato = 300;
            System.out.println(timerSelezionato);
            settingsSelected.setText("SELECTED SCENARIO: " + scenarioSelezionato + "\n SELECTED LANES: " + laneSelezionate + "\n SELECTED TIMER: " + timerSelezionato + "S");
        });
        listaTimer.get(1).setOnAction(e -> {
            timerSelezionato = 600;
            System.out.println(timerSelezionato);
            settingsSelected.setText("SELECTED SCENARIO: " + scenarioSelezionato + "\n SELECTED LANES: " + laneSelezionate + "\n SELECTED TIMER: " + timerSelezionato + "S");
        });
        listaTimer.get(2).setOnAction(e -> {
            timerSelezionato = 900;
            System.out.println(timerSelezionato);
            settingsSelected.setText("SELECTED SCENARIO: " + scenarioSelezionato + "\n SELECTED LANES: " + laneSelezionate + "\n SELECTED TIMER: " + timerSelezionato + "S");
        });


        //back button
        final Button back = new Button("BACK");
        back.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_10), ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15));
        back.setStyle(Style.BUTTON_2);
        back.setOnAction(e -> {
            scenaMenu = new MainMenu();
            try {
                pane.getChildren().setAll(scenaMenu.createMainMenu());
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        final Button start = new Button("START");
        start.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_10), ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15));
        start.setStyle(Style.BUTTON_2);
        start.setOnAction(e -> {
//            scenaGame = new GameView();
           final ControllerImpl c = new ControllerImpl();
           try {
            pane.getChildren().setAll(c.getView().createGameView());
           } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
           }
       });

        //label
        final Label scenario = new Label("Scenario:");
        scenario.setAlignment(Pos.CENTER);
        scenario.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_10), ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15));
        scenario.setStyle(Style.LABEL);

        final Label lane = new Label("Number of lane:");
        lane.setAlignment(Pos.CENTER);
        lane.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_10), ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15));
        lane.setStyle(Style.LABEL);

        final Label timer = new Label("Timer:");
        timer.setAlignment(Pos.CENTER);
        timer.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_10), ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15));
        timer.setStyle(Style.LABEL);


        settingsSelected.setAlignment(Pos.CENTER);
        settingsSelected.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_4), ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_10));
        settingsSelected.setStyle(Style.LABEL);
        settingsSelected.setText("SELECTED SCENARIO: " + scenarioSelezionato + "\n SELECTED LANES: " + laneSelezionate + "\n SELECTED TIMER: " + timerSelezionato + "S");

        //layout
        final HBox scenarioBox = new HBox(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_15));
        scenarioBox.setAlignment(Pos.CENTER);
        scenarioBox.getChildren().add(scenario);
        scenarioBox.getChildren().addAll(listaScenario);

        final HBox laneBox = new HBox(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_15));
        laneBox.setAlignment(Pos.CENTER);
        laneBox.getChildren().add(lane);
        laneBox.getChildren().addAll(listaLane);

        final HBox timerBox = new HBox(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_15));
        timerBox.setAlignment(Pos.CENTER);
        timerBox.getChildren().add(timer);
        timerBox.getChildren().addAll(listaTimer);

        final HBox backStartBox = new HBox(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_15));
        backStartBox.setPadding(new Insets(0, 0, 0, ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_30)));
        backStartBox.getChildren().addAll(back, start, settingsSelected);
        final VBox vBox = new VBox(ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15));
        vBox.setAlignment(Pos.CENTER);

        vBox.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_1_5), ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_1_5));
        vBox.getChildren().addAll(scenarioBox, laneBox, timerBox, backStartBox);
        pane.getChildren().add(backGround);
        pane.getChildren().addAll(vBox);

        return pane;
    }
}
