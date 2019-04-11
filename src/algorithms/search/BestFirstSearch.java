package algorithms.search;

import java.util.*;

/**
 * A Best First Search algorithm class
 */
public class BestFirstSearch extends ASearchingAlgorithm {

    public BestFirstSearch() {
        name = "Best First Search";
    }

    /**
     * @param domain
     * @return Solution to the problem solved by the algorithm
     */
    @Override
    public Solution solve(ISearchable domain) {
        if (domain != null) {
            //Create all necessary objects
            PriorityQueue<AState> priorityQueue = new PriorityQueue<>();
            HashMap<String, Boolean> visited = new HashMap<>();
            AState currState = domain.getStartState();
            AState endState = domain.getGoalState();
            //add the start to the data structures
            priorityQueue.add(currState);
            visited.put(currState.toString(), false);
            // while the queue is not empty
            while (!priorityQueue.isEmpty()) {
                currState = priorityQueue.remove();
                numOfNodes++;
                //if this cell is what we are looking for (end position)
                    //for each of the neighbors of current cell
                    for (AState state : domain.getAllPossibleStates(currState)) {
                        if (state.equals(endState)) {
                            return new Solution(state);
                        }
                        //if its not in the visited set
                        if (!visited.containsKey(state.toString())) {
                            //set the cost of the current state as his cost and the cost of all states up to it
                            state.setCost(currState.getCost() + state.getCost());
                            //add him to the data structures
                            visited.put(state.toString(), false);
                            priorityQueue.add(state);
                        }
                    }
                    visited.put(currState.toString(), true);

            }
            //if the algorithm failed to reach the end position, return the start state
            return new Solution(domain.getStartState());
        }
        return new Solution();
    }

}
