import java.util.ArrayList;
import java.util.List;

// Implements the 8-Puzzle logic
public class NPuzzle implements Puzzle {
    private final PuzzleState startState;
    private final int[] goal = {1, 2, 3, 4, 5, 6, 7, 8, 0};

    // Constructor to store the starting puzzle configuration
    public NPuzzle(PuzzleState startState) {
        this.startState = startState;
    }

    // Returns the initial puzzle state
    @Override
    public PuzzleState getStartState() {
        return startState;
    }

    // Checks if a state matches the finish state
    @Override
    public boolean isGoal(PuzzleState state) {
        for (int i = 0; i < goal.length; i++) {
            if (state.tiles[i] != goal[i]) return false;
        }
        return true;
    }

    // Generates all valid neighbor states by moving the blank tile
    @Override
    public List<PuzzleState> getNeighbors(PuzzleState state) {
        List<PuzzleState> neighbors = new ArrayList<>();
        int zeroIndex = -1;

        // Finds the index of the blank tile
        for (int i = 0; i < state.tiles.length; i++) {
            if (state.tiles[i] == 0) {
                zeroIndex = i;
                break;
            }
        }

        int row = zeroIndex / 3;
        int col = zeroIndex % 3;

        // Possible movements: up, down, left, right
        int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] move : moves) {
            int newRow = row + move[0];
            int newCol = col + move[1];

            if (newRow >= 0 && newRow < 3 && newCol >= 0 && newCol < 3) {
                int newIndex = newRow * 3 + newCol;
                int[] newTiles = state.tiles.clone();

                // Swaps the blank with the target tile
                newTiles[zeroIndex] = newTiles[newIndex];
                newTiles[newIndex] = 0;

                neighbors.add(new PuzzleState(newTiles));
            }
        }
        return neighbors;
    }

    // Calculates the Manhattan distance heuristic.
    @Override
    public int estimateCost(PuzzleState state) {
        int distance = 0;
        for (int i = 0; i < state.tiles.length; i++) {
            int val = state.tiles[i];
            if (val == 0) continue;
            int targetRow = (val - 1) / 3;
            int targetCol = (val - 1) % 3;
            int currRow = i / 3;
            int currCol = i % 3;
            distance += Math.abs(targetRow - currRow) + Math.abs(targetCol - currCol);
        }
        return distance;
    }
}
