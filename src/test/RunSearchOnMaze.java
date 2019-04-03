package test;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;

import java.util.ArrayList;

public class RunSearchOnMaze {
    public static void main(String[] args) {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(20, 20);
        maze.print();
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        /**double sum=0;
        for(int i=0; i<50; i++){
         maze = mg.generate(1000, 1000);
         //maze.print();
         searchableMaze = new SearchableMaze(maze);
         sum += (new BreadthFirstSearch().measureAlgorithmTimeMillis(searchableMaze));
         }
        System.out.println(sum/50);**/

        /**for(int i=0; i<10; i++){
            maze = mg.generate(1000, 1000);
            searchableMaze = new SearchableMaze(maze);
            BestFirstSearch searcher = new BestFirstSearch();
            BreadthFirstSearch searcher2 = new BreadthFirstSearch();
            Solution solution = searcher.solve(searchableMaze);
            int bfs = solution.getSolutionPath().size();
            solution = searcher2.solve(searchableMaze);
            int dfs = solution.getSolutionPath().size();
            if(bfs> dfs)
                System.out.println(bfs + " vs. " + dfs);
            else if(bfs==dfs)
                System.out.println("equals");
            else
                System.out.println("there is a problem");
        }**/

        /** Average Time - 967.72 out of 50 Tests **/
        solveProblem(searchableMaze, new BreadthFirstSearch());
        /** Average Time - 2373.5 out of 50 Tests **/
        solveProblem(searchableMaze, new DepthFirstSearch());
        /** Average Time - 1700.04 out of 50 Tests **/
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