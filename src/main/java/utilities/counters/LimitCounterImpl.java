package utilities.counters;

public class LimitCounterImpl extends CounterImpl implements LimitCounter {

    private final int limit;

    public LimitCounterImpl(final int limit) {
        super();
        this.limit = limit;
    }

    /**
     * Increment with limit bound check.
     */
    @Override
    public void increment() {
        if (!this.isOver()) {
            super.increment();
        }
    }

    /**
     * Check if value is over the limit.
     */
    @Override
    public boolean isOver() {
        return super.getValue() >= this.limit;
    }

}
