package algorithms.search;

import java.util.*;

/**
 * A Breadth First Search algorithm class
 */
public class BreadthFirstSearch extends ASearchingAlgorithm {

    public BreadthFirstSearch() {
        name = "Breadth First Search";
    }

    /**
     * @param domain
     * @return Solution to the problem solved by the algorithm
     */
    @Override
    public Solution solve(ISearchable domain) {
        if (domain != null) {
            //Create all necessary objects
            Queue<AState> myQueue = new LinkedList<>();
            HashMap<String, Boolean> visited = new HashMap<>();
            AState startState = domain.getStartState();
            AState endState = domain.getGoalState();
            //add the start to the data structures
            myQueue.offer(startState);
            visited.put(startState.toString(), true);
            // while the queue is not empty
            while (myQueue.peek() != null) {
                //take out the first element in the queue
                AState current = myQueue.poll();
                numOfNodes++;
                //cells that could potentially be neighbors
                ArrayList<AState> neighbors = domain.getAllPossibleStates(current);
                //for each of the neighbors of current cell
                for (AState neighbor : neighbors) {
                    //if its not in the visited set
                    if (!visited.containsKey(neighbor.toString())) {
                        //add him to the data structures
                        visited.put(neighbor.toString(), true);
                        myQueue.offer(neighbor);
                    }
                    //if this cell is what we are looking for (end position)
                    if (endState.equals(neighbor)) {
                        return new Solution(neighbor);
                    }
                }
            }
            return new Solution(startState);
        }
        //if the algorithm failed to reach the end position, return an empty solution
        else {
            return new Solution();
        }
    }
}