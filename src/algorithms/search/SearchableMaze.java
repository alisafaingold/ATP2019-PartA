package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * This class implements the searchable problem for the maze problem
 */
public class SearchableMaze implements ISearchable {
    private Maze myMaze;

    /**
     * This is the constructor of the SearchableMaze
     *
     * @param myMaze the maze which we want to transform into SearchableMaze
     */
    public SearchableMaze(Maze myMaze) {
        this.myMaze = myMaze;
    }

    /**
     * Find all the possible positions to move from the current one on the maze
     *
     * @param state
     * @return List of all the possible states
     */
    @Override
    public ArrayList<AState> getAllPossibleStates(AState state) {
        ArrayList<AState> possibleStates = new ArrayList<>();
        if (state != null && myMaze!= null) {
            MazeState currentState = (MazeState) state;
            Set<MazeState> allPossible = new HashSet<>();
            int row = currentState.getRow(), col = currentState.getColumn();
            allPossible.addAll(validInRow(row - 1, col, currentState));
            allPossible.addAll(validInRow(row + 1, col, currentState));
            allPossible.addAll(validInColumn(row, col - 1, currentState));
            allPossible.addAll(validInColumn(row, col + 1, currentState));
            possibleStates.addAll(allPossible);
        }
        return possibleStates;
    }

    /**
     * @return the goal state of the maze if it was set
     */
    @Override
    public AState getGoalState() {
        if (myMaze != null)
            return new MazeState(myMaze.getGoalPosition());
        return new MazeState(null);
    }

    /**
     * @return the start state of the maze if it was set
     */
    @Override
    public AState getStartState() {
        if (myMaze != null)
            return new MazeState(myMaze.getStartPosition());
        return new MazeState(null);
    }

    /**
     * Finds all the valid neighbors in the current row within 1 move
     *
     * @param rowOrigin
     * @param colOrigin
     * @param state
     * @return
     */
    private ArrayList<MazeState> validInRow(int rowOrigin, int colOrigin, MazeState state) {
        ArrayList<MazeState> corners = new ArrayList<>();
        if (myMaze.isPassable(rowOrigin, colOrigin)) {
            corners.add(addMazeState(rowOrigin, colOrigin, state, 10));
            if (myMaze.isPassable(rowOrigin, colOrigin - 1))
                corners.add(addMazeState(rowOrigin, colOrigin - 1, state, 15));
            if (myMaze.isPassable(rowOrigin, colOrigin + 1))
                corners.add(addMazeState(rowOrigin, colOrigin + 1, state, 15));
        }
        return corners;
    }

    /**
     * Finds all the valid neighbors in the current column within 1 move
     *
     * @param rowOrigin
     * @param colOrigin
     * @param state
     * @return
     */
    private Set<MazeState> validInColumn(int rowOrigin, int colOrigin, MazeState state) {
        Set<MazeState> corners = new HashSet<>();
        if (myMaze.isPassable(rowOrigin, colOrigin)) {
            corners.add(addMazeState(rowOrigin, colOrigin, state, 10));
            if (myMaze.isPassable(rowOrigin - 1, colOrigin))
                corners.add(addMazeState(rowOrigin - 1, colOrigin, state, 15));
            if (myMaze.isPassable(rowOrigin + 1, colOrigin))
                corners.add(addMazeState(rowOrigin + 1, colOrigin, state, 15));
        }
        return corners;
    }

    /**
     * @param row
     * @param col
     * @param cameFrom
     * @param cost
     * @return new maze state with all the given parametrs
     */
    private MazeState addMazeState(int row, int col, MazeState cameFrom, int cost) {
        MazeState mazeState = new MazeState(new Position(row, col));
        mazeState.setCost(cost);
        mazeState.setCameFrom(cameFrom);
        return mazeState;
    }

}
