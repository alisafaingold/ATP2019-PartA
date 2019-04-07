package algorithms.mazeGenerators;

/**
 * This class represents a position in the maze that include row and column
 */
public class Position {
    private int row;
    private int col;
    //boolean open = true;

    /**
     * constructor
     * @param row
     * @param col
     */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * @return the position's row index
     */
    public int getRowIndex() {
        return row;
    }

    /**
     * @return the position's column index
     */
    public int getColumnIndex() {
        return col;
    }

    /**
     * @return represent the position as a string
     */
    @Override
    public String toString() {
        return "{"+row+","+col+"}";
    }
}
