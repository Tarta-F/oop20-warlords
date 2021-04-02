package view;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * 
 * Class that set the resolution size of all elements in the view, based on the resolution of the screen. 
 *
 */
public final class ViewResolution {

    private static final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
    private static int screenWidth;
    private static int screenHeight;

    private ViewResolution() {
        /**Not called. */
    }

    /**
     * Method to calculate the Width resolution. 
     * @param constant Integer
     * @return screenWidth Integer
     * */
    public static int screenResolutionWidth(final int constant) {
        screenWidth = (int) SCREEN.getWidth();
        screenWidth = screenWidth / constant;
        return screenWidth;
    }

    /**
     * Method to calculate the Height resolution. 
     * @param constant Integer
     * @return screenHeigtht Integer
     * */
    public static int screenResolutionHeight(final int constant) {
        screenHeight = (int) SCREEN.getHeight();
        screenHeight = screenHeight / constant;
        return screenHeight;
    }
}
