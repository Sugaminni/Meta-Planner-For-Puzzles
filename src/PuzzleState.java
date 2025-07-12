import java.util.Arrays;


// Represents a specific arrangement of the puzzle board
public class PuzzleState {
    int[] tiles; // flat array storing puzzle tiles

    // Constructor: Creates a new puzzle state from an array of tiles
    public PuzzleState(int[] tiles) {
        this.tiles = tiles;
    }

    // Checks if this puzzle state is equal to another
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PuzzleState)) return false;
        PuzzleState that = (PuzzleState) o;
        return Arrays.equals(tiles, that.tiles);
    }

    // Gives a unique hash code for using PuzzleState in hash maps
    @Override
    public int hashCode() {
        return Arrays.hashCode(tiles);
    }

    // Displays the puzzle board in a grid format.
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tiles.length; i++) {
            sb.append(tiles[i]).append(" ");
            if ((i + 1) % 3 == 0) sb.append("\n");
        }
        return sb.toString();
    }
}

