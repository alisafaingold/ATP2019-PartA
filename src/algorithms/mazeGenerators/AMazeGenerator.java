package algorithms.mazeGenerators;

/**
 * An abstract class of maze generator which implements IMazeGenerator interface
 */
public abstract class AMazeGenerator implements IMazeGenerator {
    /**
     * @param row
     * @param col
     * @return The time it took to generate the maze
     */
    public long measureAlgorithmTimeMillis(int row, int col) {
        long startTime = System.currentTimeMillis();
        generate(row, col);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

}
