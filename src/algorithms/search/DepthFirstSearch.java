package algorithms.search;

import java.util.*;

public class DepthFirstSearch extends ASearchingAlgorithm {

    public DepthFirstSearch() {
        name = "Depth First Search";
    }

    @Override
    public Solution solve(ISearchable domain) {
        if (domain != null) {
            Stack<AState> myStack = new Stack<>();
            HashSet<String> visited = new HashSet<>();
            AState startState = domain.getStartState();
            AState endState = domain.getGoalState();
            myStack.push(startState);
            while (!myStack.empty()) {
                numOfNodes++;
                AState curr = myStack.pop();
                if (curr.equals(endState)) {
                    return new Solution(curr);
                }
                ArrayList<AState> neighbors = domain.getAllPossibleStates(curr);
                for (AState neighbor : neighbors) {
                    if (!visited.contains(neighbor.toString())) {
                        visited.add(neighbor.toString());
                        myStack.push(neighbor);
                    }
                }
            }
            return new Solution(startState);
        }
        else{
            return new Solution();
        }
    }

}
