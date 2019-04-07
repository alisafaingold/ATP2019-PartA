package algorithms.search;

/**
 * An abstract class of searching algorithm which implements ISearchingAlgorithm interface
 */
abstract public class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected String name;
    protected int numOfNodes;

    /**
     * @return searching algorithm name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return number of nodes that the algorithm passed through during the search
     */
    @Override
    public String getNumberOfNodesEvaluated() {
        return numOfNodes + "";
    }

    /**
     * @param search
     * @return how long it took for the algorithm to find a solution
     */
    public long measureAlgorithmTimeMillis(ISearchable search) {
        if (search != null) {
            long startTime = System.currentTimeMillis();
            solve(search);
            long endTime = System.currentTimeMillis();
            return endTime - startTime;
        }
        return 0;
    }
}
