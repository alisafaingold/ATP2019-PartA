package algorithms.mazeGenerators.mazeGenerators;

public class SimpleMazeGenerator extends AMazeGenerator {
    @Override
    public Maze generate(int col, int row) {
        try {
            Maze simpleMaze = new Maze(row,col);
            Position startPos = new Position(0,0);
            Position endPos = new Position(row-1,col-1);
            int curRow = 0;
            int curCol = 0;
            simpleMaze.setStartPosition(startPos);
            simpleMaze.setGoalPosition(endPos);

            double randDirection;
            while(curCol!=endPos.getColumnIndex() && curRow !=endPos.getRowIndex()){
                randDirection = Math.random();
                if(randDirection<0.5 && curRow != (row-1)){
                    curRow++;
                    simpleMaze.setValueToCell(curRow,curCol,0);

                }
                if(randDirection>0.5 && curCol != (col-1)) {
                    curCol++;
                    simpleMaze.setValueToCell(curRow, curCol, 0);
                }
            }
            for(int i=0; i<row; i++){
                for(int j=0; j<col; j++){
                    randDirection = Math.random();
                    if(randDirection<0.5 && simpleMaze.getCellValue(i,j)!=0){
                        simpleMaze.setValueToCell(i,j,0);
                    }
                }
            }
            return simpleMaze;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
