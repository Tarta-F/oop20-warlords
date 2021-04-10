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
    private static double screenWidth = SCREEN.getWidth();
    private static double screenHeight = SCREEN.getHeight();

    private ViewResolution() {
        /**Not called. */
    }

    /**
     * Method to calculate the Width resolution. 
     * @param constant Double
     * @return Width Resolution Double
     * */
    public static double screenResolutionWidth(final double constant) {
        return screenWidth / constant;
    }

    /**
     * Method to calculate the Height resolution. 
     * @param constant Double
     * @return Height Resolution Double
     * */
    public static double screenResolutionHeight(final double constant) {
        return screenHeight / constant;
    }
}