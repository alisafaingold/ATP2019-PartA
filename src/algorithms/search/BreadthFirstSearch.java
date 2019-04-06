package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm {

    public BreadthFirstSearch() {
        name = "BreadthFirstSearch";
    }

    @Override
    public Solution solve(ISearchable domain) {
        Queue<AState> Q = new LinkedList<>();
        HashMap<String, Boolean> visited = new HashMap<>();
        AState s = domain.getStartState();
        AState e = domain.getGoalState();

        Q.offer(s);
        visited.put(s.toString(), true);
        while (Q.peek() != null) {
            AState current = Q.poll();
            numOfNodes++;
            ArrayList<AState> neighbors = domain.getAllPossibleStates(current);
            /**if(e.equals(current)) {
             return new Solution(current);
             }**/
            // else{
            for (AState neighbor : neighbors) {
                if (!visited.containsKey(neighbor.toString())) {
                    visited.put(neighbor.toString(), true);
                    Q.offer(neighbor);
                }
                if (e.equals(neighbor)) {
                    return new Solution(neighbor);
                }
            }
            //visited.put(current.toString(), true);
            // }
        }
        return null;
    }
}