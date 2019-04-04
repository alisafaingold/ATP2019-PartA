package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator{
    public long measureAlgorithmTimeMillis(int row, int col) {
        long startTime = System.currentTimeMillis();
        generate(row, col);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

}
