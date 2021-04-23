package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import constants.GameConstants;
import controllers.ControllerImpl;
import controllers.SettingsController;
import view.constants.ViewConstants;
import view.sound.Music;
import view.constants.ResourcesConstants;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
 * GameModeSelection scene implementation.
 */
public class GameModeSelection extends Region implements ViewInterface {

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

    private final Label labelSettingsSelected;

    private final SettingsController settingsManager;

    private final ViewFactory factory = new ViewFactoryImpl();

    public GameModeSelection(final SettingsController settingsManager) {
        this.settingsManager = settingsManager;
        this.labelSettingsSelected = new Label();
        this.updateSettings();
    }

    /**Method to upgrade the label that sum up selected settings. */
    private void updateSettings() {
        this.labelSettingsSelected.setText(this.settingsManager.toString());
    }

    /**Method that limit the players names length. */
    private static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) {
                    final String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
            }
        });
    }

    /** Utility method to extract the List of button from a Map. */
    private List<Button> getButtons(final Map<Button, Integer> map) {
        return map.entrySet().stream()
                .sorted((a, b) -> a.getValue() - b.getValue())
                .map(e -> e.getKey())
                .collect(Collectors.toList());
    }

    @Override
    public final Parent createPane() throws IOException {
        /*Pane. */
        final Pane pane = new Pane();

        /*TextField. */
        final TextField playerName1 = new TextField("Player 1");
        playerName1.setPrefSize(TEXTFIELD_W, TEXTFIELD_H);
        playerName1.setStyle(Style.TEXT);
        addTextLimiter(playerName1, 10);

        final TextField playerName2 = new TextField("Player 2");
        playerName2.setPrefSize(TEXTFIELD_W, TEXTFIELD_H);
        playerName2.setStyle(Style.TEXT);
        addTextLimiter(playerName2, 10);

        /*BackGroung. */
        final Image backgroundImg  = new Image(this.getClass().getResourceAsStream(ResourcesConstants.GAME_SETTINGS));
        final ImageView background = this.factory.createImageView(backgroundImg, VBOX_W, VBOX_H);

        /*Buttons. */
        /*Buttons SCENARIO. */
        final List<Button> scenarioList = new ArrayList<>();
        final List<ScenarioViewType> scenarios = List.of(ScenarioViewType.values());
        final Map<Button, ScenarioViewType> buttonScenario = new HashMap<>();

        scenarios.forEach(s -> {
            final Button scenarioButtons = this.factory.createButton("SCENARIO " + s.getDescription(), Style.BUTTON_1, 
                    BUTTONS_W, BUTTONS_H);
            buttonScenario.put(scenarioButtons, s);
            scenarioButtons.setOnMouseClicked(e -> {
                Music.getMusic().playButtonSound();
                this.settingsManager.setScenario(s);
                this.updateSettings();
            });
            scenarioList.add(scenarioButtons);
        });

        /*Buttons LANE. Associate a button to a number of lane. */
        final Map<Button, Integer> buttonLane = new HashMap<>();
        for (int i = GameConstants.MIN_LANE; i <= GameConstants.MAX_LANE; i = i + GameConstants.LANE_STEP) {
            final Button laneButtons = this.factory.createButton("LANE'S NUMBER: " + i, Style.BUTTON_1,
                    BUTTONS_W, BUTTONS_H);
            buttonLane.put(laneButtons, i);
            laneButtons.setOnMouseClicked(e -> {
                Music.getMusic().playButtonSound();
                this.settingsManager.setLaneNumber(buttonLane.get(laneButtons));
                this.updateSettings();
            });
        }

        /*Buttons TIMER. Associate a button to a different timer (Game duration). */
        final Map<Button, Integer> buttonTimer = new HashMap<>();

        for (int i = GameConstants.MIN_TIMER; i <= GameConstants.TIMER_MAX; i = i + GameConstants.TIMER_STEP) {
            final Button timerButtons = this.factory.createButton(i + " MINUTES", Style.BUTTON_1, 
                    BUTTONS_W, BUTTONS_H);
            buttonTimer.put(timerButtons, i);
            timerButtons.setOnMouseClicked(e -> {
                Music.getMusic().playButtonSound();
                this.settingsManager.setTimerDuration(buttonTimer.get(timerButtons));
                this.updateSettings();
            });
        }

        /*Button BACK. */
        final Button back = this.factory.createButton("BACK", Style.BUTTON_2, BUTTONS_W, BUTTONS_H);
        back.setOnMouseClicked(e -> {
            final MainMenu scenaMenu = new MainMenu();
            Music.getMusic().playButtonSound();
            try {
                pane.getChildren().setAll(scenaMenu.createPane());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        /*Button START. */
        final Button start = this.factory.createButton("START", Style.BUTTON_2, BUTTONS_W, BUTTONS_H);
        start.setOnMouseClicked(e -> {
           final ControllerImpl contr = new ControllerImpl(this.settingsManager.getLaneNumber(), this.settingsManager.getTimerDuration(),
                   this.settingsManager.getScenario(), playerName1.getText(), playerName2.getText());
           Music.getMusic().startMatchSound();
           try {
               pane.getChildren().setAll(contr.getView().createPane());
           } catch (IOException e1) {
            e1.printStackTrace();
           }
        });

        /*Labels. */
        final Label scenario = this.factory.createLabel("Scenario:", Style.LABEL, LABELS_W, LABELS_H);
        final Label lane = this.factory.createLabel("Number of lane:", Style.LABEL, LABELS_W, LABELS_H);
        final Label timer = this.factory.createLabel("Timer:", Style.LABEL, LABELS_W, LABELS_H);
        final Label player1 = this.factory.createLabel("Player 1 name: ", Style.LABEL, LABELS_W, LABELS_H);
        final Label player2 = this.factory.createLabel("Player 2 name: ", Style.LABEL, LABELS_W, LABELS_H);

        labelSettingsSelected.setAlignment(Pos.CENTER);
        labelSettingsSelected.setPrefSize(LABEL_SETTINGS_W, LABEL_SETTINGS_H);
        labelSettingsSelected.setStyle(Style.LABEL);

        /*Layout and Pane gets. */
        final HBox scenarioBox = this.factory.createHBox(LAYOUT_HBOX_W);
        scenarioBox.getChildren().add(scenario);
        scenarioBox.getChildren().addAll(scenarioList);

        final HBox laneBox = this.factory.createHBox(LAYOUT_HBOX_W);
        laneBox.getChildren().add(lane);
        laneBox.getChildren().addAll(this.getButtons(buttonLane));

        final HBox timerBox = this.factory.createHBox(LAYOUT_HBOX_W);
        timerBox.getChildren().add(timer);
        timerBox.getChildren().addAll(this.getButtons(buttonTimer));

        final HBox namesBox = this.factory.createHBox(LAYOUT_HBOX_W);
        namesBox.setAlignment(Pos.CENTER);
        namesBox.getChildren().addAll(player1, playerName1, player2, playerName2);

        final HBox backStartBox = new HBox(LAYOUT_HBOX_W);
        backStartBox.setAlignment(Pos.CENTER);
        backStartBox.setPadding(new Insets(0, LAYOUT_HBOX_PADDING_W, 0, 0));
        backStartBox.getChildren().addAll(back, start, labelSettingsSelected);

        final VBox vBox = new VBox(LAYOUT_VBOX_H);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPrefSize(VBOX_W, VBOX_H);
        vBox.getChildren().addAll(scenarioBox, laneBox, timerBox, namesBox, backStartBox);

        pane.getChildren().addAll(background, vBox);

        return pane;
    }

 }
