package algorithms.search;

/**
 * This is an abstract class which represents the state
 */
abstract public class AState implements Comparable {
    private String state;
    protected double cost;
    protected AState cameFrom;

    /**
     * Constructor of new State
     *
     * @param state
     */
    public AState(String state) {
        this.state = state;
    }

    /**
     * @return the cost of this state
     */
    public double getCost() {
        return cost;
    }

    /**
     * set the cost of this state to the given cost
     *
     * @param cost
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * @return which state the current state came from
     */
    public AState getCameFrom() {
        return cameFrom;
    }

    /**
     * Set the previous state the current state came from
     *
     * @param cameFrom - which state he came from
     */
    public void setCameFrom(AState cameFrom) {
        this.cameFrom = cameFrom;
    }

    /**
     * @return The string which this state represents
     */
    @Override
    public String toString() {
        return state;
    }


    /**
     * Compare 2 objects
     *
     * @param obj
     * @return if they are equals or not
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AState) {
            AState state1 = (AState) obj;
            return this.state.equals(state1.state);
        }
        return false;
    }

    /**
     * random hash code method designed to be usually unique
     *
     * @return the hash code of the string
     */
    @Override
    public int hashCode() {
        return state.hashCode();
    }

    /**
     * compare two state depending on their cost
     *
     * @param o
     * @return positive value if the cost of the current object is larger
     * negative value if reversed
     * zero if their cost is equal
     */
    @Override
    public int compareTo(Object o) {
        if (o != null)
            return (int) (this.cost - ((AState) o).cost);
        return -1;
    }
}
