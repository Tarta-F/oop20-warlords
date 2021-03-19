package utilities;

public class Counter {

    private int value;

    public Counter() {
        this.value = 0;
    }

    /**
     *  Increments the value of the counter.
     */
    public void increment() {
       this.value++;
    }

    /**
     * @return the value of this counter.
     */
    public int getValue() {
        return this.value;
    }

}
