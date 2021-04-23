package view.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.EnumMap;
import org.apache.commons.lang3.tuple.Pair;
import constants.PlayerType;
import controllers.Controller;
import view.ConfirmBox;
import view.MainMenu;
import view.ViewFactory;
import view.ViewFactoryImpl;
import view.ViewResolution;
import view.constants.ViewConstants;
import view.sound.Music;
import view.sound.Sounds;
import view.constants.ResourcesConstants;
import view.constants.Style;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
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
    private static final double PADDING = ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_60);
    private static final double LABEL_PLAYER_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_10);

    private final ViewFactory factory = new ViewFactoryImpl();

    private final FieldView field;
    private final int laneNumber;
    private final Image scenario;

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
    private Controller observer;
    private Pane pane;

    /*Sets of all Images used. */
    /*Player 1. */
    private final Image logoSwordsmenP1  = new Image(this.getClass().getResourceAsStream(ResourcesConstants.P1_LOGO_SWORDSMEN));
    private final Image logoSpearmenP1  = new Image(this.getClass().getResourceAsStream(ResourcesConstants.P1_LOGO_SPEARMEN));
    private final Image logoArcherP1  = new Image(this.getClass().getResourceAsStream(ResourcesConstants.P1_LOGO_ARCHER));
    private final Image selectedSwordsmenP1  = new Image(this.getClass().getResourceAsStream(ResourcesConstants.P1_SELECTED_SWORDSMEN));
    private final Image selectedSpearmenP1  = new Image(this.getClass().getResourceAsStream(ResourcesConstants.P1_SELECTED_SPEARMEN));
    private final Image selectedArcherP1  = new Image(this.getClass().getResourceAsStream(ResourcesConstants.P1_SELECTED_ARCHER));
    private final Image arrowP1  = new Image(this.getClass().getResourceAsStream(ResourcesConstants.P1_ARROW));
    private final Image selectedArrowP1  = new Image(this.getClass().getResourceAsStream(ResourcesConstants.P1_SELECTED_ARROW));

    /*Player 2. */
    private final Image logoSwordsmenP2  = new Image(this.getClass().getResourceAsStream(ResourcesConstants.P2_LOGO_SWORDSMEN));
    private final Image logoSpearmenP2  = new Image(this.getClass().getResourceAsStream(ResourcesConstants.P2_LOGO_SPEARMEN));
    private final Image logoArcherP2  = new Image(this.getClass().getResourceAsStream(ResourcesConstants.P2_LOGO_ARCHER));
    private final Image selectedSwordsmenP2  = new Image(this.getClass().getResourceAsStream(ResourcesConstants.P2_SELECTED_SWORDSMEN));
    private final Image selectedSpearmenP2  = new Image(this.getClass().getResourceAsStream(ResourcesConstants.P2_SELECTED_SPEARMEN));
    private final Image selectedArcherP2  = new Image(this.getClass().getResourceAsStream(ResourcesConstants.P2_SELECTED_ARCHER));
    private final Image arrowP2  = new Image(this.getClass().getResourceAsStream(ResourcesConstants.P2_ARROW));
    private final Image selectedArrowP2  = new Image(this.getClass().getResourceAsStream(ResourcesConstants.P2_SELECTED_ARROW));

    public GameViewImpl(final int laneNumber, final int cellsNUmber, final String background, final String ground) {
        this.laneNumber = laneNumber;
        this.scenario = new Image(this.getClass().getResourceAsStream(background));
        this.field = new FieldViewImpl(laneNumber, cellsNUmber, ground);
    }

    /**
     * Method to return on main menu with a confirm box.
     * @param actual pane
     * */
    private void returnMainMenu(final Pane pane) {
        final boolean answer = ConfirmBox.display("Quitting", "Return to main menu?", "YES", "NO", "");
        if (answer) {
            final MainMenu scenaMenu = new MainMenu();
            this.observer.stopGame();
            try {
                Music.getMusic().play(Sounds.MENU);
                pane.getChildren().setAll(scenaMenu.createPane());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
    /**
    * Method to return on main menu with a confirm box.
    * @param message1 String
    * @param message2 String
    * @param player String
    * */
    private void resultBox(final String message1, final String message2, final String player) {
        final boolean choice = ConfirmBox.display(message1, message2, "MENU", "QUIT", player);
        if (choice) {
            final MainMenu scenaMenu = new MainMenu();
            try {
                Music.getMusic().playButtonSound();
                Music.getMusic().play(Sounds.MENU);
                this.pane.getChildren().setAll(scenaMenu.createPane());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else {
            final Stage stage = (Stage) this.pane.getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public Parent createPane() throws IOException {
        /*Music. */
        Music.getMusic().play(Sounds.GAME);

        /*Pane. */
        pane = new Pane();

        /*BackGround. */
        final ImageView gameBackGround = this.factory.createImageView(scenario, BORDERPANE_W, BORDERPANE_H);

        /*Lists of units logo used. */
        this.unitImageP1 = List.of(logoSwordsmenP1, logoSpearmenP1, logoArcherP1);
        this.unitSelectedP1 = List.of(selectedSwordsmenP1, selectedSpearmenP1, selectedArcherP1);
        this.unitImageP2 = List.of(logoSwordsmenP2, logoSpearmenP2, logoArcherP2);
        this.unitSelectedP2 = List.of(selectedSwordsmenP2, selectedSpearmenP2, selectedArcherP2);

        /*List of units player 1. */
        final ImageView unit1P1 = this.factory.createImageView(selectedSwordsmenP1, UNIT_ICON_WIDTH, UNIT_ICON_HEIGHT);
        final ImageView unit2P1 = this.factory.createImageView(logoSpearmenP1, UNIT_ICON_WIDTH, UNIT_ICON_HEIGHT);
        final ImageView unit3P1 = this.factory.createImageView(logoArcherP1, UNIT_ICON_WIDTH, UNIT_ICON_HEIGHT);
        listUnitP1.addAll(List.of(unit1P1, unit2P1, unit3P1));

        /*List of units player 2. */
        final ImageView unit1P2 = this.factory.createImageView(selectedSwordsmenP2, UNIT_ICON_WIDTH, UNIT_ICON_HEIGHT);
        final ImageView unit2P2 = this.factory.createImageView(logoSpearmenP2, UNIT_ICON_WIDTH, UNIT_ICON_HEIGHT);
        final ImageView unit3P2 = this.factory.createImageView(logoArcherP2, UNIT_ICON_WIDTH, UNIT_ICON_HEIGHT);
        listUnitP2.addAll(List.of(unit1P2, unit2P2, unit3P2));

        /*List of ImageView arrows for player 1. */
        for (int i = 0; i < this.laneNumber; i++) {
            final ImageView arrow1P1 = this.factory.createImageView(arrowP1, ARROW_W, ARROW_H);
            listArrowP1.add(arrow1P1);
        }
        listArrowP1.get(this.laneNumber / 2).setImage(selectedArrowP1);

        /*List of ImageView arrows for player 2. */
       for (int i = 0; i < this.laneNumber; i++) {
            final ImageView arrow1P2 = this.factory.createImageView(arrowP2, ARROW_W, ARROW_H);
            listArrowP2.add(arrow1P2);
        }
        listArrowP2.get(this.laneNumber / 2).setImage(selectedArrowP2);

        /*Buttons. */
        /*Button EXIT. */
        final Button exit = this.factory.createButton("Exit", Style.BUTTON_1, BUTTONS_W, BUTTONS_H);
        exit.setOnMouseClicked(e -> {
            Music.getMusic().playButtonSound();
            this.closeProgram(pane);
        });

        /*Button MENU. */
        final Button menu = this.factory.createButton("Menu", Style.BUTTON_1, BUTTONS_W, BUTTONS_H);
        menu.setOnMouseClicked(e ->  {
            Music.getMusic().playButtonSound();
            this.returnMainMenu(pane);
        });

        /*Button MUSIC. */
        final ToggleButton stopMusic = this.factory.createToggleButton("Music On/Off", Style.BUTTON_1, BUTTONS_W, BUTTONS_H);
        stopMusic.setOnMouseClicked(e -> {
            Music.getMusic().playButtonSound();
            Music.getMusic().musicOnOff();
        });

        /*Labels. */
        /*Label TIMER. */
        timer = this.factory.createLabel("TIMER", Style.LABEL, LABEL_W, LABEL_H);

        /*List of Labels for the respawn time of players units. */
        for (final var type : UnitViewType.values()) {
            final Label label = this.factory.createLabel(type.getWaitingTime() + " sec", Style.LABEL,
                    RESPAWN_LABEL_W, RESPAWN_LABEL_H);
            label.setTextFill(Color.RED);
            unitBoxes.put(type, label);
            if (type.getPlayer().equals(PlayerType.PLAYER1)) {
                unit1ListLabel.add(label);
            } else {
                unit2ListLabel.add(label);
            }
        }

        /*Settings for the player labels, with name and score of the player. */
        for (final var type : PlayerType.values()) {
            final Label score = this.factory.createLabel("SCORE " + this.observer.getPlayerName(type) + ": 0", Style.LABEL,
                    LABEL_PLAYER_W, LABEL_H);
            labelsScore.put(type, score);
        }

        /*Layout. */
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

        final HBox topMenu = this.factory.createHBox(TOPMENU_W, PADDING);
        topMenu.getChildren().addAll(vBoxplayer1);
        topMenu.getChildren().add(timer);
        topMenu.getChildren().addAll(vBoxplayer2);

        final HBox bottomMenu = this.factory.createHBox(BOTTOMMENU_W, PADDING);
        bottomMenu.getChildren().addAll(this.labelsScore.get(PlayerType.PLAYER1), menu, stopMusic, exit,
                this.labelsScore.get(PlayerType.PLAYER2));

        final VBox leftMenu = this.factory.createVBox(LEFT_RIGTH_MENU_H);
        leftMenu.getChildren().addAll(listArrowP1);

        final VBox rightMenu = this.factory.createVBox(LEFT_RIGTH_MENU_H);
        rightMenu.getChildren().addAll(listArrowP2);

        /*BorderPane. */
        final BorderPane borderpane = new BorderPane(this.field.getGrid());
        borderpane.setTop(topMenu);
        borderpane.setLeft(leftMenu);
        borderpane.setBottom(bottomMenu);
        borderpane.setRight(rightMenu);
        borderpane.setPrefSize(BORDERPANE_W, BORDERPANE_H);

        /*KeyInput. */
        borderpane.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            switch (e.getCode()) {
            case W: /* Up Lane Player 1. */
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
                if (this.observer.spawnUnit(PlayerType.PLAYER1)) {
                    Music.getMusic().playSpawnSound();
                }
                break;
            case ENTER:
                if (this.observer.spawnUnit(PlayerType.PLAYER2)) {
                    Music.getMusic().playSpawnSound();
                }
                break;
            default:
                break;
            }
        });
        pane.getChildren().addAll(List.of(gameBackGround, borderpane));

        return pane;
    }

    @Override
    public void updateSelectLane(final PlayerType player, final int index, final int next) {
        final List<ImageView> tempList = new ArrayList<>(player.equals(PlayerType.PLAYER1) ? listArrowP1 : listArrowP2);
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
    public void updateScorePlayer(final PlayerType player, final int score) {
        Platform.runLater(() -> this.labelsScore.get(player).setText("SCORE " + this.observer.getPlayerName(player) + ": " + score));
    }

    @Override
    public void setObserver(final Controller observer) {
        this.observer = observer;
    }

    @Override
    public void closeProgram(final Pane pane) {
        final boolean answer = ConfirmBox.display("Quitting", "Do you want to quit?", "YES", "NO", "");
        if (answer) {
            this.observer.stopGame();
            final Stage stage = (Stage) pane.getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void show(final EnumMap<UnitViewType, List<Pair<Integer, Integer>>> units) {
        Platform.runLater(() -> {
            this.field.clear();
            units.forEach((unit, positions) -> positions.forEach(p -> this.field.add(unit, p)));
        });
    }

    @Override
    public void winnerBoxResult(final String player) {
        Music.getMusic().musicStop();
        Music.getMusic().playVictorySound();
        Platform.runLater(() -> this.resultBox("Winner", " HAS WON", player));
    }

    @Override
    public void drawBoxResult(final String scores) {
        Music.getMusic().musicStop();
        Music.getMusic().playDrawSound();
        Platform.runLater(() -> this.resultBox("Draw", "   DRAW! ", scores));
    }

}
