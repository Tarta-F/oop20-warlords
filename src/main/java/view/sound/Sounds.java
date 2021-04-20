package view.sound;

import view.constants.ResourcesConstants;

public enum Sounds {

    /**
     * Sound for Menus.
     */
    MENU(ResourcesConstants.MUSIC),
    /**
     * Sound for Game.
     */
    GAME(ResourcesConstants.MUSIC_2),
    /**
     * Sound for Button pressed.
     */
    BUTTON(ResourcesConstants.BUTTON_SOUND),
    /**
     * Sound for Starting Game.
     */
    START_GAME(ResourcesConstants.BUTTON_START);

    private final String path;

    Sounds(final String path) {
        this.path = path;
    }

    /**
     * @return
     *      the path of the media to this sound
     */
    public String getPath() {
        return this.path;
    }
}
