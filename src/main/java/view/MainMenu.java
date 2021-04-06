package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.nio.file.ClosedWatchServiceException;

import constants.ViewConstants;
import constants.ViewImages;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundPosition;

public class MainMenu extends Application { 

    private Stage window;
    private GameTutorial scenaTutorial;
    private GameModeSelection scenaGameModeSelection;

    //screen size
    final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    final int sw = (int) screen.getWidth();
    final int sh = (int) screen.getHeight();

    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public final void start(final Stage primaryStage) throws Exception {

        window = primaryStage;
        Scene scene = new Scene(createContent(), sw / ViewConstants.DIVISOR_1_5, sh / ViewConstants.DIVISOR_1_5);
        window.setScene(scene);
        window.show();
        window.setResizable(false);
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
    }
        public Parent createContent() throws IOException {

        BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(sw / ViewConstants.DIVISOR_1_5, sh / ViewConstants.DIVISOR_1_5);
        //background image
        Image backgroundImg  = new Image(this.getClass().getResourceAsStream(ViewImages.MENU));
        ImageView menuBackGround = new ImageView(backgroundImg);
        menuBackGround.setFitWidth(sw / ViewConstants.DIVISOR_1_5);
        menuBackGround.setFitHeight(sh / ViewConstants.DIVISOR_1_5);

        //image
        Image logoImage  = new Image(this.getClass().getResourceAsStream(ViewImages.LOGO));
        ImageView logo = new ImageView(logoImage);
        logo.setFitWidth(sw / ViewConstants.DIVISOR_4);
        logo.setFitHeight(sh / ViewConstants.DIVISOR_8);

        Image logoSpearmanImage  = new Image(this.getClass().getResourceAsStream(ViewImages.SPEARMAN));
        ImageView logoSpearman = new ImageView(logoSpearmanImage);
        logoSpearman.setFitWidth(sw / ViewConstants.DIVISOR_7);
        logoSpearman.setFitHeight(sh / ViewConstants.DIVISOR_3);

        Image logoArcherImage  = new Image(this.getClass().getResourceAsStream(ViewImages.ARCHER));
        ImageView logoArcher = new ImageView(logoArcherImage);
        logoArcher.setFitWidth(sw / ViewConstants.DIVISOR_8);
        logoArcher.setFitHeight(sh / ViewConstants.DIVISOR_3);


        //buttons
        Button campaign = new Button("CAMPAIGN");
        campaign.setStyle(Style.BOTTONI_1);

        campaign.setPrefSize(sw / ViewConstants.DIVISOR_15, sh / ViewConstants.DIVISOR_15);

        Button versus = new Button("VERSUS");
        versus.setStyle(Style.BOTTONI_1);

        versus.setPrefSize(sw / ViewConstants.DIVISOR_15, sh / ViewConstants.DIVISOR_15);
        versus.setOnAction(e -> {
            scenaGameModeSelection = new GameModeSelection();
            try {
                borderPane.getChildren().setAll(scenaGameModeSelection.createContent());
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        Button tutorials = new Button("TUTORIALS");
        tutorials.setStyle(Style.BOTTONI_1);

        tutorials.setPrefSize(sw / ViewConstants.DIVISOR_15, sh / ViewConstants.DIVISOR_15);
        tutorials.setOnAction(e -> {
            try {
                scenaTutorial = new GameTutorial();
                borderPane.getChildren().setAll(scenaTutorial.createContent());
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        Button exitMenu = new Button("EXIT");
        exitMenu.setStyle(Style.BOTTONI_1);

        exitMenu.setPrefSize(sw / ViewConstants.DIVISOR_15, sh / ViewConstants.DIVISOR_15);
        exitMenu.setOnAction(e -> closeProgram());


        //layout
        VBox menu = new VBox(sh / ViewConstants.DIVISOR_15);
        menu.setAlignment(Pos.CENTER);
        menu.getChildren().addAll(logo, campaign, versus, tutorials, exitMenu);
        menu.setPadding(new Insets(sh / ViewConstants.DIVISOR_30, 0, sh / ViewConstants.DIVISOR_30, 0));

        VBox sinistra = new VBox();
        sinistra.setAlignment(Pos.CENTER);
        sinistra.getChildren().add(logoArcher);
        sinistra.setPadding(new Insets(0, 0, 0, sw / ViewConstants.DIVISOR_15));

        VBox destra = new VBox();
        destra.setAlignment(Pos.CENTER);
        destra.getChildren().add(logoSpearman);
        destra.setPadding(new Insets(0, sw / ViewConstants.DIVISOR_15, 0, 0));

        borderPane.getChildren().add(menuBackGround);
        borderPane.setCenter(menu);
        borderPane.setLeft(sinistra);
        borderPane.setRight(destra);
        return borderPane;

    }
        public void closeProgram() {
            boolean answer = Exit.display("quitting", "Do you want to quit?");
            if (answer) {
                System.exit(0);
                }
            } 
}
