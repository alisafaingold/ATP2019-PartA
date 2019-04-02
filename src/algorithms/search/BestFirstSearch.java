package algorithms.search;

import java.util.*;

public class BestFirstSearch extends ASearchingAlgorithm{
    private PriorityQueue<AState> priorityQueue;
    HashMap<String, Boolean> visited;

    public BestFirstSearch() {
        name="Best First Search";
    }


    @Override
    public Solution solve(ISearchable domain) {
        priorityQueue = new PriorityQueue<>();
        visited = new HashMap<>();
        AState evaluationNode = domain.getStartState();
        AState destinationNode = domain.getGoalState();
        priorityQueue.add(evaluationNode);
        visited.put(evaluationNode.toString(),false);
        while (!priorityQueue.isEmpty()){
            evaluationNode = priorityQueue.remove();
            numOfNodes++;
            if(evaluationNode.equals(destinationNode)){
                return new Solution(evaluationNode);
            }
            else{
                ArrayList<AState> possible= domain.getAllPossibleStates(evaluationNode);
                Iterator<AState> itr = possible.iterator();
                while (itr.hasNext()){
                    AState v = itr.next();
                    if(!visited.containsKey(v.toString())){
                        v.setCost(evaluationNode.getCost() + v.getCost());
                        visited.put(v.toString(),false);
                        priorityQueue.add(v);
                    }
                }
                visited.put(evaluationNode.toString(),true);
            }
        }
        return new Solution(domain.getStartState());
    }
}
