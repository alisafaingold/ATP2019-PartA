package test;

import algorithms.mazeGenerators.*;
import algorithms.search.*;

import java.util.ArrayList;

public class RunSearchOnMaze {
    public static void main(String[] args) {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(21, 21);
        maze.print();
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        /**double sum=0;
        for(int i=0; i<30; i++){
         maze = mg.generate(1000, 1000);
         //maze.print();
         searchableMaze = new SearchableMaze(maze);
         sum += (new BestFirstSearch().measureAlgorithmTimeMillis(searchableMaze));
         }
        System.out.println(sum/50);**/

        /**for(int i=0; i<30; i++){
            maze = mg.generate(10, 10);
            searchableMaze = new SearchableMaze(maze);
            BestFirstSearch searcher = new BestFirstSearch();
            DepthFirstSearch searcher2 = new DepthFirstSearch();
            Solution solution = searcher.solve(searchableMaze);
            int bfs = solution.getSolutionPath().size();
            solution = searcher2.solve(searchableMaze);
            int dfs = solution.getSolutionPath().size();
            if(bfs>= dfs)
                System.out.println(bfs + " vs. " + dfs);
            else
                System.out.println("yay :)");
        }**/
        //solveProblem(searchableMaze, new BreadthFirstSearch());
        /** Average Time - 2373.5 out of 50 Tests **/
        solveProblem(searchableMaze, new DepthFirstSearch());
        /** Average Time - 1022.04 out of 50 Tests **/
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