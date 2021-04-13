package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import constants.ViewConstants;
import constants.ViewImages;
import controllers.ControllerImpl;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private String scenario;
    private int laneNumber;
    private int timerDuration;
    private String background;
    private String ground;
    private final Label settingsSelected;

    public GameModeSelection() {
        this.laneNumber = ViewConstants.DEFAULT_LANE;
        this.timerDuration = ViewConstants.DEFAULT_TIMER;
        this.scenario = ScenarioViewType.SCENARIO_1.getDescription();
        this.background = ScenarioViewType.SCENARIO_1.getBackgroundPath();
        this.ground = ScenarioViewType.SCENARIO_1.getGroundPath();
        this.settingsSelected = new Label();
        this.updateSettings();
    }

    /**Method to upgrade the label in GameModeSelection. */
    private void updateSettings() {
        this.settingsSelected.setText("SELECTED SCENARIO: " + this.scenario + "\n NUMBER OF LANES: " + this.laneNumber 
                + "\n SELECTED TIMER: " + this.timerDuration + "MINS");
    }

    public final Parent createGameModeSelection() throws IOException {

        /**Pane. */
        final Pane pane = new Pane();

        /**TextField. */
        TextField playerName1 = new TextField("Player 1");
        playerName1.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_10), 
                ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15));

        TextField playerName2 = new TextField("Player 2");
        playerName2.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_10), 
                ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15));

        /**BackGroung. */
        final Image backgroundImg  = new Image(this.getClass().getResourceAsStream(ViewImages.GAME_SETTINGS));
        final ImageView backGround = new ImageView(backgroundImg);
        backGround.setFitWidth(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_1_3));
        backGround.setFitHeight(ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_1_3));

        /**Buttons. */
        /**Buttons SCENARIO. */
        final List<Button> scenarioList = new ArrayList<>();
        final List<ScenarioViewType> scenarios = new ArrayList<>(Arrays.asList(ScenarioViewType.values()));
        final Map<Button, ScenarioViewType> buttonScenario = new HashMap<>();

        scenarios.forEach(s -> {
            final Button scenarioButtons = new Button("SCENARIO " + s.getDescription());
            scenarioButtons.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_10), 
                    ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15));
            scenarioButtons.setStyle(Style.BUTTON_1);
            buttonScenario.put(scenarioButtons, s);
            scenarioButtons.setOnAction(e -> {
                this.background = s.getBackgroundPath();
                this.ground = s.getGroundPath();
                this.scenario = s.getDescription();
                updateSettings();
            });
            scenarioList.add(scenarioButtons);
        });

        /**Buttons LANE. */
        //final List<Button> listaLane = new ArrayList<>();
        final Map<Button, Integer> buttonLane = new HashMap<>();

        for (int i = 1; i < ViewConstants.N_BUTTON_6; i += 2) {
            final Button laneButtons = new Button("LANE'S NUMBER: " + i);
            laneButtons.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_10), 
                    ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15));
            laneButtons.setStyle(Style.BUTTON_1);
            buttonLane.put(laneButtons, i);
            laneButtons.setOnAction(e -> {
                this.laneNumber = buttonLane.get(laneButtons);
                updateSettings();
            });
        }
        final List<Button> listLane = buttonLane.entrySet().stream()
                .sorted((a, b) -> a.getValue() - b.getValue())
                .map(e -> e.getKey())
                .collect(Collectors.toList());

        /**Buttons TIMER. */
        final Map<Button, Integer> buttonTimer = new HashMap<>();

        for (int i = ViewConstants.N_BUTTON_3 + 2; i < ViewConstants.N_BUTTON_16; i += ViewConstants.N_BUTTON_3 + 2) {
            final Button timerButtons = new Button(i + " MINUTES");
            timerButtons.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_10), 
                    ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15));
            timerButtons.setStyle(Style.BUTTON_1);
            buttonTimer.put(timerButtons, i);
            timerButtons.setOnAction(e -> {
                this.timerDuration = buttonTimer.get(timerButtons);
                updateSettings();
            });
        }
        final List<Button> listTimer = buttonTimer.entrySet().stream()
                .sorted((a, b) -> a.getValue() - b.getValue())
                .map(e -> e.getKey())
                .collect(Collectors.toList());

        /**Button BACK. */
        final Button back = new Button("BACK");
        back.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_10), 
                ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15));
        back.setStyle(Style.BUTTON_2);
        back.setOnAction(e -> {
            scenaMenu = new MainMenu();
            try {
                pane.getChildren().setAll(scenaMenu.createMainMenu());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        /**Button START. */
        final Button start = new Button("START");
        start.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_10), 
                ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15));
        start.setStyle(Style.BUTTON_2);
        start.setOnAction(e -> {
//            System.out.println("back: " + this.background + "ground" + this.ground +
//                    "lane: " + this.laneNumber + "timer: " + this.timerDuration);
           final ControllerImpl contr = new ControllerImpl(this.laneNumber, this.timerDuration, this.background, this.ground);
           try {
            pane.getChildren().setAll(contr.getView().createGameView());
           } catch (IOException e1) {
            e1.printStackTrace();
           }
       });

        /**Labels. */
        final Label scenario = new Label("Scenario:");
        scenario.setAlignment(Pos.CENTER);
        scenario.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_10), 
                ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15));
        scenario.setStyle(Style.LABEL);

        final Label lane = new Label("Number of lane:");
        updateSettings();
        lane.setAlignment(Pos.CENTER);
        lane.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_10), 
                ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15));
        lane.setStyle(Style.LABEL);

        final Label timer = new Label("Timer:");
        updateSettings();
        timer.setAlignment(Pos.CENTER);
        timer.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_10), 
                ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15));
        timer.setStyle(Style.LABEL);

        final Label player1 = new Label("Player 1 name: ");
        player1.setAlignment(Pos.CENTER);
        player1.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_10), 
                ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15));
        player1.setStyle(Style.LABEL);

        final Label player2 = new Label("Player 2 name: ");
        player2.setAlignment(Pos.CENTER);
        player2.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_10), 
                ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15));
        player2.setStyle(Style.LABEL);

        settingsSelected.setAlignment(Pos.CENTER);
        settingsSelected.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_4), 
                ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_10));
        settingsSelected.setStyle(Style.LABEL);

        /**Layout and Pane gets. */
        final HBox scenarioBox = new HBox(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_15));
        scenarioBox.setAlignment(Pos.CENTER);
        scenarioBox.getChildren().add(scenario);
        scenarioBox.getChildren().addAll(scenarioList);

        final HBox laneBox = new HBox(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_15));
        laneBox.setAlignment(Pos.CENTER);
        laneBox.getChildren().add(lane);
        laneBox.getChildren().addAll(listLane);

        final HBox timerBox = new HBox(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_15));
        timerBox.setAlignment(Pos.CENTER);
        timerBox.getChildren().add(timer);
        timerBox.getChildren().addAll(listTimer);

        final HBox namesBox = new HBox(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_15));
        namesBox.setAlignment(Pos.CENTER);
        namesBox.getChildren().addAll(player1, playerName1, player2, playerName2);

        final HBox backStartBox = new HBox(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_15));
        backStartBox.setAlignment(Pos.CENTER);
        backStartBox.setPadding(new Insets(0, ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_60), 0, 0));
        backStartBox.getChildren().addAll(back, start, settingsSelected);

        final VBox vBox = new VBox(ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15));
        vBox.setAlignment(Pos.CENTER);
        vBox.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_1_3), 
                ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_1_3));
        vBox.getChildren().addAll(scenarioBox, laneBox, timerBox, namesBox, backStartBox);

        pane.getChildren().add(backGround);
        pane.getChildren().addAll(vBox);

        return pane;
    }
 }
