package view;

import constants.ViewConstants;
import controllers.Controller;
import controllers.ControllerImpl;
import model.PlayerType;
import constants.ViewImages;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.EnumMap;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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
    private int laneNumber;

    private final Image scenario;
    /**Taking screen size for the adaptation of the various elements of the view to the resolution of the screen.*/
    final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    final int sw = (int) screen.getWidth();
    final int sh = (int) screen.getHeight();

    private List<ImageView> listArrowP1 = new ArrayList<>();
    private List<ImageView> listArrowP2 = new ArrayList<>();
    private List<ImageView> listUnitP1 = new ArrayList<>();
    private List<ImageView> listUnitP2 = new ArrayList<>();

    private List<Image> unitSelected;
    private List<Image> unitImage;
    private Label timer;
    private Label timerP1;
    private Label timerP2;
    private Controller observer;



    //public GameView(/* final Controller observer */) {
//        this.observer = observer;
//        try {
//            this.createContent();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
    //}

    Image unit1Image  = new Image(this.getClass().getResourceAsStream("/SwordsmenUnit.png"));
    Image unit2Image  = new Image(this.getClass().getResourceAsStream("/SpearmenUnit.png"));
    Image unit3Image = new Image(this.getClass().getResourceAsStream("/ArcherUnit.png"));
    Image unit1SelectedImage = new Image(this.getClass().getResourceAsStream("/SelectedSwordsmenUnit.png"));
    Image unit2SelectedImage = new Image(this.getClass().getResourceAsStream("/SelectedSpearmenUnit.png"));
    Image unit3SelectedImage = new Image(this.getClass().getResourceAsStream("/SelectedArcherUnit.png"));
    Image arrowImageP1 = new Image(this.getClass().getResourceAsStream("/ArrowPlayer1.png"));
    Image arrowImageP2 = new Image(this.getClass().getResourceAsStream("/ArrowPlayer2.png"));
    Image arrowSelectedImageP1 = new Image(this.getClass().getResourceAsStream("/SelectedArrowPlayer1.png"));
    Image arrowSelectedImageP2 = new Image(this.getClass().getResourceAsStream("/SelectedArrowPlayer2.png"));

    public GameView(final int laneNumber, final Image scenario) {
        this.scenario = scenario;
        this.field = new GameFieldViewImpl(laneNumber, ViewConstants.GRID_COLUMNS, Optional.of(scenario));
    }

    public GameView(final int laneNumber) {
        this.laneNumber = laneNumber;
        this.scenario = new Image(this.getClass().getResourceAsStream("/GrassBackground.jpg"));
        this.field = new GameFieldViewImpl(this.laneNumber, ViewConstants.GRID_COLUMNS, Optional.empty());
    }


    public Parent createGameView() throws IOException {


        Pane pane = new Pane();
        //background image
        //Image backgroundImg  = new Image(this.getClass().getResourceAsStream("/GrassBackground.jpg"));
        ImageView gameBackGround = new ImageView(scenario);
        gameBackGround.setFitWidth(sw / ViewConstants.DIVISOR_1_5);
        gameBackGround.setFitHeight(sh / ViewConstants.DIVISOR_1_5);

        /**Set of all images used.*/

        Image groundImage = new Image(this.getClass().getResourceAsStream("/Ground.png"));

        BackgroundSize backgroundSize = new BackgroundSize(sw / ViewConstants.DIVISOR_1_5, sh / ViewConstants.DIVISOR_1_5,
                true, true, true, false);

        BackgroundImage backgroundImage = new BackgroundImage(groundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, backgroundSize);

        Background background = new Background(backgroundImage);

      this.unitImage = new ArrayList<>(Arrays.asList(unit1Image, unit2Image, unit3Image));
      this.unitSelected = new ArrayList<>(Arrays.asList(unit1SelectedImage, unit2SelectedImage, unit3SelectedImage));

        ImageView unitP1 = new ImageView(unit1SelectedImage);
        utilSetDimension(unitP1);  //al posto di ripetere sempre le stesse 2 righe
        listUnitP1.add(unitP1);

        unitP1 = new ImageView(unit2Image);
        utilSetDimension(unitP1);
        listUnitP1.add(unitP1);

        unitP1 = new ImageView(unit3Image);
        utilSetDimension(unitP1);
        listUnitP1.add(unitP1);

        ImageView unitP2 = new ImageView(unit1SelectedImage);
        utilSetDimension(unitP2);
        listUnitP2.add(unitP2);

        unitP2 = new ImageView(unit2Image);
        utilSetDimension(unitP2);
        listUnitP2.add(unitP2);

        unitP2 = new ImageView(unit3Image);
        utilSetDimension(unitP2);
        listUnitP2.add(unitP2);

        /**List of ImageView arrows for the player 1*/
        ImageView arrow1P1;

        for (int i = 0; i < this.laneNumber; i++) {
            arrow1P1 = new ImageView(arrowImageP1);
            utilSetDimension(arrow1P1);
            listArrowP1.add(arrow1P1);
        }
        listArrowP1.get(this.laneNumber / 2).setImage(arrowSelectedImageP1);

        /**List of ImageView arrows for the player 1*/
        ImageView arrow1P2;

        for (int i = 0; i < this.laneNumber; i++) {
            arrow1P2 = new ImageView(arrowImageP2);
            utilSetDimension(arrow1P2);
            listArrowP2.add(arrow1P2);
        }
        listArrowP2.get(this.laneNumber / 2).setImage(arrowSelectedImageP2);

        /**Creation button Exit.*/
        Button exit = new Button("Exit");
        exit.setMinSize(sw / ViewConstants.DIVISOR_30, sh / ViewConstants.DIVISOR_30);
        exit.setOnMouseClicked(e -> closeProgram(pane));
        exit.setStyle(Style.BUTTON_1);

        /**Creation button Menu.*/
        Button menu = new Button("Menu");
        menu.setStyle(Style.BUTTON_1);
        menu.setMinSize(sw / ViewConstants.DIVISOR_30, sh / ViewConstants.DIVISOR_30);
        menu.setOnMouseClicked(e ->  returnMainMenu(pane));

        /** label that display the Timer */
        timer = new Label("TIMER");
        timer.setStyle(Style.LABEL);
        timer.setPrefHeight(sh / ViewConstants.DIVISOR_20);
        timer.setPrefWidth(sw / ViewConstants.DIVISOR_15);
        timer.setAlignment(Pos.CENTER);

        /** Health Points player1 */
        int hp1 = 8;
        Label player1 = new Label("PLAYER 1 HP: " + hp1);
        player1.setStyle(Style.LABEL);

        player1.setPrefHeight(sh / ViewConstants.DIVISOR_20);
        player1.setPrefWidth(sw / ViewConstants.DIVISOR_15);
        player1.setAlignment(Pos.CENTER);

        //prova timerP1
        timerP1 = new Label("P1:00");
        timerP1.setStyle(Style.LABEL);

        timerP1.setPrefHeight(sh / ViewConstants.DIVISOR_20);
        timerP1.setPrefWidth(sw / ViewConstants.DIVISOR_15);
        timerP1.setAlignment(Pos.CENTER);

        timerP2 = new Label("P2:00");
        timerP2.setStyle(Style.LABEL);

        timerP2.setPrefHeight(sh / ViewConstants.DIVISOR_20);
        timerP2.setPrefWidth(sw / ViewConstants.DIVISOR_15);
        timerP2.setAlignment(Pos.CENTER);

        /** Health Points player2 */
        int hp2 = 8;
        Label player2 = new Label("PLAYER 2 HP: " + hp2);
        player2.setStyle(Style.LABEL);
        player2.setPrefHeight(sh / ViewConstants.DIVISOR_20);
        player2.setPrefWidth(sw / ViewConstants.DIVISOR_15);
        player2.setAlignment(Pos.CENTER);

        /**Layout of the GameView.*/
        HBox topMenu = new HBox(sw / ViewConstants.DIVISOR_25);
        topMenu.setAlignment(Pos.CENTER);
        topMenu.getChildren().addAll(listUnitP1);
        topMenu.getChildren().addAll(timer);
        topMenu.getChildren().addAll(listUnitP2);
        topMenu.setPadding(new Insets(sh / ViewConstants.DIVISOR_60, 0, sh / ViewConstants.DIVISOR_60, 0));

        HBox bottomMenu = new HBox(sw / ViewConstants.DIVISOR_30);
        bottomMenu.getChildren().addAll(timerP1, player1, menu, exit, player2, timerP2);
        bottomMenu.setAlignment(Pos.CENTER);
        bottomMenu.setPadding(new Insets(sh / ViewConstants.DIVISOR_60, 0, sh / ViewConstants.DIVISOR_60, 0));

        VBox leftMenu = new VBox(sh / ViewConstants.DIVISOR_20);
        leftMenu.setAlignment(Pos.CENTER);
        leftMenu.getChildren().addAll(listArrowP1);

        VBox rightMenu = new VBox(sh / ViewConstants.DIVISOR_20);
        rightMenu.setAlignment(Pos.CENTER);
        rightMenu.getChildren().addAll(listArrowP2);

        /**Set position of the various elements created.*/
        BorderPane borderpane = new BorderPane();
        borderpane.setTop(topMenu);
        borderpane.setLeft(leftMenu);
        borderpane.setBottom(bottomMenu);
        borderpane.setRight(rightMenu);
        borderpane.setCenter(this.field.getGrid());
        borderpane.setPrefSize(sw / ViewConstants.DIVISOR_1_5, sh / ViewConstants.DIVISOR_1_5);

        borderpane.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            switch (e.getCode()) {
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

//        this.field.add(UnitViewType.SWORDSMEN_PLAYER1, Pair.of(0, 0));
//        this.field.add(UnitViewType.SWORDSMEN_PLAYER1, Pair.of(0, 1));
//        this.field.add(UnitViewType.SWORDSMEN_PLAYER1, Pair.of(0, 2));
//        this.field.add(UnitViewType.SWORDSMEN_PLAYER1, Pair.of(0, 3));
//        this.field.add(UnitViewType.SWORDSMEN_PLAYER1, Pair.of(0, 4));

        pane.getChildren().add(gameBackGround);
        pane.getChildren().add(borderpane);
        return pane;
    }

    public void updateSelectLane(final PlayerType playerType, final int index, final int next) {
        final ArrayList<ImageView> tempList = playerType.equals(PlayerType.PLAYER1) ? new ArrayList<>(listArrowP1) : new ArrayList<>(listArrowP2);
        tempList.get(index).setImage(arrowImageP1);
        tempList.get(next).setImage(arrowSelectedImageP1);
        if (playerType.equals(PlayerType.PLAYER1)) {
            listArrowP1.get(index).setImage(arrowImageP1);
            listArrowP1.get(next).setImage(arrowSelectedImageP1);
        }
        if (playerType.equals(PlayerType.PLAYER2)) {
            listArrowP2.get(index).setImage(arrowImageP2);
            listArrowP2.get(next).setImage(arrowSelectedImageP2);
        }
    }

    public void updateSelectUnit(final PlayerType playerType, final int index, final int next) {
      if (playerType.equals(PlayerType.PLAYER1)) {
          listUnitP1.get(next).setImage(this.unitSelected.get(next));
          listUnitP1.get(index).setImage(this.unitImage.get(index));
      }
      if (playerType.equals(PlayerType.PLAYER2)) {
          listUnitP2.get(next).setImage(this.unitSelected.get(next));
          listUnitP2.get(index).setImage(this.unitImage.get(index));
      }
  }

    public void updateTimer(final int mins, final int seconds) {
        Platform.runLater(() -> timer.setText(String.format("%02d:%02d", mins, seconds)));
    }

    //prova
    public void updatePlayerTimer(final int mins, final int seconds) {
        Platform.runLater(() -> timerP1.setText(String.format("%02d:%02d", mins, seconds)));
    }

    private void utilSetDimension(final ImageView imageView) {
        imageView.setFitWidth(sw / ViewConstants.DIVISOR_20);
        imageView.setFitHeight(sh / ViewConstants.DIVISOR_20);
    }

    public void setObserver(final Controller observer) {
        this.observer = observer;
    }

    /**Method to close the program with a confirm box.*/
    private void closeProgram(final Pane pane) {
    boolean answer = Exit.display("Quitting", "Do you want to quit?");
    if (answer) {
        final Stage stage = (Stage) pane.getScene().getWindow();
        stage.close();
        }
    }

    /**Method to return to main menu with a confirm box.*/
    private void returnMainMenu(final Pane pane) {
    boolean answer = Exit.display("Quitting", "Return to main menu?");
    if (answer) {
        scenaMenu = new MainMenu();
        try {
            pane.getChildren().setAll(scenaMenu.createMainMenu());
        } catch (IOException e1) {
            // TODO Auto-generated catch block
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
