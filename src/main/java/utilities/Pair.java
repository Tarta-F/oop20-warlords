package utilities;

/*
 * A standard generic Pair<X,Y>, with getters, hashCode, equals, and toString well implemented. 
 */

public class Pair<X, Y> {

    private final X x;
    private final Y y;

    public Pair(final X x, final Y y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x element of this pair
     */
    public X getX() {
        return x;
    }

    /**
     * @return the y element of this pair
     */
    public Y getY() {
        return y;
    }

    /**
     * @return the hashcode for this pair
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
        return result;
    }

    /**
     * @param obj the object to compare
     * @return true if the objects are equals, false as well
     */
    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Pair other = (Pair) obj;
        if (x == null) {
            if (other.x != null) {
                return false;
            }
        } else if (!x.equals(other.x)) {
            return false;
        }
        if (y == null) {
            if (other.y != null) {
                return false;
            }
        } else if (!y.equals(other.y)) {
            return false;
        }
        return true;
    }

    /**
     * @return the string rapresenting this pair
     */
    @Override
    public String toString() {
        return "Pair [x=" + x + ", y=" + y + "]";
}


}
