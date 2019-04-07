package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState {
    private Position position;

    public MazeState(Position position) {
        super(position != null ? position.toString() : "");
        if (position != null) {
            this.position = position;
        }
    }

    public int getRow() {
        if(this.position!=null)
            return position.getRowIndex();
        return -1;
    }

    public int getColumn() {
        if(this.position!=null)
            return position.getColumnIndex();
        return -1;
    }

}
