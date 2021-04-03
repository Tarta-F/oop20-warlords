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
    private static double screenWidth;
    private static double screenHeight;

    private ViewResolution() {
        /**Not called. */
    }

    /**
     * Method to calculate the Width resolution. 
     * @param constant Integer
     * @return screenWidth Integer
     * */
    public static double screenResolutionWidth(final double constant) {
        screenWidth = SCREEN.getWidth();
        screenWidth = screenWidth / constant;
        return screenWidth;
    }

    /**
     * Method to calculate the Height resolution. 
     * @param constant Integer
     * @return screenHeigtht Integer
     * */
    public static double screenResolutionHeight(final double constant) {
        screenHeight = SCREEN.getHeight();
        screenHeight = screenHeight / constant;
        return screenHeight;
    }
}
