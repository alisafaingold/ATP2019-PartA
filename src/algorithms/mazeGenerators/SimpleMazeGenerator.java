package algorithms.mazeGenerators;

public class SimpleMazeGenerator extends AMazeGenerator {
    @Override
    public Maze generate(int col, int row) {
        try {
            Maze simpleMaze = new Maze(row,col);
            int randRow = (int)Math.random()*row;
            Position startPos = new Position(randRow,0);
            int curRow = randRow;
            int curCol = 0;
            simpleMaze.setStartPosition(new Position(curRow,curCol));
            int colGoal=curCol;
            int rowGoal=curRow;

            while(curRow<row && curCol< col && curCol>=0 && curRow>=0){
                double randDirection = Math.random();
                if(randDirection < 0.33){
                    colGoal=curCol;
                    rowGoal=curRow;
                    simpleMaze.setValueToCell(curRow,curCol,0);
                    curRow--;
                }
                else if(randDirection < 0.66){
                    colGoal=curCol;
                    rowGoal=curRow;
                    simpleMaze.setValueToCell(curRow,curCol,0);
                    curRow++;
                }
                else{
                    colGoal=curCol;
                    rowGoal=curRow;
                    simpleMaze.setValueToCell(curRow,curCol,0);
                    curCol++;
                }
            }
            simpleMaze.setGoalPosition(new Position(curRow,curCol));
            return simpleMaze;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
