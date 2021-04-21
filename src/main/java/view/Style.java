package view;

import view.constants.ViewConstants;

/**
 * This class contain the various CSS codes that we use for the appearance of the elements in the view.
 */
public final class Style {

    private static final double FONT_W = ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_150);

    /**Constant CSS for the appearance of buttons. */
    public static final String BUTTON_1 = "-fx-text-fill: #FFFFFF;\r\n"
            + " -fx-background-radius: 6;\r\n"
            + " -fx-font-weight: bold;\r\n "
            + " -fx-background-color: linear-gradient(#000000, #696969);\r\n"
            + " -fx-font-size: " + FONT_W + ";";

    /**Constant CSS for the appearance of buttons. */
    public static final String BUTTON_2 = "-fx-text-fill: #000000;\r\n"
            + " -fx-background-radius: 6;\r\n"
            + " -fx-font-weight: bold;\r\n "
            + " -fx-background-color: linear-gradient(#FFFFFF, #696969);\r\n"
            + " -fx-font-size: " + FONT_W + ";";

    /**Constant CSS for the appearance of labels. */
    public static final String LABEL = "-fx-text-fill: #FFFFFF;\r\n"
            + " -fx-background-radius: 6;\r\n"
            + " -fx-font-weight: bold;\r\n "
            + " -fx-background-color: rgba(0, 0, 0, 0.75);\r\n"
            + " -fx-font-size: " + FONT_W + ";";

    /**Constant CSS for the appearance of labels. */
    public static final String FONT = "-fx-font-weight: bold;\r\n "
            + "-fx-font-size: " + FONT_W + ";";

    /**Constants CSS for the appearance of layouts. */
    public static final String LAYOUT = "-fx-background-color: grey;";

    private Style() { }

}
