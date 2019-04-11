package test;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;
import sun.awt.SunHints;

import java.util.ArrayList;

/**
 * Created by Aviadjo on 3/22/2017.
 */


public class RunSearchOnMaze {

    public static void main(String[] args) {
        int numRelevantBits = 32 - Integer.numberOfLeadingZeros(9458);
        System.out.println(numRelevantBits);
        System.out.println(9458&127);

        int primary = 9458;
        System.out.println(primary >>>=7);

        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(30, 30);
        SearchableMaze searchableMaze = new SearchableMaze(maze);

        solveProblem(searchableMaze, new BreadthFirstSearch());
        solveProblem(searchableMaze, new DepthFirstSearch());
        solveProblem(searchableMaze, new BestFirstSearch());
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