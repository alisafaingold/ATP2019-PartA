package algorithms.search;

/**
 * Interface of all the searching algorithms
 */
public interface ISearchingAlgorithm {
    /**
     * This function get domain which is searchable problem
     *
     * @param domain
     * @return the solution to the problem if was found
     */
    Solution solve(ISearchable domain);

    /**
     * @return the name of the searching algorithm
     */
    String getName();

    /**
     * @return how many states the algorithm has been visited
     */
    String getNumberOfNodesEvaluated();
}
