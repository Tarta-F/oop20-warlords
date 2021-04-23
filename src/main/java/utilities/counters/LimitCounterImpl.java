package utilities.counters;

/**
 * Basic implementation of {@link LimitCounter}.
 */
public class LimitCounterImpl extends CounterImpl implements LimitCounter {

    private final int limit;

    /**
     * Creates a {@link LimitCounter} with the given limit.
     * 
     * @param limit
     *      the max value that the value can assume
     */
    public LimitCounterImpl(final int limit) {
        super();
        this.limit = limit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void increment() {
        if (!this.isOver()) {
            super.increment();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOver() {
        return super.getCount() >= this.limit;
    }

}
