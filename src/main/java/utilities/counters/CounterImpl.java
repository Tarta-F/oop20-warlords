package utilities.counters;

/**
 * Basic implementation of {@link Counte} with starting value 0.
 *
 */
public class CounterImpl implements Counter {

    private int value;

    /**
     * Creates a {@link Counter} with starting value 0.
     */
    public CounterImpl() {
        this.value = 0;
    }

    /**
     * Increments the value by one.
     */
    @Override
    public void increment() {
        this.value++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCount() {
        return this.value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        this.value = 0;
    }

}
