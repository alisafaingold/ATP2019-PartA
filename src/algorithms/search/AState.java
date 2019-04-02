package algorithms.search;

abstract public class AState {
    protected String state;
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
}
