package view.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.EnumMap;
import org.apache.commons.lang3.tuple.Pair;
import constants.PlayerType;
import controllers.Controller;
import view.ConfirmBox;
import view.MainMenu;
import view.Music;
import view.Style;
import view.UnitViewType;
import view.ViewResolution;
import view.WinnerBox;
import view.constants.ViewConstants;
import view.constants.ViewImages;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This class is the BattleField game view.
 */
public final class GameViewImpl extends Region implements GameView {

    private static final double UNIT_ICON_WIDTH = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_15);
    private static final double UNIT_ICON_HEIGHT = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15);
    private static final double ARROW_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_20);
    private static final double ARROW_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_20);
    private static final double BUTTONS_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_15);
    private static final double BUTTONS_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_20);
    private static final double LABEL_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_15);
    private static final double LABEL_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_20);
    private static final double RESPAWN_LABEL_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_30);
    private static final double RESPAWN_LABEL_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_25);
    private static final double TOPMENU_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_25);
    private static final double BOTTOMMENU_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_30);
    private static final double LEFT_RIGTH_MENU_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_20);
    private static final double BORDERPANE_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_1_3);
    private static final double BORDERPANE_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_1_3);
    private static final double PADDING_H = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_60);

    private MainMenu scenaMenu;
    private final GameFieldView field;
    private final int laneNumber;
    private final Image scenario;
    private final String player1Name;
    private final String player2Name;

    private final List<ImageView> listArrowP1 = new ArrayList<>();
    private final List<ImageView> listArrowP2 = new ArrayList<>();
    private final List<ImageView> listUnitP1 = new ArrayList<>();
    private final List<ImageView> listUnitP2 = new ArrayList<>();
    private final List<Label> unit1ListLabel = new ArrayList<>();
    private final List<Label> unit2ListLabel = new ArrayList<>();

    private final EnumMap<UnitViewType, Label> unitBoxes = new EnumMap<>(UnitViewType.class);
    private final EnumMap<PlayerType, Label> labelsScore = new EnumMap<>(PlayerType.class);

    private List<Image> unitSelectedP1;
    private List<Image> unitImageP1;
    private List<Image> unitSelectedP2;
    private List<Image> unitImageP2;
    private Label timer;
    private Label player1;
    private Label player2;
    private Controller observer;
    private Pane pane;

    /**Sets of all Images used. */
    /**Player 1. */
    private final Image logoSwordsmenP1  = new Image(this.getClass().getResourceAsStream(ViewImages.P1_LOGO_SWORDSMEN));
    private final Image logoSpearmenP1  = new Image(this.getClass().getResourceAsStream(ViewImages.P1_LOGO_SPEARMEN));
    private final Image logoArcherP1  = new Image(this.getClass().getResourceAsStream(ViewImages.P1_LOGO_ARCHER));
    private final Image selectedSwordsmenP1  = new Image(this.getClass().getResourceAsStream(ViewImages.P1_SELECTED_SWORDSMEN));
    private final Image selectedSpearmenP1  = new Image(this.getClass().getResourceAsStream(ViewImages.P1_SELECTED_SPEARMEN));
    private final Image selectedArcherP1  = new Image(this.getClass().getResourceAsStream(ViewImages.P1_SELECTED_ARCHER));
    private final Image arrowP1  = new Image(this.getClass().getResourceAsStream(ViewImages.P1_ARROW));
    private final Image selectedArrowP1  = new Image(this.getClass().getResourceAsStream(ViewImages.P1_SELECTED_ARROW));

    /**Player 2. */
    private final Image logoSwordsmenP2  = new Image(this.getClass().getResourceAsStream(ViewImages.P2_LOGO_SWORDSMEN));
    private final Image logoSpearmenP2  = new Image(this.getClass().getResourceAsStream(ViewImages.P2_LOGO_SPEARMEN));
    private final Image logoArcherP2  = new Image(this.getClass().getResourceAsStream(ViewImages.P2_LOGO_ARCHER));
    private final Image selectedSwordsmenP2  = new Image(this.getClass().getResourceAsStream(ViewImages.P2_SELECTED_SWORDSMEN));
    private final Image selectedSpearmenP2  = new Image(this.getClass().getResourceAsStream(ViewImages.P2_SELECTED_SPEARMEN));
    private final Image selectedArcherP2  = new Image(this.getClass().getResourceAsStream(ViewImages.P2_SELECTED_ARCHER));
    private final Image arrowP2  = new Image(this.getClass().getResourceAsStream(ViewImages.P2_ARROW));
    private final Image selectedArrowP2  = new Image(this.getClass().getResourceAsStream(ViewImages.P2_SELECTED_ARROW));

    public GameViewImpl(final int laneNumber, final String background, final String ground,
            final String player1Name, final String player2Name) {
        this.laneNumber = laneNumber;
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.scenario = new Image(this.getClass().getResourceAsStream(background));
        this.field = new GameFieldViewImpl(laneNumber, ViewConstants.GRID_COLUMNS, ground);
    }

    /**
     * Get player NAME.
     * @param player PlayerType 
     * */
    private String getPlayerName(final PlayerType player) {
        if (player.equals(PlayerType.PLAYER1)) {
            return this.player1Name;
        } else {
            return this.player2Name;
        }
    }

    /** Create the timer label from the given long number.
     * @param l the quantity of seconds to display
     * @return the label created
     */
    private Label unitTimerLabel(final long l) {
        final Label respawnLabel = new Label(l + " sec");
        respawnLabel.setPrefSize(RESPAWN_LABEL_W, RESPAWN_LABEL_H);
        respawnLabel.setAlignment(Pos.CENTER);
        respawnLabel.setStyle(Style.LABEL);
        return respawnLabel;
    }

    /**
     * Create the score label.
     * @param player PlayerType
     * */
    private Label scorePlayerLabel(final PlayerType player) {
        final Label scoreLabel = new Label("SCORE " + getPlayerName(player) + ": " + observer.getScore(player));
        scoreLabel.setPrefSize(LABEL_W, LABEL_H);
        scoreLabel.setAlignment(Pos.CENTER);
        scoreLabel.setStyle(Style.LABEL);
        return scoreLabel;
    }

    /**
     * Method to return on main menu with a confirm box. 
     * @param actual pane 
     * */
    private void returnMainMenu(final Pane pane) {
        final boolean answer = ConfirmBox.display("Quitting", "Return to main menu?");
        if (answer) {
            scenaMenu = new MainMenu();
            try {
                Music.musicStop();
                Music.musicStart(ViewImages.MUSIC);
                pane.getChildren().setAll(scenaMenu.createPane());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public Parent createPane() throws IOException {

        /**Music. */
        Music.musicStart(ViewImages.MUSIC_2);

        /**Pane. */
        pane = new Pane();

        /**BackGround. */
        final ImageView gameBackGround = ViewResolution.createImageView(scenario, BORDERPANE_W, BORDERPANE_H);

        /**Lists of units logo used. */
        this.unitImageP1 = new ArrayList<>(Arrays.asList(logoSwordsmenP1, logoSpearmenP1, logoArcherP1));
        this.unitSelectedP1 = new ArrayList<>(Arrays.asList(selectedSwordsmenP1, selectedSpearmenP1, selectedArcherP1));
        this.unitImageP2 = new ArrayList<>(Arrays.asList(logoSwordsmenP2, logoSpearmenP2, logoArcherP2));
        this.unitSelectedP2 = new ArrayList<>(Arrays.asList(selectedSwordsmenP2, selectedSpearmenP2, selectedArcherP2));

        /**List of units player 1. */
        final ImageView unit1P1 = ViewResolution.createImageView(selectedSwordsmenP1, UNIT_ICON_WIDTH, UNIT_ICON_HEIGHT);
        final ImageView unit2P1 = ViewResolution.createImageView(logoSpearmenP1, UNIT_ICON_WIDTH, UNIT_ICON_HEIGHT);

        final ImageView unit3P1 = ViewResolution.createImageView(logoArcherP1, UNIT_ICON_WIDTH, UNIT_ICON_HEIGHT);
        listUnitP1.addAll(List.of(unit1P1, unit2P1, unit3P1));

        /**List of units player 2. */
        final ImageView unit1P2 = ViewResolution.createImageView(selectedSwordsmenP2, UNIT_ICON_WIDTH, UNIT_ICON_HEIGHT);

        final ImageView unit2P2 = ViewResolution.createImageView(logoSpearmenP2, UNIT_ICON_WIDTH, UNIT_ICON_HEIGHT);

        final ImageView unit3P2 = ViewResolution.createImageView(logoArcherP2, UNIT_ICON_WIDTH, UNIT_ICON_HEIGHT);
        listUnitP2.addAll(List.of(unit1P2, unit2P2, unit3P2));

        /**List of ImageView arrows for player 1. */
        for (int i = 0; i < this.laneNumber; i++) {
            final ImageView arrow1P1 = ViewResolution.createImageView(arrowP1, ARROW_W, ARROW_H);
            listArrowP1.add(arrow1P1);
        }
        listArrowP1.get(this.laneNumber / 2).setImage(selectedArrowP1);

        /**List of ImageView arrows for player 2. */
       for (int i = 0; i < this.laneNumber; i++) {
            final ImageView arrow1P2 = ViewResolution.createImageView(arrowP2, ARROW_W, ARROW_H);
            listArrowP2.add(arrow1P2);
        }
        listArrowP2.get(this.laneNumber / 2).setImage(selectedArrowP2);

        /**Buttons. */
        /**Button EXIT. */
        final Button exit = new Button("Exit");
        exit.setMinSize(BUTTONS_W, BUTTONS_H);
        exit.setOnMouseClicked(e -> {
            Music.buttonsMusic(ViewImages.BUTTON_SOUND);
            closeProgram(pane);
        });
        exit.setStyle(Style.BUTTON_1);

        /**Button MENU. */
        final Button menu = new Button("Menu");
        menu.setStyle(Style.BUTTON_1);
        menu.setPrefSize(BUTTONS_W, BUTTONS_H);
        menu.setOnMouseClicked(e ->  {
            Music.buttonsMusic(ViewImages.BUTTON_SOUND);
            returnMainMenu(pane);
        });

        /**Button MUSIC. */
        final ToggleButton stopMusic = new ToggleButton("Music On/Off");
        stopMusic.setStyle(Style.BUTTON_1);
        stopMusic.setPrefSize(BUTTONS_W, BUTTONS_H);
        stopMusic.setOnMouseClicked(e -> {
            if (stopMusic.isSelected()) {
                Music.buttonsMusic(ViewImages.BUTTON_SOUND);
                Music.musicStop();
            } else {
                Music.buttonsMusic(ViewImages.BUTTON_SOUND);
                Music.musicStart(ViewImages.MUSIC_2);
            }
        });

        /**Labels. */
        /**Label TIMER. */
        timer = new Label("TIMER");
        timer.setStyle(Style.LABEL);
        timer.setPrefSize(LABEL_W, LABEL_H);
        timer.setAlignment(Pos.CENTER);

        /**List of Labels for the respawn time of players units. */
        for (final var type : UnitViewType.values()) {
            final Label label = this.unitTimerLabel(type.getWaitingTime());
            unitBoxes.put(type, label);
            if (type.getPlayer().equals(PlayerType.PLAYER1)) { 
                unit1ListLabel.add(label);
            } else {
                unit2ListLabel.add(label);
            }
        }

        /**Settings for the player labels, with name and score of the player. */
        for (final var type : PlayerType.values()) {
            final Label score = this.scorePlayerLabel(type);
            labelsScore.put(type, score);
            if (type.equals(PlayerType.PLAYER1)) {
                player1 = score;
            } else {
                player2 = score;
            }
        }

        /**Layout. */
        final List<VBox> vBoxplayer1 = new ArrayList<>();
        for (int i = 0;  i < listUnitP1.size(); i++) {
            final VBox vBox1 = new VBox();
            vBox1.setAlignment(Pos.CENTER);
            vBox1.getChildren().addAll(listUnitP1.get(i), unit1ListLabel.get(i));
            vBoxplayer1.add(vBox1);
        }

        final List<VBox> vBoxplayer2 = new ArrayList<>();
        for (int i = 0;  i < listUnitP2.size(); i++) {
            final VBox vBox2 = new VBox();
            vBox2.setAlignment(Pos.CENTER);
            vBox2.getChildren().addAll(listUnitP2.get(i), unit2ListLabel.get(i));
            vBoxplayer2.add(vBox2);
        }

        final HBox topMenu = new HBox(TOPMENU_W);
        topMenu.setAlignment(Pos.CENTER);
        topMenu.getChildren().addAll(vBoxplayer1);
        topMenu.getChildren().add(timer);
        topMenu.getChildren().addAll(vBoxplayer2);
        topMenu.setPadding(new Insets(PADDING_H, 0, PADDING_H, 0));

        final HBox bottomMenu = new HBox(BOTTOMMENU_W);
        bottomMenu.getChildren().addAll(player1, menu, stopMusic, exit, player2);
        bottomMenu.setAlignment(Pos.CENTER);
        bottomMenu.setPadding(new Insets(PADDING_H, 0, PADDING_H, 0));

        final VBox leftMenu = new VBox(LEFT_RIGTH_MENU_H);
        leftMenu.setAlignment(Pos.CENTER);
        leftMenu.getChildren().addAll(listArrowP1);

        final VBox rightMenu = new VBox(LEFT_RIGTH_MENU_H);
        rightMenu.setAlignment(Pos.CENTER);
        rightMenu.getChildren().addAll(listArrowP2);

        /**BorderPane. */
        final BorderPane borderpane = new BorderPane();
        borderpane.setTop(topMenu);
        borderpane.setLeft(leftMenu);
        borderpane.setBottom(bottomMenu);
        borderpane.setRight(rightMenu);
        borderpane.setCenter(this.field.getGrid());
        borderpane.setPrefSize(BORDERPANE_W, BORDERPANE_H);

        /**KeyInput. */
        borderpane.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            switch (e.getCode()) {
            // TODO
//            case (KeyCode) InputType.UP_LANE_1.getKey():
//                break;    //doesn't work :(
            case W: /** Up Lane Player 1. */
                this.observer.controlPrevLane(PlayerType.PLAYER1);
                break;
            case S:
                this.observer.controlNextLane(PlayerType.PLAYER1);
                break;
            case A:
                this.observer.controlPrevUnit(PlayerType.PLAYER1);
                break;
            case D:
                this.observer.controlNextUnit(PlayerType.PLAYER1);
                break;
            case UP:
                this.observer.controlPrevLane(PlayerType.PLAYER2);
                break;
            case DOWN:
                this.observer.controlNextLane(PlayerType.PLAYER2);
                break;
            case LEFT:
                this.observer.controlPrevUnit(PlayerType.PLAYER2);
                break;
            case RIGHT:
                this.observer.controlNextUnit(PlayerType.PLAYER2);
                break;
            case SPACE:
                this.observer.spawnUnit(PlayerType.PLAYER1);
                break;
            case ENTER:
                this.observer.spawnUnit(PlayerType.PLAYER2);
                break;
            default:
                break;
            }
        });

        pane.getChildren().add(gameBackGround);
        pane.getChildren().add(borderpane);

        return pane;
    }

    @Override
    public void updateSelectLane(final PlayerType player, final int index, final int next) {
        final List<ImageView> tempList = player.equals(PlayerType.PLAYER1) ? new ArrayList<>(listArrowP1) : new ArrayList<>(listArrowP2);
        tempList.get(index).setImage(arrowP1);
        tempList.get(next).setImage(selectedArrowP1);
        if (player.equals(PlayerType.PLAYER1)) {
            listArrowP1.get(index).setImage(arrowP1);
            listArrowP1.get(next).setImage(selectedArrowP1);
        }
        if (player.equals(PlayerType.PLAYER2)) {
            listArrowP2.get(index).setImage(arrowP2);
            listArrowP2.get(next).setImage(selectedArrowP2);
        }
    }

    @Override
    public void updateSelectUnit(final PlayerType player, final int index, final int next) {
      if (player.equals(PlayerType.PLAYER1)) {
          listUnitP1.get(next).setImage(this.unitSelectedP1.get(next));
          listUnitP1.get(index).setImage(this.unitImageP1.get(index));
      }
      if (player.equals(PlayerType.PLAYER2)) {
          listUnitP2.get(next).setImage(this.unitSelectedP2.get(next));
          listUnitP2.get(index).setImage(this.unitImageP2.get(index));
      }
  }

    @Override
    public void updateTimer(final int mins, final int seconds) {
        Platform.runLater(() -> timer.setText(String.format("%02d:%02d", mins, seconds)));
    }

    @Override
    public void updatePlayerTimer(final int seconds, final PlayerType player) {
        Platform.runLater(() -> {
            unitBoxes.forEach((type, label) -> {
                if (type.getPlayer().equals(player)) {
                    final int timer = type.getWaitingTime() - seconds;
                    label.setText(timer <= 0 ? "SPAWN" : timer + " sec");
                    label.setTextFill(timer <= 0 ? Color.GREEN : Color.RED);
                }
        });
     });
    }

    @Override
    public void updateScorePlayer() {
        labelsScore.forEach((type, label) -> {
            label.setText("SCORE " + getPlayerName(type) + ": " + observer.getScore(type));
        });
    }

    @Override
    public void setObserver(final Controller observer) {
        this.observer = observer;
    }

    @Override
    public void closeProgram(final Pane pane) {
        final boolean answer = ConfirmBox.display("Quitting", "Do you want to quit?");
        if (answer) {
            final Stage stage = (Stage) pane.getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void show(final EnumMap<UnitViewType, List<Pair<Integer, Integer>>> units) {
        this.field.clear();
        units.forEach((unit, positions) -> positions.forEach(p -> this.field.add(unit, p)));
    }

    /**
     * Method to return on main menu with winner box or close the program. 
     * @param player name
     * */
    public void winnerMBoxResult(final String player) {
        final boolean answer = WinnerBox.winner(player);
        if (answer) {
            scenaMenu = new MainMenu();
            try {
                pane.getChildren().setAll(scenaMenu.createPane());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else {
            final Stage stage = (Stage) pane.getScene().getWindow();
            stage.close();
        }
    }

}
