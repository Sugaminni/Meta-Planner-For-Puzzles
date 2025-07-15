import java.util.*;

// Implements A* Search
public class AStarSolver implements Solver {
    @Override
    public Solution solve(Puzzle puzzle) {
        System.out.println("Thinking (A*)...");
        PuzzleState start = puzzle.getStartState();

        PriorityQueue<Node> open = new PriorityQueue<>(Comparator.comparingInt(n -> n.f));
        Map<PuzzleState, Integer> gScore = new HashMap<>();
        Map<PuzzleState, PuzzleState> cameFrom = new HashMap<>();

        gScore.put(start, 0);
        open.add(new Node(start, puzzle.estimateCost(start)));

        int steps = 0;

        while (!open.isEmpty()) {
            Node current = open.poll();
            steps++;

            if (steps % 1000 == 0) {
                System.out.println("A* expanded " + steps + " states...");
            }

            if (puzzle.isGoal(current.state)) {
                return reconstructPath(start, current.state, cameFrom, steps);
            }

            for (PuzzleState neighbor : puzzle.getNeighbors(current.state)) {
                int tentativeG = gScore.get(current.state) + 1;

                if (!gScore.containsKey(neighbor) || tentativeG < gScore.get(neighbor)) {
                    gScore.put(neighbor, tentativeG);
                    int f = tentativeG + puzzle.estimateCost(neighbor);
                    open.add(new Node(neighbor, f));
                    cameFrom.put(neighbor, current.state);
                }
            }
        }
        return new Solution(null, steps);
    }

    // Reconstructs the solution path from start state to finish
    private Solution reconstructPath(PuzzleState start, PuzzleState goal, Map<PuzzleState, PuzzleState> cameFrom, int steps) {
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

    // Represents a node in the A* priority queue
    private static class Node {
        PuzzleState state;
        int f;

        Node(PuzzleState state, int f) {
            this.state = state;
            this.f = f;
        }
    }
}
