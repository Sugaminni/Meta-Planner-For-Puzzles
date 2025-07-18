import java.util.Random;

// Chooses the best algorithm for solving the puzzle
public class MetaPlanner {
    private final Solver bfsSolver;
    private final Solver ucsSolver;
    private final Solver aStarSolver;

    public MetaPlanner() {
        bfsSolver = new BFSSolver();
        ucsSolver = new UCSSolver();
        aStarSolver = new AStarSolver();
    }

    // Analyzes the puzzle and solves it with the best algorithm
    public Solution solvePuzzle(Puzzle puzzle) {
        System.out.println("Analyzing puzzle...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Solver chosenSolver = chooseSolver(puzzle);
        System.out.println("Meta-Planner will use: " + chosenSolver.getClass().getSimpleName());
        return chosenSolver.solve(puzzle);
    }

    // Rule-based logic for choosing solvers.
    private Solver chooseSolver(Puzzle puzzle) {
        int size = puzzle.getStartState().tiles.length;

        Random rand = new Random();

        if (size <= 6) {
            return bfsSolver; // Shallow puzzles
        } else if (size <= 12) { // "Coin Tosses" because 8 tile puzzles are often in UCS(rand makes program randomly choose UCS or A*)
            return (rand.nextDouble() < 0.5) ? ucsSolver : aStarSolver;
        } else {
            return aStarSolver; // Complex puzzles
        }
    }
}
