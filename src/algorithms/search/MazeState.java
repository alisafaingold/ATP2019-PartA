package algorithms.search;

import algorithms.mazeGenerators.Position;

/**
 * This class extends the Astate and is to use with the maze problem
 */
public class MazeState extends AState {
    private Position position;

    /**
     * This is the constructor of this class
     *
     * @param position which is the position of this state
     */
    public MazeState(Position position) {
        super(position != null ? position.toString() : "");
        if (position != null) {
            this.position = position;
        }
    }

    /**
     * @return the row of the current state
     */
    public int getRow() {
        if (this.position != null)
            return position.getRowIndex();
        return -1;
    }

    /**
     * @return the column of the current state
     */
    public int getColumn() {
        if (this.position != null)
            return position.getColumnIndex();
        return -1;
    }

}
