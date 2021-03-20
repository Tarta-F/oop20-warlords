package utilities.counters;

public class CounterImpl implements Counter {

    private int value;

    public CounterImpl() {
        this.value = 0;
    }

    /**
     * 
     */
    @Override
    public void increment() {
        this.value++;
    }

    /**
     * 
     */
    @Override
    public int getValue() {
        return this.value;
    }

}
