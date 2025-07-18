import java.util.*;

// Implements Breadth-First Search algorithm
public class BFSSolver implements Solver {
    @Override
    public Solution solve(Puzzle puzzle) {
        System.out.println("We'll use Breadth-first search to solve this puzzle!");
        PuzzleState start = puzzle.getStartState();

        Queue<PuzzleState> queue = new LinkedList<>();
        Map<PuzzleState, PuzzleState> cameFrom = new HashMap<>();
        Set<PuzzleState> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        int steps = 0;

        while (!queue.isEmpty()) {
            PuzzleState current = queue.poll();
            steps++;

            if (steps % 1000 == 0) {
                System.out.println("BFS expanded " + steps + " states...");
            }

            if (puzzle.isGoal(current)) {
                return reconstructPath(start, current, cameFrom, steps);
            }

            for (PuzzleState neighbor : puzzle.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    cameFrom.put(neighbor, current);
                }
            }
        }
        return new Solution(null, steps);
    }

    // Reconstructs the path from start to finish
    private Solution reconstructPath(PuzzleState start, PuzzleState goal,
                                     Map<PuzzleState, PuzzleState> cameFrom, int steps) {
        List<PuzzleState> path = new ArrayList<>();
        PuzzleState curr = goal;
        while (curr != null && !curr.equals(start)) {
            path.add(curr);
            curr = cameFrom.get(curr);
        }
        path.add(start);
        Collections.reverse(path);
        return new Solution(path, steps);
    }
}
