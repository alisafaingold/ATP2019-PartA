package algorithms.search;

import java.util.ArrayList;
import java.util.Stack;

public class Solution {
    private AState solution;

    public Solution(AState solution) {
        this.solution = solution;
    }

    public Solution() {
    }

    public ArrayList<AState> getSolutionPath() {
        Stack<AState> path = new Stack<>();
        ArrayList<AState> result = new ArrayList<>();
        AState current = solution;
        while (current != null) {
            path.push(current);
            current = current.getCameFrom();
        }
        for (int i = path.size() - 1; i >= 0; i--) {
            result.add(path.get(i));
        }
        return result;
    }
}
