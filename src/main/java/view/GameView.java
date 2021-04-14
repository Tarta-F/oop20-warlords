package view;

import constants.GameConstants;
import constants.ViewConstants;
import controllers.Controller;
import model.PlayerType;
import constants.ViewImages;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;
import java.util.Arrays;
import java.util.EnumMap;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import javafx.stage.Stage;

/**
 * This class is the BattleField game view.
 */
public final class GameView extends Region {

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

    private List<Image> unitSelectedP1;
    private List<Image> unitImageP1;
    private List<Image> unitSelectedP2;
    private List<Image> unitImageP2;
    private Label timer;
    private Controller observer;

    //private final String backgroundF; //fil
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


    public GameView(final int laneNumber, final String background, final String ground, final String player1Name, final String player2Name) {
        this.laneNumber = laneNumber;
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        //this.backgroundF = background;
        this.scenario = new Image(this.getClass().getResourceAsStream(background));
        this.field = new GameFieldViewImpl(laneNumber, ViewConstants.GRID_COLUMNS, ground);
    }

    public Parent createGameView() throws IOException {
        /**Pane. */
        final Pane pane = new Pane();
        /**BackGround. */
        final ImageView gameBackGround = new ImageView(scenario);
        gameBackGround.setFitWidth(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_1_3));
        gameBackGround.setFitHeight(ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_1_3));

        this.unitImageP1 = new ArrayList<>(Arrays.asList(logoSwordsmenP1, logoSpearmenP1, logoArcherP1));
        this.unitSelectedP1 = new ArrayList<>(Arrays.asList(selectedSwordsmenP1, selectedSpearmenP1, selectedArcherP1));
        this.unitImageP2 = new ArrayList<>(Arrays.asList(logoSwordsmenP2, logoSpearmenP2, logoArcherP2));
        this.unitSelectedP2 = new ArrayList<>(Arrays.asList(selectedSwordsmenP2, selectedSpearmenP2, selectedArcherP2));

        ImageView unitP1 = new ImageView(selectedSwordsmenP1);
        utilSetDimension1(unitP1);  //al posto di ripetere sempre le stesse 2 righe
        listUnitP1.add(unitP1);

        unitP1 = new ImageView(logoSpearmenP1);
        utilSetDimension1(unitP1);
        listUnitP1.add(unitP1);

        unitP1 = new ImageView(logoArcherP1);
        utilSetDimension1(unitP1);
        listUnitP1.add(unitP1);

        ImageView unitP2 = new ImageView(selectedSwordsmenP2);
        utilSetDimension1(unitP2);
        listUnitP2.add(unitP2);

        unitP2 = new ImageView(logoSpearmenP2);
        utilSetDimension1(unitP2);
        listUnitP2.add(unitP2);

        unitP2 = new ImageView(logoArcherP2);
        utilSetDimension1(unitP2);
        listUnitP2.add(unitP2);

        /**List of ImageView arrows for the player 1*/
        ImageView arrow1P1;
        for (int i = 0; i < this.laneNumber; i++) {
            arrow1P1 = new ImageView(arrowP1);
            utilSetDimension2(arrow1P1);
            listArrowP1.add(arrow1P1);
        }
        listArrowP1.get(this.laneNumber / 2).setImage(selectedArrowP1);

        /**List of ImageView arrows for the player 1*/
        ImageView arrow1P2;
        for (int i = 0; i < this.laneNumber; i++) {
            arrow1P2 = new ImageView(arrowP2);
            utilSetDimension2(arrow1P2);
            listArrowP2.add(arrow1P2);
        }
        listArrowP2.get(this.laneNumber / 2).setImage(selectedArrowP2);

        /**Buttons. */
        /**Button EXIT. */
        final Button exit = new Button("Exit");
        exit.setMinSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_30),
                ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_30));
        exit.setOnMouseClicked(e -> closeProgram(pane));
        exit.setStyle(Style.BUTTON_1);

        /**Button MENU. */
        final Button menu = new Button("Menu");
        menu.setStyle(Style.BUTTON_1);
        menu.setMinSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_30),
                ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_30));
        menu.setOnMouseClicked(e ->  returnMainMenu(pane));

        /**Labels. */
        /**Label TIMER. */
        timer = new Label("TIMER");
        timer.setStyle(Style.LABEL);
        utilSetDimension3(timer);
        timer.setAlignment(Pos.CENTER);

        // TODO Cambia hp in score da Controller
        /**Label Player 1 HEALTH. */
        final int scoreP1 = 0;
        final Label player1 = new Label("SCORE " + this.player1Name + ": " + scoreP1);
        player1.setStyle(Style.LABEL);
        utilSetDimension3(player1);
        player1.setAlignment(Pos.CENTER);

        /** Health Points player2 */
        final int scoreP2 = 0;
        final Label player2 = new Label("SCORE " + this.player2Name + ": " + scoreP2);
        player2.setStyle(Style.LABEL);
        player2.setPrefHeight(ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_20));
        player2.setPrefWidth(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_15));
        player2.setAlignment(Pos.CENTER);

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

        final HBox topMenu = new HBox(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_25));
        topMenu.setAlignment(Pos.CENTER);
        topMenu.getChildren().addAll(vBoxplayer1);
        topMenu.getChildren().add(timer);
        topMenu.getChildren().addAll(vBoxplayer2);
        topMenu.setPadding(new Insets(ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_60), 0, 
                ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_60), 0));

        final HBox bottomMenu = new HBox(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_30));
        bottomMenu.getChildren().addAll(player1, menu, exit, player2);
        bottomMenu.setAlignment(Pos.CENTER);
        bottomMenu.setPadding(new Insets(ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_60), 0,
                ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_60), 0));

        final VBox leftMenu = new VBox(ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_20));
        leftMenu.setAlignment(Pos.CENTER);
        leftMenu.getChildren().addAll(listArrowP1);

        final VBox rightMenu = new VBox(ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_20));
        rightMenu.setAlignment(Pos.CENTER);
        rightMenu.getChildren().addAll(listArrowP2);

        /**BorderPane. */
        final BorderPane borderpane = new BorderPane();
        borderpane.setTop(topMenu);
        borderpane.setLeft(leftMenu);
        borderpane.setBottom(bottomMenu);
        borderpane.setRight(rightMenu);
        borderpane.setCenter(this.field.getGrid());
        borderpane.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_1_3),
                ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_1_3));

        /**KeyInput. */
        borderpane.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            switch (e.getCode()) {
            // TODO MIGLIORIA
//            case (KeyCode) InputType.UP_LANE_1.getKey():
//                break;    //doesn't work :(
            case W:
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

    private Label unitTimerLabel(final long l) {
        final Label respawnLabel = new Label(l + " sec");
        respawnLabel.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_30), 
                ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_25));
        respawnLabel.setAlignment(Pos.CENTER);
        respawnLabel.setStyle(Style.LABEL);
        return respawnLabel;
    }

    public void updateSelectLane(final PlayerType playerType, final int index, final int next) {
        final ArrayList<ImageView> tempList = playerType.equals(PlayerType.PLAYER1) ? new ArrayList<>(listArrowP1) : new ArrayList<>(listArrowP2);
        tempList.get(index).setImage(arrowP1);
        tempList.get(next).setImage(selectedArrowP1);
        if (playerType.equals(PlayerType.PLAYER1)) {
            listArrowP1.get(index).setImage(arrowP1);
            listArrowP1.get(next).setImage(selectedArrowP1);
        }
        if (playerType.equals(PlayerType.PLAYER2)) {
            listArrowP2.get(index).setImage(arrowP2);
            listArrowP2.get(next).setImage(selectedArrowP2);
        }
    }

    public void updateSelectUnit(final PlayerType playerType, final int index, final int next) {
      if (playerType.equals(PlayerType.PLAYER1)) {
          listUnitP1.get(next).setImage(this.unitSelectedP1.get(next));
          listUnitP1.get(index).setImage(this.unitImageP1.get(index));
      }
      if (playerType.equals(PlayerType.PLAYER2)) {
          listUnitP2.get(next).setImage(this.unitSelectedP2.get(next));
          listUnitP2.get(index).setImage(this.unitImageP2.get(index));
      }
  }

    public void updateTimer(final int mins, final int seconds) {
        Platform.runLater(() -> timer.setText(String.format("%02d:%02d", mins, seconds)));
    }

    public void updatePlayerTimer(final int mins, final int seconds, final PlayerType playerType) {
        Platform.runLater(() -> {
            unitBoxes.forEach((type, label) -> {
                if (type.getPlayer().equals(playerType)) {
                    final int timer = type.getWaitingTime() - seconds;
                    label.setText(Integer.toString(timer < 0 ? 0 : timer));
                }
            });
        });
    }

    private void utilSetDimension1(final ImageView imageView) {
      imageView.setFitWidth(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_15));
      imageView.setFitHeight(ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_15));
    }

    private void utilSetDimension2(final ImageView imageView) {
        imageView.setFitWidth(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_20));
        imageView.setFitHeight(ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_20));
      }

    private void utilSetDimension3(final Label label) {
        label.setPrefSize(ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_15), 
                ViewResolution.screenResolutionHeight(ViewConstants.DIVISOR_20));
    }


    public void setObserver(final Controller observer) {
        this.observer = observer;
    }

    /**Method to close the program with a confirm box. */
    private void closeProgram(final Pane pane) {
        final boolean answer = Exit.display("Quitting", "Do you want to quit?");
        if (answer) {
            final Stage stage = (Stage) pane.getScene().getWindow();
            stage.close();
        }
    }

    /**Method to return to main menu with a confirm box.*/
    private void returnMainMenu(final Pane pane) {
        final boolean answer = Exit.display("Quitting", "Return to main menu?");
        if (answer) {
            scenaMenu = new MainMenu();
            try {
                pane.getChildren().setAll(scenaMenu.createMainMenu());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Draw in the Field the given units.
     * @param units a set containing the info for drawing the units in the right place
     */
    public void show(final EnumMap<UnitViewType, List<Pair<Integer, Integer>>> units) {
        this.field.clear();
        units.forEach((unit, positions) -> positions.forEach(p -> this.field.add(unit, p)));
    }

}
