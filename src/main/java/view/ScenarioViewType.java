package view;

public enum ScenarioViewType {
    /** Scenario 1 imgaes. */
    SCENARIO_1("/GrassBackground.png", "/Ground.png"),

    /** Scenario 2 images. */
    SCENARIO_2("/GrassBackground2.png", "/GrassBackground2.png"),

    /** Scenario 3 images. */
    SCENARIO_3("/.png", "/.png");

    private final String backgroundPath;
    private final String groundPath;

    ScenarioViewType(final String backgroundPath, final String groundPath) {
        this.backgroundPath = backgroundPath;
        this.groundPath = groundPath;
    }

    /**
     * 
     * @return the path of the background image
     */
    public String getBackgroundPath() {
        return this.backgroundPath;
    }

    /**
     * 
     * @return the path of the field ground image
     */
    public String getGroundPath() {
        return this.groundPath;
    }
}
