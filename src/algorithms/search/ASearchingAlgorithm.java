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
        return numOfNodes + "";
    }

    public long measureAlgorithmTimeMillis(ISearchable search) {
        long startTime = System.currentTimeMillis();
        Solution sol = solve(search);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
