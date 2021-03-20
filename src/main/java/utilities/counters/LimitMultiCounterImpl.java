package utilities.counters;

import java.util.stream.IntStream;

public class LimitMultiCounterImpl extends LimitCounterImpl implements LimitMultiCounter {

    public LimitMultiCounterImpl(final int limit) {
        super(limit);
    }

    /**
     * Increase value of the given number if the result is in the limit.
     * @param number value of which increase
     */
    @Override
    public void multiIncrement(final int number) {
        IntStream.range(0, number).forEach(n -> super.increment());
    }

}
