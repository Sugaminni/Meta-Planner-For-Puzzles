import java.util.*;

// Implements Uniform Cost Search
public class UCSSolver implements Solver {
    @Override
    public Solution solve(Puzzle puzzle) {
        System.out.println("We'll use Uniform Cost Search to solve this puzzle!");

        // Starting point
        PuzzleState start = puzzle.getStartState();

        // Priority queue ordered by path cost (g)
        PriorityQueue<Node> open = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));

        // Cost map: stores best known cost for each state
        Map<PuzzleState, Integer> costSoFar = new HashMap<>();

        // Tracks how each state was reached
        Map<PuzzleState, PuzzleState> cameFrom = new HashMap<>();

        // Initializes queue with start node
        open.add(new Node(start, 0));
        costSoFar.put(start, 0);

        int steps = 0;

        while (!open.isEmpty()) {
            Node current = open.poll(); // Gets node with the lowest cost
            steps++;

            if (steps % 1000 == 0) {
                System.out.println("UCSSolver expanded " + steps + " states...");
            }

            // Checks for finish state
            if (puzzle.isGoal(current.state)) {
                return reconstructPath(start, current.state, cameFrom, steps);
            }

            // Explores neighbors
            for (PuzzleState neighbor : puzzle.getNeighbors(current.state)) {
                int newCost = costSoFar.get(current.state) + 1; // Cost to move to neighbor

                // Updates cost if a new path is better
                if (!costSoFar.containsKey(neighbor) || newCost < costSoFar.get(neighbor)) {
                    costSoFar.put(neighbor, newCost);
                    open.add(new Node(neighbor, newCost));
                    cameFrom.put(neighbor, current.state);
                }
            }
        }

        // No solution found
        return new Solution(null, steps);
    }

    // Builds a solution path by going backward from finish to start
    private Solution reconstructPath(PuzzleState start, PuzzleState goal,
                                     Map<PuzzleState, PuzzleState> cameFrom, int steps) {
        List<PuzzleState> path = new ArrayList<>();
        PuzzleState curr = goal;

        // Traces back the path
        while (curr != null && !curr.equals(start)) {
            path.add(curr);
            curr = cameFrom.get(curr);
        }

        // Adds start state and reverse paths
        path.add(start);
        Collections.reverse(path);

        return new Solution(path, steps);
    }

    // Inner class representing a node in the UCSSolver priority queue
    private static class Node {
        PuzzleState state;
        int cost;

        Node(PuzzleState state, int cost) {
            this.state = state;
            this.cost = cost;
        }
    }
}
