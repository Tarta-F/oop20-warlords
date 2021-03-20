package utilities.counters;

public interface MultiCounter extends Counter {

    /**
     * Increment the Counter value of the given number.
     * 
     * @param number value of which increase
     */
    void multiIncrement(int number);
}
