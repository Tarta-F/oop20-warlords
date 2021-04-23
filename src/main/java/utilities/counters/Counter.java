package utilities.counters;

/**
 * Utility Class that holds a value that can be increased and got.
 */
public interface Counter {

    /**
     * Increase the value.
     */
    void increment();

    /**
     * @return the current value
     */
    int getCount();

    /**
     * Sets the value to the inital one.
     */
    void reset();

}
