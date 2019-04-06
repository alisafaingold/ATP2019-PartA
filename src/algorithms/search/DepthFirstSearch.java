package algorithms.search;

import java.util.*;

public class DepthFirstSearch extends ASearchingAlgorithm{

    public DepthFirstSearch() {
        name = "DFS";
    }

    @Override
    public Solution solve(ISearchable domain) {

        Stack<AState> stack = new Stack<>();
        HashMap<String,Iterator> iterators = new HashMap<>();
        AState s = domain.getStartState();
        AState e = domain.getGoalState();
        ArrayList<AState> neighbors = domain.getAllPossibleStates(s);
        Iterator<AState> iter = neighbors.iterator();
        iterators.put(s.toString(),iter);
        stack.push(s);
        while(!stack.empty()) {
            AState curr = stack.peek();
            numOfNodes++;
            if(curr.equals(e)){
                return new Solution(curr);
            }
            if(iterators.get(curr.toString()).hasNext()){
                AState x = (AState)iterators.get(curr.toString()).next();
                if(!iterators.containsKey(x.toString())){
                    neighbors = domain.getAllPossibleStates(x);
                    iter = neighbors.iterator();
                    iterators.put(x.toString(),iter);
                    stack.push(x);
                }
            }
            else{
                stack.pop();
            }
        }
        return new Solution(s);
    }
}
