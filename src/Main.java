import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Generates a randomized solvable puzzle state
        PuzzleState startState = generateRandomSolvableState();

        // Prints the randomly generated puzzle starting state
        System.out.println("Randomized Start State:");
        System.out.println(startState);

        // Creates NPuzzle, MetaPlanner, and Solution objects
        Puzzle puzzle = new NPuzzle(startState);
        MetaPlanner planner = new MetaPlanner();
        Solution solution = planner.solvePuzzle(puzzle);

        // Prints the solution to the puzzle
        System.out.println(solution);
    }

    // Generates a solvable random 8-puzzle board
    private static PuzzleState generateRandomSolvableState() {
        int[] tiles = new int[9];
        Random rand = new Random();

        do {
            // Fills the list with numbers 0 to 8 and shuffles them
            List<Integer> nums = new ArrayList<>();
            for (int i = 0; i < 9; i++) nums.add(i);
            Collections.shuffle(nums, rand);
            for (int i = 0; i < 9; i++) tiles[i] = nums.get(i);
        } while (!isSolvable(tiles)); // Repeat until solvable

        return new PuzzleState(tiles);
    }

    // Checks if the puzzle is solvable
    private static boolean isSolvable(int[] tiles) {
        int inversions = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = i + 1; j < tiles.length; j++) {
                if (tiles[i] != 0 && tiles[j] != 0 && tiles[i] > tiles[j]) {
                    inversions++;
                }
            }
        }
        return inversions % 2 == 0;
    }
}
