package algorithms.search;

import algorithms.mazeGenerators.Maze;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable {
    private Maze myMaze;

    public SearchableMaze(Maze myMaze) {
        this.myMaze = myMaze;
    }

    @Override
    public ArrayList<AState> getAllPossibleStates(AState state) {
        return null;
    }

    @Override
    public AState getGoalState() {
        return null;
    }

    @Override
    public AState getStartState() {
        return null;
    }
}
