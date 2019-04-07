package algorithms.mazeGenerators;

import java.util.*;

/**
 * This class creates a random simple maze base on depth-first search algorithm
 */
public class SimpleMazeGenerator extends AMazeGenerator {

    /**
     * @param row
     * @param col
     * @return a random simple maze in size rowXcol
     */
    @Override
    public Maze generate(int row, int col) {
        //Create all necessary objects
        Stack<Position> myStack = new Stack<>();
        Maze myMaze = new Maze(row, col);
        row = myMaze.getRow();
        col = myMaze.getColumn();
        //Set the starting point as a cell {0,0}
        Position start = new Position(0, 0);
        myStack.push(start);
        myMaze.setStartPosition(start);
        Position next, last = new Position(0, 0);
        //helps to set the end point - the first time you reach the cell on the lower or right side
        boolean firstTimeBack = false;
        while (!myStack.empty()) {
            next = myStack.pop();
            //if the current cell have a cell that you can move through
            if (validNextPosition(next,myMaze)) {
                //set him as open cell, with value '0'
                myMaze.setValueToCell(next.getRowIndex(), next.getColumnIndex(), 0);
                ArrayList<Position> myNeighbors = findMyNeighbors(next,myMaze);
                //add all the cell's neighbors in random order
                Collections.shuffle(myNeighbors);
                myStack.addAll(myNeighbors);
            }
            //if it is the first time that the we reach to cell on the lower or right side
            //set him as the last
            if ((next.getColumnIndex() == col - 1 || next.getRowIndex() == row - 1) && !firstTimeBack) {
                last = next;
                firstTimeBack = true;
            }
        }
        // set the last as a goal position
        myMaze.setGoalPosition(last);
        return myMaze;
    }

    /**
     * @param pos
     * @param myMaze
     * @return whether it is possible to advance through the current cell, meaning that it is not a wall,
     * and that it has at most one neighbor that can be advanced
     */
    private boolean validNextPosition(Position pos, Maze myMaze) {
        int numNeighboringOnes = 0;
        ArrayList<Position> myNeighbors = findMyNeighbors(pos, myMaze);
        //for each neighbor
        for (Position neighbor : myNeighbors) {
            if (myMaze.getCellValue(neighbor.getRowIndex(), neighbor.getColumnIndex()) != 1) {
                numNeighboringOnes++;
            }
        }
        return (numNeighboringOnes < 2) && myMaze.getCellValue(pos.getRowIndex(), pos.getColumnIndex()) != 0;
    }

    /**
     * @param pos
     * @param myMaze
     * @return list with all cells that could potentially be valid neighbors
     * not checking if they are a wall but only if they are in the index range
     */
    private ArrayList<Position> findMyNeighbors(Position pos, Maze myMaze) {
        ArrayList<Position> neighbors = new ArrayList<>();
        //cell {row-1, col}
        if (myMaze.isSafe(pos.getRowIndex() - 1, pos.getColumnIndex())) {
            neighbors.add(new Position(pos.getRowIndex() - 1, pos.getColumnIndex()));
        }
        //cell {row, col-1}
        if (myMaze.isSafe(pos.getRowIndex(), pos.getColumnIndex() - 1)) {
            neighbors.add(new Position(pos.getRowIndex(), pos.getColumnIndex() - 1));
        }
        //cell {row+1, col}
        if (myMaze.isSafe(pos.getRowIndex() + 1, pos.getColumnIndex())) {
            neighbors.add(new Position(pos.getRowIndex() + 1, pos.getColumnIndex()));
        }
        //cell {row, col+1}
        if (myMaze.isSafe(pos.getRowIndex(), pos.getColumnIndex() + 1)) {
            neighbors.add(new Position(pos.getRowIndex(), pos.getColumnIndex() + 1));
        }
        return neighbors;
    }

}


