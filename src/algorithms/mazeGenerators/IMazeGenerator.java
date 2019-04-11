package algorithms.mazeGenerators;

/**
 * Interface for maze generator which has two methods
 */
public interface IMazeGenerator {
    /**
     * @param row -  how many rows in the maze
     * @param col -  how many columns in the maze
     * @return
     */
    Maze generate(int row, int col);

    /**
     * @param row
     * @param col
     * @return The time it took to generate the maze
     */
    long measureAlgorithmTimeMillis(int row, int col);
}
