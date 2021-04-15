package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import view.constants.ViewConstants;
import view.constants.ViewImages;

/**
 *
 * This class implements the GameModeSelection Pane.
 *
 */
public class GameModeSelection extends Region implements ViewInterface {

    private MainMenu scenaMenu;
    private String scenario;
    private String background;
    private String ground;
    private int laneNumber;
    private int timerDuration;
    private final Label settingsSelected;
    private static final double TEXTFIELD_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_10);
    private static final double TEXTFIELD_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15);
    private static final double BUTTONS_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_10);
    private static final double BUTTONS_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15);
    private static final double LABELS_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_10);
    private static final double LABELS_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15);
    private static final double LABEL_SETTINGS_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_4);
    private static final double LABEL_SETTINGS_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_10);
    private static final double LAYOUT_HBOX_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_15);
    private static final double LAYOUT_VBOX_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15);
    private static final double LAYOUT_HBOX_PADDING_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_60);
    private static final double VBOX_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_1_3);
    private static final double VBOX_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_1_3);
    
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

    public final Parent createContent() throws IOException {

        /**Pane. */
        final Pane pane = new Pane();


        /**TextField. */
        final TextField playerName1 = new TextField("Player 1");
        playerName1.setPrefSize(TEXTFIELD_W, TEXTFIELD_H);

        final TextField playerName2 = new TextField("Player 2");
        playerName2.setPrefSize(TEXTFIELD_W, TEXTFIELD_H);


        /**BackGroung. */
        final Image backgroundImg  = new Image(this.getClass().getResourceAsStream(ViewImages.GAME_SETTINGS));
        final ImageView background = ViewResolution.createImageView(backgroundImg, VBOX_W, VBOX_H);


        /**Buttons. */
        /**Buttons SCENARIO. */
        final List<Button> scenarioList = new ArrayList<>();
        final List<ScenarioViewType> scenarios = new ArrayList<>(Arrays.asList(ScenarioViewType.values()));
        final Map<Button, ScenarioViewType> buttonScenario = new HashMap<>();

        scenarios.forEach(s -> {
            final Button scenarioButtons = new Button("SCENARIO " + s.getDescription());
            scenarioButtons.setPrefSize(BUTTONS_W, BUTTONS_H);
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
        final Map<Button, Integer> buttonLane = new HashMap<>();

        for (int i = 1; i < ViewConstants.N_BUTTON_6; i += 2) {
            final Button laneButtons = new Button("LANE'S NUMBER: " + i);
            laneButtons.setPrefSize(BUTTONS_W, BUTTONS_H);
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
            timerButtons.setPrefSize(BUTTONS_W, BUTTONS_H);
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
        back.setPrefSize(BUTTONS_W, BUTTONS_H);
        back.setStyle(Style.BUTTON_2);
        back.setOnAction(e -> {
            scenaMenu = new MainMenu();
            try {
                pane.getChildren().setAll(scenaMenu.createContent());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        /**Button START. */
        final Button start = new Button("START");
        start.setPrefSize(BUTTONS_W, BUTTONS_H);
        start.setStyle(Style.BUTTON_2);
        start.setOnAction(e -> {
           final ControllerImpl contr = new ControllerImpl(this.laneNumber, this.timerDuration, this.background, this.ground, playerName1.getText(), playerName2.getText());
           try {
            pane.getChildren().setAll(contr.getView().createContent());
           } catch (IOException e1) {
            e1.printStackTrace();
           }
       });


        /**Labels. */
        final Label scenario = new Label("Scenario:");
        scenario.setAlignment(Pos.CENTER);
        scenario.setPrefSize(LABELS_W, LABELS_H);
        scenario.setStyle(Style.LABEL);

        final Label lane = new Label("Number of lane:");
        updateSettings();
        lane.setAlignment(Pos.CENTER);
        lane.setPrefSize(LABELS_W, LABELS_H);
        lane.setStyle(Style.LABEL);

        final Label timer = new Label("Timer:");
        updateSettings();
        timer.setAlignment(Pos.CENTER);
        timer.setPrefSize(LABELS_W, LABELS_H);
        timer.setStyle(Style.LABEL);

        final Label player1 = new Label("Player 1 name: ");
        player1.setAlignment(Pos.CENTER);
        player1.setPrefSize(LABELS_W, LABELS_H);
        player1.setStyle(Style.LABEL);

        final Label player2 = new Label("Player 2 name: ");
        player2.setAlignment(Pos.CENTER);
        player2.setPrefSize(LABELS_W, LABELS_H);
        player2.setStyle(Style.LABEL);

        settingsSelected.setAlignment(Pos.CENTER);
        settingsSelected.setPrefSize(LABEL_SETTINGS_W, LABEL_SETTINGS_H);
        settingsSelected.setStyle(Style.LABEL);


        /**Layout and Pane gets. */
        final HBox scenarioBox = new HBox(LAYOUT_HBOX_W);
        scenarioBox.setAlignment(Pos.CENTER);
        scenarioBox.getChildren().add(scenario);
        scenarioBox.getChildren().addAll(scenarioList);

        final HBox laneBox = new HBox(LAYOUT_HBOX_W);
        laneBox.setAlignment(Pos.CENTER);
        laneBox.getChildren().add(lane);
        laneBox.getChildren().addAll(listLane);

        final HBox timerBox = new HBox(LAYOUT_HBOX_W);
        timerBox.setAlignment(Pos.CENTER);
        timerBox.getChildren().add(timer);
        timerBox.getChildren().addAll(listTimer);

        final HBox namesBox = new HBox(LAYOUT_HBOX_W);
        namesBox.setAlignment(Pos.CENTER);
        namesBox.getChildren().addAll(player1, playerName1, player2, playerName2);

        final HBox backStartBox = new HBox(LAYOUT_HBOX_W);
        backStartBox.setAlignment(Pos.CENTER);
        backStartBox.setPadding(new Insets(0, ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_60), 0, 0));
        backStartBox.getChildren().addAll(back, start, settingsSelected);

        final VBox vBox = new VBox(LAYOUT_VBOX_H);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPrefSize(VBOX_W, VBOX_H);
        vBox.getChildren().addAll(scenarioBox, laneBox, timerBox, namesBox, backStartBox);

        pane.getChildren().add(background);
        pane.getChildren().addAll(vBox);

        return pane;
    }
 }
