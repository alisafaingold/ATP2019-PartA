package algorithms.search;
import algorithms.mazeGenerators.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {
    BestFirstSearch bfs = new BestFirstSearch();

    @Test
    void getName() {
        assertEquals("Best First Search", bfs.getName());
    }

    @Test
    void solveOneSolutionMaze() {
        AMazeGenerator empty = new EmptyMazeGenerator();
        Maze mazy = empty.generate(9,9);
        mazy.setValueToCell(0,1,1);
        mazy.setValueToCell(0,5,1);
        mazy.setValueToCell(1,1,1);
        mazy.setValueToCell(1,3,1);
        mazy.setValueToCell(1,5,1);
        mazy.setValueToCell(1,6,1);
        mazy.setValueToCell(1,7,1);
        mazy.setValueToCell(2,3,1);
        mazy.setValueToCell(2,7,1);
        mazy.setValueToCell(3,0,1);
        mazy.setValueToCell(3,1,1);
        mazy.setValueToCell(3,2,1);
        mazy.setValueToCell(3,3,1);
        mazy.setValueToCell(3,4,1);
        mazy.setValueToCell(3,5,1);
        mazy.setValueToCell(3,7,1);
        mazy.setValueToCell(4,5,1);
        mazy.setValueToCell(5,1,1);
        mazy.setValueToCell(5,3,1);
        mazy.setValueToCell(5,4,1);
        mazy.setValueToCell(5,5,1);
        mazy.setValueToCell(5,6,1);
        mazy.setValueToCell(5,7,1);
        mazy.setValueToCell(6,1,1);
        mazy.setValueToCell(6,3,1);
        mazy.setValueToCell(6,7,1);
        mazy.setValueToCell(7,1,1);
        mazy.setValueToCell(7,3,1);
        mazy.setValueToCell(7,5,1);
        mazy.setValueToCell(7,7,1);
        mazy.setValueToCell(8,1,1);
        mazy.setValueToCell(8,5,1);
        SearchableMaze sm = new SearchableMaze(mazy);
        Solution sol = bfs.solve(sm);
        assertNotEquals(null, sol); //make sure its not equal null
    }

    @Test
    void solve() {
        Maze myMaze = (new MyMazeGenerator()).generate(20,20);
        Solution sol = bfs.solve(new SearchableMaze(myMaze)); //solve maze with bfs
        assertNotEquals(null, sol); //make sure its not equal null
    }

    @Test
    void getNumberOfNodesEvaluated() {
        assertEquals("0", bfs.getNumberOfNodesEvaluated()); //make sure before run its 0
    }

    @Test
    void nullDomain(){
        Solution sol = bfs.solve(null);
        assertTrue(sol.getSolutionPath().isEmpty());
    }

    @Test
    void nullStateSearchable(){
        SearchableMaze sm = new SearchableMaze(null);
        assertEquals(sm.getStartState().toString(),"");
        assertTrue(sm.getAllPossibleStates(null).isEmpty());
    }

    @Test
    void nullMazeState(){
        MazeState ms = new MazeState(null);
        assertEquals(ms.toString(),"");
        assertEquals(ms.getColumn(),-1);
        assertEquals(ms.getRow(),-1);
    }
}