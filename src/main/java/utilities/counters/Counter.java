package utilities.counters;

public interface Counter {

    /**
     * Increase the counter value by one.
     */
    void increment();

    /**
     * @return the current value
     */
    int getValue();
}
