package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.List;


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

    public byte[] toByteArray() {
        //first part
        int index =0;
        int [] paramNumber = paramArrayNumber();
        byte[] byteParam = new byte[18];
        for(int i=0; i<6; i++){
            byte[] numberToByte = encode(paramNumber[i]);
           addArray(byteParam,numberToByte,index);
            index=index+3;
        }
        //second part
        ArrayList<byte[]> mazeByteList = new ArrayList<>();
        int lookingValue=0;
        int currCounter=0;
        byte[] byteFirstGrid;
        int mazeArraySize=0;
        //encode maze
        for(int i=0; i<row; i++){
            for(int j=0; j<column; j++){
                if(myMaze[i][j]==lookingValue){
                    currCounter++;
                }
                else{
                    byteFirstGrid = encode(currCounter);
                    mazeByteList.add(byteFirstGrid);
                    lookingValue^=1;
                    currCounter=1;
                }
            }
        }
        //maze byte array size
        for(int i=0; i<mazeByteList.size(); i++){
            mazeArraySize=mazeArraySize+mazeByteList.get(i).length;
        }
        byte[] mazeByte = new byte[mazeArraySize];
        //add to one array
        for(int i=0; i<mazeByte.length; i++) {
            for (byte byteToadd :
                    mazeByteList.get(i)) {
                mazeByte[i] = byteToadd;

            }
        }
        //connecting two arrays
        byte[] mazeObjectByte = new byte[byteParam.length + mazeByte.length];
        System.arraycopy(byteParam, 0, mazeObjectByte, 0, byteParam.length);
        System.arraycopy(mazeByte,0,mazeObjectByte,byteParam.length,mazeByte.length);
        //return
        return mazeObjectByte;
    }

    private int[] paramArrayNumber (){
        int [] paramNumber= new int[6];
        paramNumber[0] = this.row; //rowNumber
        paramNumber[1] = this.column; //colNumber
        paramNumber[2] = startPosition.getRowIndex(); //rowStart
        paramNumber[3] = startPosition.getColumnIndex();//colStart
        paramNumber[4] = GoalPosition.getRowIndex();//rowGoal
        paramNumber[5] = GoalPosition.getColumnIndex();//colGoal
        return paramNumber;
    }

    private void addArray(byte[] param, byte[] toAdd, int startIndex) {
        int lengthToAdd = toAdd.length;
        if (lengthToAdd < 3) {
            while (lengthToAdd != 3) {
                param[startIndex] = 0;
                startIndex++;
                lengthToAdd++;
            }
        }
        for (int i = 0; i < toAdd.length; i++) {
            param[startIndex] = toAdd[i];
        }
    }

    private byte[] encode(int n) {
        int numRelevantBits = 32 - Long.numberOfLeadingZeros(n);
        int numBytes = (numRelevantBits + 6) / 7;
        if (numBytes == 0)
            numBytes = 1;
        byte[] output = new byte[numBytes];
        for (int i = numBytes - 1; i >= 0; i--) {
            int curByte = (int) (n & 0x7F);
            if (i != (numBytes - 1))
                curByte |= 0x80;
            output[i] = (byte) curByte;
            n >>>= 7;
        }
        return output;
    }
}



