package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * Class that set the resolution size of all elements in the view, based on the resolution of the screen. 
 *
 */
public final class ViewResolution {

    private static final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
    private static double screenWidth = SCREEN.getWidth();
    private static double screenHeight = SCREEN.getHeight();

    private  ViewResolution() {
        /**Not called. */
    }

    /**
     * Method to calculate the WIDTH resolution of an element of the view. 
     * @param constant chosen
     * @return Width Resolution Double
     * */
    public static double screenResolutionWidth(final double constant) {
        return screenWidth / constant;
    }

    /**
     * Method to calculate the HEIGHT resolution of an element of the view. 
     * @param constant chosen
     * @return Height Resolution Double
     * */
    public static double screenResolutionHeight(final double constant) {
        return screenHeight / constant;
    }

    /**
     * Method to create an ImageView and set his resolution.
     * @param image Image
     * @param width chosen
     * @param height chosen
     * @return imageView with resolution sizes
     * */
    public static ImageView createImageView(final Image image, final double width, final double height) {
        final ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        return imageView;
    }
}
