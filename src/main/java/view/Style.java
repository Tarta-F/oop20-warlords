package view;

import constants.ViewConstants;

/**
 * This class contain the various constants that we use in the game.
 */
public final class Style {

    public static final String BOTTONI_1= "-fx-text-fill: #FFFFFF;\r\n"
            + " -fx-background-radius: 6;\r\n"
            + " -fx-font-weight: bold;\r\n "
            + " -fx-background-color: linear-gradient(#000000, #696969);\r\n"
            + " -fx-font-size: "+ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_150)+";";
    
    
    
    public static final String BOTTONI_2= "-fx-text-fill: #000000;\r\n"
            + " -fx-background-radius: 6;\r\n"
            + " -fx-font-weight: bold;\r\n "
            + " -fx-background-color: linear-gradient(#FFFFFF, #696969);\r\n"
            + " -fx-font-size: "+ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_150)+";";
    
    
    public static final String LABEL= "-fx-text-fill: #FFFFFF;\r\n"
            + " -fx-background-radius: 6;\r\n"
            + " -fx-font-weight: bold;\r\n "
            + " -fx-background-color: rgba(0, 0, 0, 0.5);\r\n"
            + " -fx-font-size: "+ViewResolution.screenResolutionWidth(ViewConstants.DIVISOR_150)+";";
    
    private Style () { }
}



//   + "      -fx-font-size:" + sw / ViewConstants.DIVISOR_150 + ";");