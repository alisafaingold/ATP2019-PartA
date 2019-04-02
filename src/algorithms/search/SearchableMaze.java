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
        allPossible.addAll(validInRow(state1.getRow()-1,state1.getColumn(),state1));
        allPossible.addAll(validInRow(state1.getRow()+1,state1.getColumn(),state1));
        allPossible.addAll(validInColumn(state1.getRow(),state1.getColumn()-1,state1));
        allPossible.addAll(validInColumn(state1.getRow(),state1.getColumn()+1,state1));
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

    private ArrayList<MazeState> validInRow(int rowOrigin, int colOrigin, MazeState s){
        ArrayList<MazeState> corners= new ArrayList<>();
        if (myMaze.isPassable(rowOrigin, colOrigin)){
            MazeState curr = new MazeState("{"+rowOrigin+","+colOrigin+"}",2);
            curr.setCameFrom(s);
            corners.add(curr);
            if(myMaze.isPassable(rowOrigin, colOrigin-1)){
                MazeState corner1 = new MazeState("{"+rowOrigin+","+(colOrigin-1)+"}",1);
                corner1.setCameFrom(s);
                corners.add(corner1);
            }
            if(myMaze.isPassable(rowOrigin, colOrigin+1)){
                MazeState corner2 = new MazeState("{"+rowOrigin+","+(colOrigin+1)+"}",1);
                corner2.setCameFrom(s);
                corners.add(corner2);
            }
        }
        return corners;
    }

    private Set<MazeState> validInColumn(int rowOrigin, int colOrigin, MazeState s){
        Set<MazeState> corners= new HashSet<>();
        if (myMaze.isPassable(rowOrigin, colOrigin)){
            MazeState curr = new MazeState("{"+rowOrigin+","+colOrigin+"}",2);
            curr.setCameFrom(s);
            corners.add(curr);
            if(myMaze.isPassable(rowOrigin-1, colOrigin)){
                MazeState corner1 = new MazeState("{"+(rowOrigin-1)+","+colOrigin+"}",1);
                corner1.setCameFrom(s);
                corners.add(corner1);
            }
            if(myMaze.isPassable(rowOrigin+1, colOrigin)){
                MazeState corner2 = new MazeState("{"+(rowOrigin+1)+","+colOrigin+"}",1);
                corner2.setCameFrom(s);
                corners.add(corner2);
            }
        }
        return corners;
    }

}
