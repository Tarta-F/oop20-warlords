package view;

public enum ScenarioViewType {

    /**Scenario 1 images. */
    SCENARIO_1("EARTH", "/GrassBackground.jpg", "/Ground.png"),

    /**Scenario 2 images. */
    SCENARIO_2("AIR", "/GrassBackground2.png", "/Ground2.png"),

    /**Scenario 3 images. */
    SCENARIO_3("WIND", "/.png", "/.png");

    private final String description;
    private final String backgroundPath;
    private final String groundPath;

    ScenarioViewType(final String description, final String backgroundPath, final String groundPath) {
        this.description = description;
        this.backgroundPath = backgroundPath;
        this.groundPath = groundPath;
    }

    /**
     * Method to get to BACKGROUND.
     * @return the path of the background image
     */
    public String getBackgroundPath() {
        return this.backgroundPath;
    }

    /**
     * Method to get the GRID BACKGROUND.
     * @return the path of the field ground image
     */
    public String getGroundPath() {
        return this.groundPath;
    }

    /** 
     * Method to get DESCRIPTION of the scenario.
     * @return description of the Scenario
     */
    public String getDescription() {
        return this.description;
    }
}
