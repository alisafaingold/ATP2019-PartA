package algorithms.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm{

    public DepthFirstSearch() {
        name = "DFS";
    }

    @Override
    public Solution solve(ISearchable domain) {

        Stack<AState> stack = new Stack<>();
        HashMap<String, Boolean> visited = new HashMap<>();
        AState s = domain.getStartState();
        AState e = domain.getGoalState();
        stack.push(s);
        visited.put(s.toString(),false);
        while(!stack.empty())
        {
            s = stack.peek();
            this.numOfNodes++;
            stack.pop();
            if(e.equals(s)){
                return new Solution(s);
            }
            if(visited.get(s.toString())==false)
            {
                if(s.getCameFrom()!=null){
                    visited.put(s.getCameFrom().toString(),true);
                }
                visited.put(s.toString(),true);
            }
            else
            {
                continue;
            }
            ArrayList<AState> possible= domain.getAllPossibleStates(s);
            Iterator<AState> itr = possible.iterator();
            while (itr.hasNext())
            {
                AState v = itr.next();
                if(visited.containsKey(v.toString())) {
                    if(!visited.get(v.toString())){
                        stack.push(v);
                    }
                }
                else{
                    visited.put(v.toString(),false);
                    stack.push(v);
                }
            }
        }
        return null;
    }
}
