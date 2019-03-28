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
            simpleMaze.setValueToCell(curRow,curCol,0);
            while(curRow<row && curCol< col){
                double randDirection = Math.random();
                if(randDirection < 0.33){
                    curRow--;
                    simpleMaze.setValueToCell(curRow,curCol,0);
                }
                else if(randDirection < 0.66){
                    curRow++;
                    simpleMaze.setValueToCell(curRow,curCol,0);
                }
                else{
                    curCol++;
                    simpleMaze.setValueToCell(curRow,curCol,0);
                }
            }
            return simpleMaze;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
