import java.util.List;

public interface Puzzle {

    // Interface that defines how puzzle should be implemented.
    // Allows solvers to work with different puzzle types

    // Returns initial puzzle state
    PuzzleState getStartState();

    // Checks if the given state is the solved state
    boolean isGoal(PuzzleState state);

    // Generates all valid moves from current state
    List<PuzzleState> getNeighbors(PuzzleState state);

    //  Estimates cost from current state to solved state (A*).
    int estimateCost(PuzzleState state);
}
