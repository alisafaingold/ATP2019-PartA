package algorithms.search;

import java.util.*;

/**
 * A Depth First Search algorithm class
 */
public class DepthFirstSearch extends ASearchingAlgorithm {

    public DepthFirstSearch() {
        name = "Depth First Search";
    }

    /**
     * @param domain
     * @return Solution to the problem solved by the algorithm
     */
    @Override
    public Solution solve(ISearchable domain) {
        if (domain != null) {
            //Create all necessary objects
            Stack<AState> myStack = new Stack<>();
            HashSet<String> visited = new HashSet<>();
            AState startState = domain.getStartState();
            AState endState = domain.getGoalState();
            //add the start to the stack
            myStack.push(startState);
            // while the stack is not empty
            while (!myStack.empty()) {
                numOfNodes++;
                AState curr = myStack.pop();
                //if this cell is what we are looking for (end position)
                if (curr.equals(endState)) {
                    return new Solution(curr);
                }
                //cells that could potentially be neighbors
                ArrayList<AState> neighbors = domain.getAllPossibleStates(curr);
                //for each of the neighbors of current cell
                for (AState neighbor : neighbors) {
                    //if its not in the visited set
                    if (!visited.contains(neighbor.toString())) {
                        visited.add(neighbor.toString());
                        myStack.push(neighbor);
                    }
                }
            }
            //if the algorithm failed to reach the end position, return the start state
            return new Solution(startState);
        } else {
            return new Solution();
        }
    }

}
