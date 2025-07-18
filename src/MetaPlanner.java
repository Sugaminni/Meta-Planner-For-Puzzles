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

        if (size <= 9) {
            return bfsSolver;
        } else if (size <= 15) {
            return ucsSolver;
        } else {
            return aStarSolver;
        }
    }
}
