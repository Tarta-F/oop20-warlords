package view.sound;

import java.net.URISyntaxException;
import java.util.EnumMap;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import view.constants.ResourcesConstants;

/**
 * Scenes sounds implementation.
 * */
public final class Music {

    private static final Music SINGLETON = new Music();
    private static final double VOLUME = 0.8;

    private final EnumMap<Sounds, MediaPlayer> soundMedias = new EnumMap<>(Sounds.class);

    private MediaPlayer currentMusic;
    private MediaPlayer currentSound;
    private boolean stop;

    private Music() {
    }

    /**
     * @return
     *      SINGLETON
     */
    public static Music getMusic() {
        return SINGLETON;
    }

    /**
     * Method to set the MUSIC. 
     * @param song String
     */
    private void switchMusic(final MediaPlayer music) {
        if (SINGLETON.currentMusic != null) {
            SINGLETON.currentMusic.stop();
        }
        SINGLETON.currentMusic = music;

        if (!SINGLETON.stop) {
            SINGLETON.currentMusic.play();
        }
    }

    private MediaPlayer getMediaPlayer(final String path) {
        Media sound = null;
        try {
            sound = new Media(Music.class.getResource(path).toURI().toString());
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }

        final MediaPlayer mp = new MediaPlayer(sound);
        mp.setOnEndOfMedia(() -> mp.seek(Duration.ZERO));
        mp.setVolume(VOLUME);

        return mp;
    }
    /**
     * Method to set the MUSIC. 
     * @param sound
     *      sound to play
     * */
//    public void play(final String sound) {
//        switchMusic(sound);
//    }

    /**
     * Method to set the MUSIC. 
     * @param sound
     *      sound to play
     * */
    public void play(final Sounds sound) {
        this.soundMedias.putIfAbsent(sound, this.getMediaPlayer(sound.getPath()));
        this.switchMusic(this.soundMedias.get(sound));
    }

    /**
     * Method to on/off the MUSIC. 
     * */
    public void musicOnOff() {
        if (SINGLETON.stop) {
            SINGLETON.currentMusic.play();
        } else {
            SINGLETON.currentMusic.stop();
        }
        SINGLETON.stop = !SINGLETON.stop;
    }

    /**
      * Method to set BUTTONS SOUND. 
      * @param song String
     * */
    public void playSound(final String song) {
        Media sound = null;
        try {
            sound = new Media(Music.class.getResource(song).toURI().toString());
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
        SINGLETON.currentSound = new MediaPlayer(sound);
        SINGLETON.currentSound.setVolume(VOLUME);

        if (!SINGLETON.stop) {
            SINGLETON.currentSound.play();
        }
    }

    /**
     * Method to play BUTTONS SOUND. 
     */
    public void playButtonSound() {
        playSound(ResourcesConstants.BUTTON_SOUND);
    }

    /**
     * Plays the sound for Starting Match.
     */
    public void startMatchSound() {
        playSound(ResourcesConstants.BUTTON_START);
    }

    /**
     * Method to stop BUTTONS SOUND. 
     */
    public void buttonStop() {
        SINGLETON.currentSound.stop();
    }

}
