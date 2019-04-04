package algorithms.mazeGenerators;

public class Maze {
    private int[][] myMaze;
    private int row;
    private int column;
    private Position startPosition;
    private Position GoalPosition;

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
    }

    public void setValueToCell(int row, int col, int val) {
        if (isSafe(row, col))
            myMaze[row][col] = val;
    }

    public int getCellValue(int row, int col) {
        if (!isSafe(row, col)) {
            return -1;
        }
        return myMaze[row][col];
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getGoalPosition() {
        return GoalPosition;
    }

    public void setStartPosition(Position startPosition) {
        if(startPosition!=null)
            this.startPosition = startPosition;
    }

    public void setGoalPosition(Position goalPosition) {
        if(goalPosition != null)
            GoalPosition = goalPosition;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isSafe(int row, int col) {
        return (row >= 0 && row < this.row && col >= 0 && col < this.column);
    }

    public boolean isPassable(int row, int col) {
        return isSafe(row, col) && myMaze[row][col] == 0;
    }

    public void print() {
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

    public void simplePrint() {
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
}
