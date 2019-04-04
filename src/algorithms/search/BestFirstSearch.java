package algorithms.search;

import java.util.*;

public class BestFirstSearch extends ASearchingAlgorithm {

    public BestFirstSearch() {
        name = "Best First Search";
    }

    @Override
    public Solution solve(ISearchable domain) {
        if (domain != null) {
            PriorityQueue<AState> priorityQueue = new PriorityQueue<>();
            HashMap<String, Boolean> visited = new HashMap<>();
            AState evaluationNode = domain.getStartState();
            AState destinationNode = domain.getGoalState();
            priorityQueue.add(evaluationNode);
            visited.put(evaluationNode.toString(), false);
            while (!priorityQueue.isEmpty()) {
                evaluationNode = priorityQueue.remove();
                numOfNodes++;
                if (evaluationNode.equals(destinationNode)) {
                    return new Solution(evaluationNode);
                } else {
                    for (AState state : domain.getAllPossibleStates(evaluationNode)) {
                        if (!visited.containsKey(state.toString())) {
                            state.setCost(evaluationNode.getCost() + state.getCost());
                            visited.put(state.toString(), false);
                            priorityQueue.add(state);
                        }
                    }
                    visited.put(evaluationNode.toString(), true);
                }
            }
            return new Solution(domain.getStartState());
        }
        return new Solution();
    }

}
