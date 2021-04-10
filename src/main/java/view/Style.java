package view;

import constants.ViewConstants;

/**
 * This class contain the various CSS codes that we use for the appearance of the elements in the view.
 */
public final class Style {

    /**Constant CSS for the appearance of buttons. */
    public static final String BUTTON_1 = "-fx-text-fill: #FFFFFF;\r\n"
            + " -fx-background-radius: 6;\r\n"
            + " -fx-font-weight: bold;\r\n "
            + " -fx-background-color: linear-gradient(#000000, #696969);\r\n"
            + " -fx-font-size: " + ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_150) + ";";

    /**Constant CSS for the appearance of buttons. */
    public static final String BUTTON_2 = "-fx-text-fill: #000000;\r\n"
            + " -fx-background-radius: 6;\r\n"
            + " -fx-font-weight: bold;\r\n "
            + " -fx-background-color: linear-gradient(#FFFFFF, #696969);\r\n"
            + " -fx-font-size: " + ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_150) + ";";

    /**Constant CSS for the appearance of labels. */
    public static final String LABEL = "-fx-text-fill: #FFFFFF;\r\n"
            + " -fx-background-radius: 6;\r\n"
            + " -fx-font-weight: bold;\r\n "
            + " -fx-background-color: rgba(0, 0, 0, 0.5);\r\n"
            + " -fx-font-size: " + ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_150) + ";";

    private Style() { }
}