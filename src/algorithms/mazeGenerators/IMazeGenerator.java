package algorithms.mazeGenerators;

/**
 *Interface for maze generator which has two methods
 */
public interface IMazeGenerator {
    Maze generate(int row, int col);
    long measureAlgorithmTimeMillis(int row, int col);
}
