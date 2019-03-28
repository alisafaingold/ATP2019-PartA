package algorithms.mazeGenerators;
import java.util.Arrays;

public class Maze {
    private int row;
    private int column;
    private int[][] myMaze;

    public Maze(int row, int column) throws Exception {
        if(row<=0 || column<=0){
            throw new Exception();
        }
        this.row = row;
        this.column = column;
        myMaze = new int[row][column];
        for(int i =0; i<row; i++){
            for (int j=0; j<column; j++){
                myMaze[i][j]=1;
            }
        }
    }

    public void setValueToCell(int row, int col, int val){
        myMaze[row][col]=val;
    }

    public Position getStartPosition(){
        return null;
    }

    public Position getGoalPosition(){
        return null;
    }

    public void print(){
        Arrays.stream(myMaze)
                .forEach(
                        (row) -> {
                            System.out.print("[");
                            Arrays.stream(row)
                                    .forEach((el) -> System.out.print(" " + el + " "));
                            System.out.println("]");
                        }
                );
    }
}
