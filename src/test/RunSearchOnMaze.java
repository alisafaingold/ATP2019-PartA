package test;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;

import java.util.ArrayList;

/**
 * Created by Aviadjo on 3/22/2017.
 */
public class RunSearchOnMaze {
    public static void main(String[] args) {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(10, 10);
        maze.print();
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        /**double sum=0;
        for(int i=0; i<50; i++){
            maze = mg.generate(1000, 1000);
            //maze.print();
            searchableMaze = new SearchableMaze(maze);
            sum += (new DepthFirstSearch()).measureAlgorithmTimeMillis(searchableMaze);
        }
        System.out.println(sum/50);**/
        solveProblem(searchableMaze, new BreadthFirstSearch());
        /** Average Time - 2373.5 out of 50 Tests **/
        //solveProblem(searchableMaze, new DepthFirstSearch());
        //solveProblem(searchableMaze, new BestFirstSearch());
    }

    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
        }
    }
}