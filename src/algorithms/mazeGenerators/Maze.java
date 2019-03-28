package algorithms.mazeGenerators;

public class Maze {
    private int row;
    private int column;
    private int[][] myMaze;

    public Maze(int row, int column) throws Exception {
        if(row<=0 || column<=0){
            throw new Exception();
        }
        int moshe;
        this.row = row;
        this.column = column;
        myMaze = new int[row][column];
        for(int i =0; i<row; i++){
            for (int j=0; j<column; j++){
                myMaze[i][j]=0;
            }
        }
    }
}
