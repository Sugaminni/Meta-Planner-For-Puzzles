public class Main {
    public static void main(String[] args) {

        // Array for the puzzle tiles 0 represents blank tile
        int[] tiles = {1, 2, 3, 4, 5, 6, 0, 7, 8};

        // Creates PuzzleState, Puzzle, and MetaPlanner Instances
        PuzzleState startState = new PuzzleState(tiles);

        // NPuzzle implements puzzle interface, allowing MetaPlanner to handle
        Puzzle puzzle = new NPuzzle(startState);

        // Decides which algorithm to use to solve puzzle
        MetaPlanner planner = new MetaPlanner();

        // Solves the puzzle using MetaPlanner
        Solution solution = planner.solvePuzzle(puzzle);

        // Prints the solution to the puzzle
        System.out.println(solution);
    }
}
