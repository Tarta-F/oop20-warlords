package view;

import java.awt.Dimension;
import java.awt.Toolkit;

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
    
    static boolean answer;
    
    public static boolean display(String title, String message) {
       
        Stage window = new Stage();    
       
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight(); 
        
        window.initModality(Modality.APPLICATION_MODAL);  
        window.setTitle(title);    

        
        Label label = new Label();
        label.setText(message);
        label.setAlignment(Pos.CENTER);

        Button yesButton = new Button("yes");
        Button noButton = new Button("no");
        
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });
        
        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });
        
        VBox layout = new VBox (10);
        layout.getChildren().addAll(label,yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene (layout,sw/4, sh/4);    
        window.setScene(scene);            
        window.setResizable(false);
        window.showAndWait();              
                                       
        
        return (answer);  //viene impostata poi ad una variabile nel main per essere stampata
    
        //test
        
    }
}