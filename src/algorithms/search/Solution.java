package algorithms.search;

import java.util.ArrayList;
import java.util.Stack;

/**
 * This is class to represent the solution of the problem
 */
public class Solution {
    private AState solution;

    /**
     * This is the constructor of the class which gets state
     *
     * @param solution
     */
    public Solution(AState solution) {
        this.solution = solution;
    }

    /**
     * The default constructor of the class
     */
    public Solution() {
    }

    /**
     * @return List with all the states within the solution path
     */
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
