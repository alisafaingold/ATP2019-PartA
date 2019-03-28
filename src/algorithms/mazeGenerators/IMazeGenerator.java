package algorithms.mazeGenerators;

public interface IMazeGenerator {

    Maze generate(int col, int row);
    long measureAlgorithmTimeMillis(int col, int row);
}
