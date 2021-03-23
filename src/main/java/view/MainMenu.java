package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import constants.ViewConstants;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.Exit;

public class MainMenu extends Application { 

    private Stage window;

    public static void main(final String[] args) {
        launch(args);
    }

    public final void start(final Stage primaryStage) throws Exception {

        //screen size
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();

        window = primaryStage;

        window.setOnCloseRequest(e -> {
           e.consume();
           closeProgram();
        });

        //ViewConstants.DIVISOR

        //image
        Image logoImage  = new Image(this.getClass().getResourceAsStream("/logo.png"));
        ImageView logo = new ImageView(logoImage);
        logo.setFitWidth(sw / ViewConstants.DIVISOR_4);
        logo.setFitHeight(sh / ViewConstants.DIVISOR_8);

        Image logoSpearmanImage  = new Image(this.getClass().getResourceAsStream("/spearman.png"));
        ImageView logoSpearman = new ImageView(logoSpearmanImage);
        logoSpearman.setFitWidth(sw / ViewConstants.DIVISOR_7);
        logoSpearman.setFitHeight(sh / ViewConstants.DIVISOR_3);

        Image logoArcherImage  = new Image(this.getClass().getResourceAsStream("/archer.png"));
        ImageView logoArcher = new ImageView(logoArcherImage);
        logoArcher.setFitWidth(sw / ViewConstants.DIVISOR_8);
        logoArcher.setFitHeight(sh / ViewConstants.DIVISOR_3);


        //buttons
        Button campaign = new Button("CAMPAIGN");
        campaign.setPrefSize(sw / ViewConstants.DIVISOR_15, sh / ViewConstants.DIVISOR_15);
        Button versus = new Button("VERSUS");
        versus.setPrefSize(sw / ViewConstants.DIVISOR_15, sh / ViewConstants.DIVISOR_15);
        Button tutorials = new Button("TUTORIALS");
        tutorials.setPrefSize(sw / ViewConstants.DIVISOR_15, sh / ViewConstants.DIVISOR_15);
        Button exit = new Button("EXIT");
        exit.setPrefSize(sw / ViewConstants.DIVISOR_15, sh / ViewConstants.DIVISOR_15);
        exit.setOnAction(e -> closeProgram());


        //layout
        VBox menu = new VBox(sh / ViewConstants.DIVISOR_15);
        menu.setAlignment(Pos.CENTER);
        menu.getChildren().addAll(logo, campaign, versus, tutorials, exit);
        menu.setPadding(new Insets(sh / ViewConstants.DIVISOR_30, 0, sh / ViewConstants.DIVISOR_30, 0));

        VBox sinistra = new VBox();
        sinistra.setAlignment(Pos.CENTER);
        sinistra.getChildren().add(logoArcher);
        sinistra.setPadding(new Insets(0, 0, 0, sw / ViewConstants.DIVISOR_15));

        VBox destra = new VBox();
        destra.setAlignment(Pos.CENTER);
        destra.getChildren().add(logoSpearman);
        destra.setPadding(new Insets(0, sw / ViewConstants.DIVISOR_15, 0, 0));

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(menu);
        borderPane.setLeft(sinistra);
        borderPane.setRight(destra);
        borderPane.setId("menu");

        Scene scene = new Scene(borderPane,  sw / ViewConstants.DIVISOR_1_5, sh / ViewConstants.DIVISOR_1_5);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        window.setScene(scene);
        window.show();
        window.setResizable(false);

    }
        /**Method to close the program with a confirm box.*/
        private void closeProgram() {
        boolean answer = Exit.display("quitting", "Do you want to quit?");
        if (answer) {
              window.close();
            }
        }
}