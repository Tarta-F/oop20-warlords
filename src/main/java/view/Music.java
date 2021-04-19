package view;

import java.net.URISyntaxException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public final class Music {

    private static MediaPlayer media;
    private static MediaPlayer mediaButton;
    private Music() {
        /**Not called. */
    }
    
    /**Method to set scene music. */
    public static void musicStart(final String song) {

        Media sound = null;
        try {
            sound = new Media(Music.class.getResource(song).toURI().toString());
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
        media = new MediaPlayer(sound);
        media.setOnEndOfMedia(new Runnable() {
            public void run() {
                media.seek(Duration.ZERO);
            }
        });
        media.setVolume(1);
        media.play();
    }
   
    /**Method to stop scene music. */
    public static void musicStop() {
        media.stop();
    }
   
    /**Method to set buttons sound. */
    public static void buttonsMusic(final String song) {

        Media sound = null;
        try {
            sound = new Media(Music.class.getResource(song).toURI().toString());
        } catch (URISyntaxException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        mediaButton = new MediaPlayer(sound);
        mediaButton.setVolume(1);
        mediaButton.play();

    }
   
    /**Method to stop buttons sound. */
    public static void buttonStop() {
        mediaButton.stop();
    }

}
