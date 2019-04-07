package algorithms.search;

abstract public class AState implements Comparable{
    private String state;
    protected double cost;
    protected AState cameFrom;

    public AState(String state) {
        this.state = state;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public AState getCameFrom() {
        return cameFrom;
    }

    //set parent to cell
    public void setCameFrom(AState cameFrom) {
        this.cameFrom = cameFrom;
    }

    @Override
    public String toString() {
        return state;
    }

    /**
     * state equivalence
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof AState){
            AState state1 = (AState)obj;
            return this.state.equals(state1.state);
        }
        return false;
    }

    /**
     * random hash code method designed to be usually unique
     */
    @Override
    public int hashCode() {
        return state.hashCode();
    }

    /**
     * compare two state depending on their cost
     * @param o
     * @return positive value if the cost of the current object is larger
     * negative value if reversed
     * zero if their cost is equal
     */
    @Override
    public int compareTo(Object o) {
        if(o != null)
            return (int)(this.cost - ((AState)o).cost);
        return -1;
    }
}
