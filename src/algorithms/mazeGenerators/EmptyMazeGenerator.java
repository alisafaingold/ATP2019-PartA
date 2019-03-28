package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator {
    @Override
    public Maze generate(int col, int row) {
        try {
            return new Maze(col,row);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
