package algorithms.search;

abstract public class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected String name;
    protected int numOfNodes;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getNumberOfNodesEvaluated() {
        return numOfNodes+"";
    }
}
