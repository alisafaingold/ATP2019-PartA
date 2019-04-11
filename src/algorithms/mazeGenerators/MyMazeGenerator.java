package algorithms.mazeGenerators;

import java.util.*;

/**
 * this class creates a random maze base on
 */
public class MyMazeGenerator extends AMazeGenerator {
    /**
     * @param row
     * @param col
     * @return a random maze in size rowXcol
     */
    @Override
    public Maze generate(int row, int col) {
        //Create all necessary objects
        Maze myMaze = new Maze(row, col);
        ArrayList<Position> myList = new ArrayList<>();
        HashSet<String> isVisited = new HashSet<>(); //saves the cell that have been opened
        Position start = new Position(0, 0); // init the start position
        Position next = new Position(0, 0), curr = new Position(0, 0); //will save
        //init the start position as '0' and insert him to the visit list
        myList.add(start);
        isVisited.add(start.toString());
        myMaze.setStartPosition(start);
        myMaze.setValueToCell(next.getRowIndex(), next.getColumnIndex(), 0);
        while (!myList.isEmpty()) {
            //get random neighbor cell
            next = findMyNeighbors(curr, isVisited, myMaze);
            if (next != null) {
                //set '0' to the next cell
                myMaze.setValueToCell(next.getRowIndex(), next.getColumnIndex(), 0);
                //break the wall between the curr and the next
                breakWall(curr, next, myMaze, isVisited);
                curr = next;
                //indicate cell as visited for generation
                myList.add(curr);
                isVisited.add(curr.toString());
                //in each round we define the last cell marked '0' as the goal cell
                myMaze.setGoalPosition(curr);
            } else { // if there are no valid neighbors
                myList.remove(curr); // pop out the curr cell
                if (!myList.isEmpty()) { // set as the curr cell the next one in the list
                    curr = myList.get(0);
                }
            }
        }
        return myMaze;
    }

    /**
     * @param pos
     * @param isVisited
     * @param myMaze
     * @return a neighbors who is drawn from a list of neighbors that can be advanced through them,
     * meaning that they are not a wall and they have not yet been visited
     */
    private Position findMyNeighbors(Position pos, HashSet<String> isVisited, Maze myMaze) {
        ArrayList<Position> neighbors = new ArrayList<>();
        int row = pos.getRowIndex(), col = pos.getColumnIndex();
        //check neighbor {row-2, col}
        if (isValidNeighbor(row - 2, col, myMaze, isVisited))
            neighbors.add(new Position(row - 2, col));
        //check neighbor {row, col-2}
        if (isValidNeighbor(row, col - 2, myMaze, isVisited))
            neighbors.add(new Position(row, col - 2));
        //check neighbor {row+2, col}
        if (isValidNeighbor(row + 2, col, myMaze, isVisited))
            neighbors.add(new Position(row + 2, col));
        //check neighbor {row, col+2}
        if (isValidNeighbor(row, col + 2, myMaze, isVisited))
            neighbors.add(new Position(row, col + 2));
        //if there are no valid neighbors
        if (neighbors.isEmpty())
            return null;
        //neighbor draw
        Collections.shuffle(neighbors);
        return neighbors.get(0);
    }

    /**
     * Breaking the wall between the two cells, checking which wall should be broken
     *
     * @param curr
     * @param next
     * @param myMaze
     * @param isVisited
     */
    private void breakWall(Position curr, Position next, Maze myMaze, HashSet<String> isVisited) {
        int currRow = curr.getRowIndex(), currCol = curr.getColumnIndex();
        int rowDiff = currRow - next.getRowIndex();
        int colDiff = currCol - next.getColumnIndex();
        // break {row-1,col}
        if (rowDiff > 0)
            setAndMarkAsVisited(currRow - 1, currCol, myMaze, isVisited);
        //break {row+1,col}
        if (rowDiff < 0)
            setAndMarkAsVisited(currRow + 1, currCol, myMaze, isVisited);
        //break {row,col-1}
        if (colDiff > 0)
            setAndMarkAsVisited(currRow, currCol - 1, myMaze, isVisited);
        //break {row,col+1}
        if (colDiff < 0)
            setAndMarkAsVisited(currRow, currCol + 1, myMaze, isVisited);
    }

    /**
     * Adds the cell to the list of cells that have already been visited and puts the value '0'
     *
     * @param row
     * @param col
     * @param myMaze
     * @param isVisited
     */
    private void setAndMarkAsVisited(int row, int col, Maze myMaze, HashSet<String> isVisited) {
        myMaze.setValueToCell(row, col, 0);
        isVisited.add(new Position(row, col).toString());
    }

    /**
     * @param row
     * @param col
     * @param myMaze
     * @param isVisited
     * @return If you can move through the cell - if it is not a wall and if it has not been visited before
     */
    private boolean isValidNeighbor(int row, int col, Maze myMaze, HashSet<String> isVisited) {
        return myMaze.isSafe(row, col) && !isVisited.contains((new Position(row, col)).toString());
    }

}


