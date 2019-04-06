package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator {
    @Override
    public Maze generate(int col, int row) {
        Maze newMaze = new Maze(col, row);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                newMaze.setValueToCell(i, j, 0);
            }
        }
        return newMaze;
    }

}
