package algorithms.mazeGenerators;

public class SimpleMazeGenerator extends AMazeGenerator {
    @Override
    public Maze generate(int col, int row) {
        try {
            Maze sMaze = new Maze(row,col);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
