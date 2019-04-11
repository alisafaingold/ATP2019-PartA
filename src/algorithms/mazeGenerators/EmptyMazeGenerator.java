package algorithms.mazeGenerators;

/**
 * EmptyMazeGenerator class which extends the AMazeGenerator
 */
public class EmptyMazeGenerator extends AMazeGenerator {
    /**
     * Generate an empty maze where all cell's value is '0'
     *
     * @param row - how many rows in the maze
     * @param col - how many columns in the maze
     * @return new Maze without any walls
     */
    @Override
    public Maze generate(int row, int col) {
        Maze newMaze = new Maze(row, col);
        row = newMaze.getRow();
        col = newMaze.getColumn();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                newMaze.setValueToCell(i, j, 0);
            }
        }
        newMaze.setStartPosition(new Position(0, 0));
        newMaze.setGoalPosition(new Position(row - 1, col - 1));
        return newMaze;
    }

}
