package view.sound;

import java.net.URISyntaxException;
import java.util.EnumMap;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * Class to manage music and sounds.
 */
public final class Music {

    private static final Music SINGLETON = new Music();
    private static final double VOLUME = 0.3;
    private static final double VOLUME2 = 0.5;

    private final EnumMap<Sounds, MediaPlayer> soundMedias = new EnumMap<>(Sounds.class);

    private MediaPlayer currentMusic;
    private MediaPlayer currentSound;
    private boolean stop;

    private Music() {
    }

    /**
     * @return
     *      Music SINGLETON
     */
    public static Music getMusic() {
        return SINGLETON;
    }

    /**
     * Method to set the background music.
     * 
     * @param music
     *      The {@link MediaPlayer to set as #currentMusic}
     */
    private void switchMusic(final MediaPlayer music) {
        if (SINGLETON.currentMusic != null) {
            SINGLETON.currentMusic.stop();
        }

        music.setOnEndOfMedia(() -> music.seek(Duration.ZERO));
        SINGLETON.currentMusic = music;

        if (!SINGLETON.stop) {
            SINGLETON.currentMusic.play();
        }
    }

    /**
     * Extract the {@link MediaPlayer} from the given file.
     * 
     * @param path
     *      A {@link String} that specifies the file path
     * @return
     *      the corresponding {@link MediaPlayer}
     */
    private MediaPlayer getMediaPlayer(final String path) {
        Media sound = null;
        try {
            sound = new Media(Music.class.getResource(path).toURI().toString());
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }

        final MediaPlayer mp = new MediaPlayer(sound);
        mp.setVolume(VOLUME);

        return mp;
    }

    /**
     * Method to set the MUSIC. 
     * @param sound
     *      sound to play
     */
    public void play(final Sounds sound) {
        this.soundMedias.putIfAbsent(sound, this.getMediaPlayer(sound.getPath()));
        this.switchMusic(this.soundMedias.get(sound));
    }

    /**
     * Method to on/off sounds effects. 
     */
    public void musicOnOff() {
        if (SINGLETON.stop) {
            SINGLETON.currentMusic.play();
        } else {
            SINGLETON.currentMusic.stop();
        }
        SINGLETON.stop = !SINGLETON.stop;
    }

    /**
     * Method to stop current music. 
     */
    public void musicStop() {
            SINGLETON.currentMusic.stop();
    }

    /**
      * Method to play specific sound once. 
      * 
      * @param sound 
      *         the {@link Sounds} to be played
      */
    public void playSound(final Sounds sound) {
        this.soundMedias.putIfAbsent(sound, this.getMediaPlayer(sound.getPath()));
        SINGLETON.currentSound = new MediaPlayer(this.soundMedias.get(sound).getMedia());
        SINGLETON.currentSound.setVolume(VOLUME2);
        SINGLETON.currentSound.setOnEndOfMedia(() -> SINGLETON.currentSound.seek(Duration.UNKNOWN));
        if (!SINGLETON.stop) {
            SINGLETON.currentSound.play();
        }
    }

    /**
     * Method to play button sound. 
     */
    public void playButtonSound() {
        this.playSound(Sounds.BUTTON);
    }

    /**
     * Method to play the sound for Starting Game.
     */
    public void startMatchSound() {
        this.playSound(Sounds.START_GAME);
    }
    /**
     * Method to play the sound for spawning troups.
     */
    public void playSpawnSound() {
        this.playSound(Sounds.SPAWN_TROUP);
    }
    /**
     * Method to play the sound for Victory result.
     */ 
    public void playVictorySound() {
        this.playSound(Sounds.VICTORY_RESULT);
    }
    /**
     * Method to play the sound for Draw result.
     */
    public void playDrawSound() {
        this.playSound(Sounds.DRAW_RESULT);
    }
}
