package view;

import view.constants.ViewConstants;

/**
 * This class contain the various CSS codes that we use for the appearance of the elements in the view.
 */
public final class Style {

    private static final String FONT_SIZE = " -fx-font-size: " + ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_150);
    private static final String RADIUS = " -fx-background-radius: 6;\r\n";
    private static final String BOLD = " -fx-font-weight: bold;\r\n ";

    /**Constant CSS for the appearance of buttons. */
    public static final String BUTTON_1 = "-fx-text-fill: #FFFFFF;\r\n"
            + RADIUS
            + BOLD
            + " -fx-background-color: linear-gradient(#000000, #696969);\r\n"
            + FONT_SIZE + ";";

    /**Constant CSS for the appearance of buttons. */
    public static final String BUTTON_2 = "-fx-text-fill: #000000;\r\n"
            + RADIUS
            + BOLD
            + " -fx-background-color: linear-gradient(#FFFFFF, #696969);\r\n"
            + FONT_SIZE + ";";

    /**Constant CSS for the appearance of labels. */
    public static final String LABEL = "-fx-text-fill: #FFFFFF;\r\n"
            + RADIUS
            + BOLD
            + " -fx-background-color: rgba(0, 0, 0, 0.75);\r\n"
            + FONT_SIZE + ";";

    /**Constant CSS for the appearance of labels. */
    public static final String FONT = "-fx-font-weight: bold;\r\n "
            + FONT_SIZE + ";";

    /**Constants CSS for the appearance of layouts. */
    public static final String LAYOUT = "-fx-background-color: grey;";

    /** Constant CSS for the Text. */
    public static final String TEXT = "-fx-text-fill: #000000;\r\n"
            + RADIUS
            + BOLD
            + " -fx-background-color: #FFFFFF;\r\n"
            + FONT_SIZE + ";";

    private Style() { }

}
