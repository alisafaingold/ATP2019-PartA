package algorithms.search;
import algorithms.mazeGenerators.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SearchableMaze implements ISearchable {
    private Maze myMaze;

    public SearchableMaze(Maze myMaze) {
        this.myMaze = myMaze;
    }

    @Override
    public ArrayList<AState> getAllPossibleStates(AState state) {
        MazeState state1 = (MazeState)state;
        ArrayList<AState> possibleStates = new ArrayList<>();
        Set<MazeState> allPossible= new HashSet<>();
        allPossible.addAll(validInRow(state1.getRow()-1,state1.getColumn()));
        allPossible.addAll(validInRow(state1.getRow()+1,state1.getColumn()));
        allPossible.addAll(validInColumn(state1.getRow(),state1.getColumn()-1));
        allPossible.addAll(validInColumn(state1.getRow(),state1.getColumn()+1));
        possibleStates.addAll(allPossible);
        return possibleStates;
    }

    @Override
    public AState getGoalState() {
        return new MazeState(myMaze.getGoalPosition().toString());
    }

    @Override
    public AState getStartState() {
        return new MazeState(myMaze.getStartPosition().toString());
    }

    private ArrayList<MazeState> validInRow(int rowOrigin, int colOrigin){
        ArrayList<MazeState> corners= new ArrayList<>();
        if (myMaze.isPassable(rowOrigin, colOrigin)){
            corners.add(new MazeState("{"+rowOrigin+","+colOrigin+"}"));
            if(myMaze.isPassable(rowOrigin, colOrigin-1)){
                corners.add(new MazeState("{"+rowOrigin+","+(colOrigin-1)+"}"));
            }
            if(myMaze.isPassable(rowOrigin, colOrigin+1)){
                corners.add(new MazeState("{"+rowOrigin+","+(colOrigin+1)+"}"));
            }
        }
        return corners;
    }

    private Set<MazeState> validInColumn(int rowOrigin, int colOrigin){
        Set<MazeState> corners= new HashSet<>();
        if (myMaze.isPassable(rowOrigin, colOrigin)){
            corners.add(new MazeState("{"+rowOrigin+","+colOrigin+"}"));
            if(myMaze.isPassable(rowOrigin-1, colOrigin)){
                corners.add(new MazeState("{"+(rowOrigin-1)+","+colOrigin+"}"));
            }
            if(myMaze.isPassable(rowOrigin+1, colOrigin)){
                corners.add(new MazeState("{"+(rowOrigin+1)+","+colOrigin+"}"));
            }
        }
        return corners;
    }

}
