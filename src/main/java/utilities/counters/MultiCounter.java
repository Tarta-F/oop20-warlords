package utilities.counters;

/**
 * A {@link Counter} that allows a multi increment of the value.
 */
public interface MultiCounter extends Counter {

    /**
     * Increment the value of the given number.
     * 
     * @param number 
     *      value of which increase
     */
    void multiIncrement(int number);

}
