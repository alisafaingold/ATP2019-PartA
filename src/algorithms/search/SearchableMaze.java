package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SearchableMaze implements ISearchable {
    private Maze myMaze;

    public SearchableMaze(Maze myMaze) {
        this.myMaze = myMaze;
    }

    @Override
    public ArrayList<AState> getAllPossibleStates(AState state) {
        ArrayList<AState> possibleStates = new ArrayList<>();
        if(state!=null) {
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

    @Override
    public AState getGoalState() {
        if(myMaze!=null)
            return new MazeState(myMaze.getGoalPosition());
        return new MazeState(null);
    }

    @Override
    public AState getStartState() {
        if(myMaze!=null)
            return new MazeState(myMaze.getStartPosition());
        return new MazeState(null);
    }

    private ArrayList<MazeState> validInRow(int rowOrigin, int colOrigin, MazeState s) {
        ArrayList<MazeState> corners = new ArrayList<>();
        if (myMaze.isPassable(rowOrigin, colOrigin)) {
            corners.add(addMazeState(rowOrigin, colOrigin, s, 10));
            if (myMaze.isPassable(rowOrigin, colOrigin - 1))
                corners.add(addMazeState(rowOrigin, colOrigin - 1, s, 15));
            if (myMaze.isPassable(rowOrigin, colOrigin + 1))
                corners.add(addMazeState(rowOrigin, colOrigin + 1, s, 15));
        }
        return corners;
    }

    private Set<MazeState> validInColumn(int rowOrigin, int colOrigin, MazeState s) {
        Set<MazeState> corners = new HashSet<>();
        if (myMaze.isPassable(rowOrigin, colOrigin)) {
            corners.add(addMazeState(rowOrigin, colOrigin, s, 10));
            if (myMaze.isPassable(rowOrigin - 1, colOrigin))
                corners.add(addMazeState(rowOrigin - 1, colOrigin, s, 15));
            if (myMaze.isPassable(rowOrigin + 1, colOrigin))
                corners.add(addMazeState(rowOrigin + 1, colOrigin, s, 15));
        }
        return corners;
    }

    private MazeState addMazeState(int row, int col, MazeState cameFrom, int cost) {
        MazeState mazeState = new MazeState(new Position(row, col));
        mazeState.setCost(cost);
        mazeState.setCameFrom(cameFrom);
        return mazeState;
    }

}
