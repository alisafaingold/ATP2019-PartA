package algorithms.mazeGenerators.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator{
    public long measureAlgorithmTimeMillis(int col, int row){
        long startTime= System.currentTimeMillis();
        Maze newMze = generate(col, row);
        long endTime=System.currentTimeMillis();
        return endTime-startTime;
    }

}
