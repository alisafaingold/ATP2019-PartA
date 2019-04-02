package algorithms.mazeGenerators;
import java.util.*;


public class MyMazeGenerator extends AMazeGenerator{
    private Stack<Position> myStack = new Stack<>();
    Maze myMaze;

    @Override
    public Maze generate(int col, int row) {
        try {
            myMaze = new Maze(col, row);
            Position start =new Position(0,0);
            myStack.push(start);
            myMaze.setStartPosition(start);
            Position next,last = new Position(0,0);
            boolean firstTimeBack=false;
            while(!myStack.empty()){
                next= myStack.pop();
                if (validNextPosition(next)) {
                    myMaze.setValueToCell(next.getRowIndex(), next.getColumnIndex(), 0);
                    ArrayList<Position> myNeighbors = findMyNeighbors(next);
                    Collections.shuffle(myNeighbors);
                    myStack.addAll(myNeighbors);
                }
                    if((next.getColumnIndex()==col-1||next.getRowIndex()==row-1)&&!firstTimeBack){
                        last= next;
                        firstTimeBack=true;
                    }
            }

            myMaze.setGoalPosition(last);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return  myMaze;
    }

    private boolean validNextPosition(Position pos) {
        int numNeighboringOnes = 0;
        ArrayList<Position> myNeighbors = findMyNeighbors(pos);
        for (Position x:
                myNeighbors) {
            if (myMaze.getCellValue(x.getRowIndex(),x.getColumnIndex()) != 1) {
                numNeighboringOnes++;
            }
        }
        return (numNeighboringOnes < 2) &&myMaze.getCellValue(pos.getRowIndex(),pos.getColumnIndex()) != 0;
    }



    private ArrayList<Position> findMyNeighbors(Position pos) {
        ArrayList<Position> neighbors = new ArrayList<>();
        if (myMaze.isSafe(pos.getRowIndex() - 1, pos.getColumnIndex())) {
            neighbors.add(new Position(pos.getRowIndex() - 1, pos.getColumnIndex() ));
        }
        if (myMaze.isSafe(pos.getRowIndex(), pos.getColumnIndex() - 1)) {
            neighbors.add(new Position(pos.getRowIndex() , pos.getColumnIndex() - 1));
        }
        if (myMaze.isSafe(pos.getRowIndex() + 1, pos.getColumnIndex())) {
            neighbors.add(new Position(pos.getRowIndex() + 1, pos.getColumnIndex() ));
        }
        if (myMaze.isSafe(pos.getRowIndex(), pos.getColumnIndex() + 1)) {
            neighbors.add(new Position(pos.getRowIndex(), pos.getColumnIndex() + 1));
        }
        return neighbors;
    }



}


