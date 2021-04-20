package utilities.counters;

/**
 * A {@link Counter} with a limit value that cannot be surpassed.
 */
public interface LimitCounter extends Counter {

    /**
     * Increase the counter value only if under the limit.
     * If the value reached the limit, this method have no effect.
     */
    @Override
    void increment();

    /**
     * @return 
     *      true if the value has reached the limit
     */
    boolean isOver();

}
