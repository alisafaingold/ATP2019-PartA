package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm {

    public BreadthFirstSearch() {
        name = "Breadth First Search";
    }

    @Override
    public Solution solve(ISearchable domain) {
        if (domain != null) {
            Queue<AState> myQueue = new LinkedList<>();
            HashMap<String, Boolean> visited = new HashMap<>();
            AState startState = domain.getStartState();
            AState endState = domain.getGoalState();

            myQueue.offer(startState);
            visited.put(startState.toString(), true);
            while (myQueue.peek() != null) {
                AState current = myQueue.poll();
                numOfNodes++;
                ArrayList<AState> neighbors = domain.getAllPossibleStates(current);
                for (AState neighbor : neighbors) {
                    if (!visited.containsKey(neighbor.toString())) {
                        visited.put(neighbor.toString(), true);
                        myQueue.offer(neighbor);
                    }
                    if (endState.equals(neighbor)) {
                        return new Solution(neighbor);
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