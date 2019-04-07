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
     * @return Solution to the maze solved by the algorithm
     */
    @Override
    public Solution solve(ISearchable domain) {
        if (domain != null) {
            //Create all necessary objects
            PriorityQueue<AState> priorityQueue = new PriorityQueue<>();
            HashMap<String, Boolean> visited = new HashMap<>();
            AState startState = domain.getStartState();
            AState endState = domain.getGoalState();
            //add the start to the data structures
            priorityQueue.add(startState);
            visited.put(startState.toString(), false);
            // while the queue is not empty
            while (!priorityQueue.isEmpty()) {
                startState = priorityQueue.remove();
                numOfNodes++;
                //if this cell is what we are looking for (end position)
                if (startState.equals(endState)) {
                    return new Solution(startState);
                } else {
                    //for each of the neighbors of current cell
                    for (AState state : domain.getAllPossibleStates(startState)) {
                        //if its not in the visited set
                        if (!visited.containsKey(state.toString())) {
                            //set the cost of the current state as his cost and the cost of all states up to it
                            state.setCost(startState.getCost() + state.getCost());
                            //add him to the data structures
                            visited.put(state.toString(), false);
                            priorityQueue.add(state);
                        }
                    }
                    visited.put(startState.toString(), true);
                }
            }
            return new Solution(domain.getStartState());
        }
        //if the algorithm failed to reach the end position, return an empty solution
        return new Solution();
    }

}
