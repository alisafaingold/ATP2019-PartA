package algorithms.search;

import java.util.ArrayList;

/**
 * Interface to implement for searchable problem
 */
public interface ISearchable {
    /**
     * @param state
     * @return All the possible states that are possible from the current state
     */
    ArrayList<AState> getAllPossibleStates(AState state);

    /**
     * @return the goal state of the problem
     */
    AState getGoalState();

    /**
     * @return the start state of the problem
     */
    AState getStartState();
}
