package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import constants.ViewConstants;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class Exit {

    private static boolean answer;

    public static boolean display(final String title, final String message) {

        Stage window = new Stage();

        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight(); 

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);


        Label label = new Label();
        label.setText(message);
        label.setAlignment(Pos.CENTER);
        label.setPrefSize(sw / ViewConstants.DIVISOR_10, sh / ViewConstants.DIVISOR_25);
        label.setStyle("    -fx-text-fill: #FFFFFF;\r\n"
                + "    -fx-background-radius: 6;\r\n"
                + "    -fx-font-weight: bold;\r\n"
                + "    -fx-background-color: rgba(0, 0, 0, 0.5);");

        Button yesButton = new Button("YES");
        yesButton.setStyle(" -fx-background-radius: 6; -fx-font-weight: bold; -fx-text-fill: #FFFFFF; -fx-background-color: linear-gradient(#000000, #696969);");
        yesButton.setPrefSize(sw / ViewConstants.DIVISOR_25, sh / ViewConstants.DIVISOR_25);

        Button noButton = new Button("NO");
        noButton.setStyle(" -fx-background-radius: 6; -fx-font-weight: bold; -fx-text-fill: #FFFFFF; -fx-background-color: linear-gradient(#000000, #696969);");
        noButton.setPrefSize(sw / ViewConstants.DIVISOR_25, sh / ViewConstants.DIVISOR_25);

        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: grey;");
        Scene scene = new Scene(layout, sw / ViewConstants.DIVISOR_4, sh / ViewConstants.DIVISOR_4);
        window.setScene(scene);
        window.setResizable(false);
        window.showAndWait();


        return (answer);  //viene impostata poi ad una variabile nel main per essere stampata
    }
}