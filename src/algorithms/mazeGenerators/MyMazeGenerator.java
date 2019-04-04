package algorithms.mazeGenerators;

import java.util.*;


public class MyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int row, int col) {
        Maze myMaze = new Maze(row, col);
        ArrayList<Position> myList = new ArrayList<>();
        HashSet<String> isVisited = new HashSet<>();
        Position start = new Position(0, 0);
        Position next = new Position(0, 0), curr = new Position(0, 0);
        myList.add(start);
        isVisited.add(start.toString());
        myMaze.setStartPosition(start);
        myMaze.setValueToCell(next.getRowIndex(), next.getColumnIndex(), 0);
        while (!myList.isEmpty()) {
            next = findMyNeighbors(curr, isVisited, myMaze);
            if (next != null) {
                myMaze.setValueToCell(next.getRowIndex(), next.getColumnIndex(), 0);
                breakWall(curr, next, myMaze, isVisited);
                curr = next;
                myList.add(curr);
                isVisited.add(curr.toString());
                myMaze.setGoalPosition(curr);
            } else {
                myList.remove(curr);
                if (!myList.isEmpty()) {
                    curr = myList.get(0);
                }
            }
        }
        return myMaze;
    }


    private Position findMyNeighbors(Position pos, HashSet<String> isVisited, Maze myMaze) {
        ArrayList<Position> neighbors = new ArrayList<>();
        int row = pos.getRowIndex(), col = pos.getColumnIndex();
        if (isValidNeighbor(row - 2, col, myMaze, isVisited))
            neighbors.add(new Position(row - 2, col));
        if (isValidNeighbor(row, col - 2, myMaze, isVisited))
            neighbors.add(new Position(row, col - 2));
        if (isValidNeighbor(row + 2, col, myMaze, isVisited))
            neighbors.add(new Position(row + 2, col));
        if (isValidNeighbor(row, col + 2, myMaze, isVisited))
            neighbors.add(new Position(row, col + 2));
        if (neighbors.isEmpty())
            return null;
        Collections.shuffle(neighbors);
        return neighbors.get(0);
    }

    private void breakWall(Position curr, Position next, Maze myMaze, HashSet<String> isVisited) {
        int currRow = curr.getRowIndex(), currCol = curr.getColumnIndex();
        int rowDiff = currRow - next.getRowIndex();
        int colDiff = currCol - next.getColumnIndex();
        if (rowDiff > 0)
            setAndMarkAsVisited(currRow - 1, currCol, myMaze, isVisited);
        if (rowDiff < 0)
            setAndMarkAsVisited(currRow + 1, currCol, myMaze, isVisited);
        if (colDiff > 0)
            setAndMarkAsVisited(currRow, currCol - 1, myMaze, isVisited);
        if (colDiff < 0)
            setAndMarkAsVisited(currRow, currCol + 1, myMaze, isVisited);
    }

    private void setAndMarkAsVisited(int row, int col, Maze myMaze, HashSet<String> isVisited) {
        myMaze.setValueToCell(row, col, 0);
        isVisited.add(new Position(row, col).toString());
    }

    private boolean isValidNeighbor(int row, int col, Maze myMaze, HashSet<String> isVisited) {
        return myMaze.isSafe(row, col) && !isVisited.contains((new Position(row, col)).toString());
    }

}


