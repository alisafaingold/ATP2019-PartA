package algorithms.mazeGenerators;

/**
 * A class representing the maze
 */
public class Maze {
    private int[][] myMaze;
    private int row; //the row number of the cell(row 0 is the top)
    private int column; //the column number of the cell (Column 0 is the left)
    private Position startPosition; //the initial position
    private Position GoalPosition; //the target position

    /**
     * Create a maze containing only walls, the values of each cell are '1'
     * get as parameters the size of the maze you want to create
     * if the parameters are accepted smaller than 3, a default maze will be created at size 10X10
     *
     * @param row
     * @param column
     */
    public Maze(int row, int column) {
        if (row < 3 || column < 3) {
            this.row = 10;
            this.column = 10;
        } else {
            this.row = row;
            this.column = column;
        }
        myMaze = new int[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                myMaze[i][j] = 1;
            }
        }
        startPosition = new Position(0, 0);
        GoalPosition = new Position(0, 0);
    }

    public Maze(byte[] bytesArr) {
        if (bytesArr != null) {
            this.row = copyArrayDecode(bytesArr, 0, 3);
            this.column = copyArrayDecode(bytesArr, 3, 3);
            int rowIndex = copyArrayDecode(bytesArr, 6, 3);
            int colIndex = copyArrayDecode(bytesArr, 9, 3);
            this.startPosition = new Position(rowIndex, colIndex);
            rowIndex = copyArrayDecode(bytesArr, 12, 3);
            colIndex = copyArrayDecode(bytesArr, 15, 3);
            this.GoalPosition = new Position(rowIndex, colIndex);
            myMaze = new int[row][column];
            //Fill the maze with the values from the byte Array
            fillMazeWithValues(bytesArr);
        }
        else {
            this.row = 10;
            this.column = 10;
            myMaze = new int[row][column];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    myMaze[i][j] = 1;
                }
            }
            startPosition = new Position(0, 0);
            GoalPosition = new Position(0, 0);
        }
    }

    /**
     * Applying value to a particular cell in the array
     *
     * @param row
     * @param col
     * @param val
     */
    public void setValueToCell(int row, int col, int val) {
        if (isSafe(row, col))
            myMaze[row][col] = val;
    }

    /**
     * @param row
     * @param col
     * @return the cell value in the received indexes
     */
    public int getCellValue(int row, int col) {
        if (!isSafe(row, col)) {
            return -1;
        }
        return myMaze[row][col];
    }

    /**
     * @return the initial position
     */
    public Position getStartPosition() {
        return startPosition;
    }

    /**
     * @return the target position
     */
    public Position getGoalPosition() {
        return GoalPosition;
    }

    /**
     * set up an initial position
     *
     * @param startPosition
     */
    public void setStartPosition(Position startPosition) {
        if (startPosition != null && isSafe(startPosition.getRowIndex(), startPosition.getColumnIndex()))
            this.startPosition = startPosition;
    }

    /**
     * set a target position
     *
     * @param goalPosition
     */
    public void setGoalPosition(Position goalPosition) {
        if (goalPosition != null && isSafe(goalPosition.getRowIndex(), goalPosition.getColumnIndex()))
            GoalPosition = goalPosition;
    }

    /**
     * @return number of rows in the maze
     */
    public int getRow() {
        return row;
    }

    /**
     * @return number of column in the maze
     */
    public int getColumn() {
        return column;
    }

    /**
     * @param row
     * @param col
     * @return if the indexes given do not exceed the array
     */
    public boolean isSafe(int row, int col) {
        return (row >= 0 && row < this.row && col >= 0 && col < this.column);
    }

    /**
     * @param row
     * @param col
     * @return if the cell in the given indexes is safe and is not a wall
     */
    public boolean isPassable(int row, int col) {
        return isSafe(row, col) && myMaze[row][col] == 0;
    }

    /**
     * print function
     */
    public void print() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (i == GoalPosition.getRowIndex() && j == GoalPosition.getColumnIndex()) {
                    System.out.print(" E ");
                } else if (i == startPosition.getRowIndex() && j == startPosition.getColumnIndex()) {
                    System.out.print(" S ");
                } else {
                    System.out.print(" " + myMaze[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * simple print function
     */
    public void simplePrint() {
        for (int i = 0; i < myMaze.length; i++) {
            for (int j = 0; j < myMaze[i].length; j++) {
                if (i == startPosition.getRowIndex() && j == startPosition.getColumnIndex()) {//startPosition
                    System.out.print(" " + "\u001B[44m" + " ");
                } else if (i == GoalPosition.getRowIndex() && j == GoalPosition.getColumnIndex()) {//goalPosition
                    System.out.print(" " + "\u001B[44m" + " ");
                } else if (myMaze[i][j] == 1) System.out.print(" " + "\u001B[40m" + " ");
                else System.out.print(" " + "\u001B[107m" + " ");
            }
            System.out.println(" " + "\u001B[107m");
        }
    }

    private int decode(byte[] b, int startIndex, boolean returnIndex) {
        int i, n = 0;
        for (i = startIndex; i < b.length; i++) {
            int curByte = b[i] & 0xFF;
            n = (n << 7) | (curByte & 0x7F);
            if ((curByte & 0x80) == 0)
                break;
        }
        return returnIndex ? i : n;
    }

    private int copyArrayDecode(byte[] readFrom, int startR, int size) {
        int times = 0;
        byte[] writeTo = new byte[3];
        for (int startW = 0; startW < writeTo.length && startR < readFrom.length && times < size; startR++, startW++) {
            writeTo[startW] = readFrom[startR];
        }
        return decode(writeTo, 0, false);
    }

    private void fillMazeWithValues(byte[] bytesArr){
        int timesToWrite, valueToWrite = 0, written = 0, indexToStart = 18;
        timesToWrite = decode(bytesArr, indexToStart, false);
        indexToStart = decode(bytesArr, indexToStart, true);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (written < timesToWrite) {
                    myMaze[i][j] = valueToWrite;
                    written++;
                }
                else {
                    valueToWrite ^= 1;
                    written = 1;
                    myMaze[i][j] = valueToWrite;
                    timesToWrite = decode(bytesArr, indexToStart, false);
                    indexToStart = decode(bytesArr, indexToStart, true);
                }
            }
        }
    }
}
