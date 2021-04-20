package utilities.counters;

import java.util.stream.IntStream;

/**
 * Basic implementation of {@link LimitCounter}.
 *
 */
public class LimitMultiCounterImpl extends LimitCounterImpl implements LimitMultiCounter {

    /**
     * Creates a {@link LimitMultiCounter} with the given limit.
     * 
     * @param limit
     *      the max value that the value can assume
     */
    public LimitMultiCounterImpl(final int limit) {
        super(limit);
    }

    /**
     * Increase the value of the given number.
     * If the value reached the limit, this method have no effect.
     * 
     * @param number 
     *      value of which increase
     */
    @Override
    public void multiIncrement(final int number) {
        IntStream.range(0, number).forEach(n -> super.increment());
    }

}
