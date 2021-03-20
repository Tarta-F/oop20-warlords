package utilities.counters;

public interface LimitCounter extends Counter {

    /**
     * Increase the counter value only if under the limit.
     */
    @Override
    void increment();

    /**
     * @return boolean that indicates if the value has reached the limit
     */
    boolean isOver();
}
