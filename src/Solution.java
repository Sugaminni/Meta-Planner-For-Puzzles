import java.util.List;

// Holds the results of solving a puzzle.
public class Solution {

    // The ordered list of states from start to goal
    List<PuzzleState> path;

    // The total number of nodes expanded during the search
    int steps;

    // Constructor for the solution object
    public Solution(List<PuzzleState> path, int steps) {
        this.path = path;
        this.steps = steps;
    }


    // Converts the solution to a string for printing, Prints the solution path if found
    @Override
    public String toString() {
        if (path == null) {
            return "No solution found. Steps expanded: " + steps;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Solution found in ").append(steps).append(" steps:\n");

        for (PuzzleState state : path) {
            sb.append(state).append("\n");
        }

        return sb.toString();
    }
}
