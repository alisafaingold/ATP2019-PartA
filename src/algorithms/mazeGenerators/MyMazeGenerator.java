package algorithms.mazeGenerators;

import java.util.*;


public class MyMazeGenerator extends AMazeGenerator {
    private Stack<Position> myStack = new Stack<>();
    Maze myMaze;

    @Override
    public Maze generate(int col, int row) {
        Maze newMaze = new Maze(row, col);
        int [][] isVisited = new int[row][col];
        // insert -1 to all cells, -1 represents a cell that wasn't visit yet
        for (int i = 0 ; i<row ; i++) {
            for (int j = 0; j < col; j++) {
                newMaze.setValueToCell(i, j, 1);
                isVisited[i][j]= -1 ;
            }
        }
        /*change to 1 start*/
        newMaze.setStartPosition(new Position(0,0));
        LinkedList<Position> NeighborsList= new LinkedList<>();
        Position N = newMaze.getStartPosition();
        NeighborsList.add(N);
        addToMaze( newMaze, N, isVisited);
        Position NeighboreA = null;
        while(!NeighborsList.isEmpty()){
            NeighboreA = getNeighbore(newMaze,N,isVisited);
            if(NeighboreA != null){
                brakeWall(newMaze , N ,NeighboreA , isVisited);
                N=NeighboreA;
                NeighborsList.add(N);
                addToMaze(newMaze, N, isVisited); // mark as visited and inside the maze
            }
            else{
                NeighborsList.remove(N);
                if( !NeighborsList.isEmpty())
                    N=NeighborsList.getFirst();
            }
        }
        return newMaze;
    }
    private void addToMaze(Maze newMaze, Position n, int[][] isVisited) {
        MarkVisit(isVisited, n);
        newMaze.setValueToCell(n.getRowIndex(), n.getColumnIndex(), 0);
    }

    private void MarkVisit(int[][] isVisited, Position neighboreA) {
        isVisited[neighboreA.getRowIndex()][neighboreA.getColumnIndex()]=0;
    }

    private Position getNeighbore(Maze newMaze, Position currentPosition, int[][] isVisited) {
        int row = currentPosition.getRowIndex();
        int column = currentPosition.getColumnIndex();
        LinkedList<Position> neighbersList = new LinkedList<>();

        if(row > 1) {//up
            if ( isVisited[row-2][column]== -1 ){
                neighbersList.add(new Position(row - 2, column));
            }
        }
        if(row < newMaze.row-2 ) {//down
            if( isVisited[row+2][column]==-1){
                neighbersList.add(new Position(row+2, column));
            }

        }
        if(column>1  ) {//left
            if(isVisited[row][column-2]==-1){
                neighbersList.add(new Position(row, column-2));

            }
        }
        if(column< newMaze.columnס-2 ) {//right
            if (isVisited[row][column + 2] == -1) {
                neighbersList.add(new Position(row, column + 2));
            }
        }
        if( neighbersList.isEmpty()) {
            return null;
        }
        Collections.shuffle(neighbersList, new Random());

        Position chosenA = neighbersList.getLast();
        return chosenA;
    }

    private void brakeWall(Maze newMaze, Position N , Position neighber , int[][]isVisited) {
        int N_rows = N.getRowIndex();
        int N_column = N.getColumnIndex();
        int neighbor_rows = neighber.getRowIndex();
        int neighbor_col = neighber.getColumnIndex();

        //braking wall between N and neighbor
        if( neighbor_rows == N_rows-2){
            newMaze.setValueToCell(N_rows-1, N_column, 0);
            isVisited[N_rows-1][N_column] =0;
        }
        if( neighbor_rows == N_rows+2){
            newMaze.setValueToCell(N_rows+1, N_column, 0);
            isVisited[N_rows+1][N_column]=0;
        }
        if( neighbor_col == N_column-2){
            newMaze.setValueToCell(N_rows, N_column-1, 0);
            isVisited[N_rows][N_column-1] = 0;

        }
        if( neighbor_col == N_column+2){
            newMaze.setValueToCell(N_rows, N_column+1, 0);
            isVisited[N_rows][N_column+1]=0;
        }
    }


    private ArrayList<Position> findMyNeighbors(Position pos) {
        ArrayList<Position> neighbors = new ArrayList<>();
        if (myMaze.isSafe(pos.getRowIndex() - 1, pos.getColumnIndex())) {
            neighbors.add(new Position(pos.getRowIndex() - 1, pos.getColumnIndex()));
        }
        if (myMaze.isSafe(pos.getRowIndex(), pos.getColumnIndex() - 1)) {
            neighbors.add(new Position(pos.getRowIndex(), pos.getColumnIndex() - 1));
        }
        if (myMaze.isSafe(pos.getRowIndex() + 1, pos.getColumnIndex())) {
            neighbors.add(new Position(pos.getRowIndex() + 1, pos.getColumnIndex()));
        }
        if (myMaze.isSafe(pos.getRowIndex(), pos.getColumnIndex() + 1)) {
            neighbors.add(new Position(pos.getRowIndex(), pos.getColumnIndex() + 1));
        }
        return neighbors;
    }


}


