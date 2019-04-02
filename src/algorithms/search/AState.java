package algorithms.search;

abstract public class AState implements Comparable{
    protected String state;

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    protected double cost;
    protected AState cameFrom;

    public AState getCameFrom() {
        return cameFrom;
    }

    public void setCameFrom(AState cameFrom) {
        this.cameFrom = cameFrom;
    }

    @Override
    public String toString() {
        return state;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof AState){
            AState state1 = (AState)obj;
            return this.state.equals(state1.state);
        }
        return false;
    }

    @Override
    public int compareTo(Object o) {
        return (int)(this.cost - ((AState)o).cost);
    }
}
