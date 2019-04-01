package algorithms.mazeGenerators;

public class Maze {
    private int row;
    private int column;
    private int[][] myMaze;
    private Position startPosition;
    private Position GoalPosition;

    public void setStartPosition(Position startPosition) {
        this.startPosition = startPosition;
    }

    public void setGoalPosition(Position goalPosition) {
        GoalPosition = goalPosition;
    }

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

    public int getCellValue(int row, int col){
        if(!isSafe(row,col)){
            return -1;
        }
        return myMaze[row][col];
    }

    public Position getStartPosition(){
        return startPosition;
    }
    public Position getGoalPosition(){
        return GoalPosition;
    }
    boolean isSafe(int x, int y)
    {
        return (x >= 0 && x < row && y >= 0 && y < column );
    }

    public void print(){
        for(int i=0; i<row; i++){
            for(int j=0; j<column; j++){
                if(i==GoalPosition.getRowIndex() && j==GoalPosition.getColumnIndex()){
                    System.out.print(" E ");
                }
                else if(i==startPosition.getRowIndex() && j==startPosition.getColumnIndex()){
                    System.out.print(" S ");
                }
                else{
                    System.out.print(" " + myMaze[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
