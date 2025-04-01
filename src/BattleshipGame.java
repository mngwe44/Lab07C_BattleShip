import java.util.ArrayList;
import java.util.Random;

public class BattleshipGame {
    private GameBoard board;
    private int missCounter, strikeCounter, totalMisses, totalHits;
    private static final int MAX_MISSES = 5;
    private static final int MAX_STRIKES = 3;

    public BattleshipGame() {
        resetGame();
    }

    public void resetGame() {
        board = new GameBoard();
        missCounter = 0;
        strikeCounter = 0;
        totalMisses = 0;
        totalHits = 0;
        board.placeShips();
    }

    public String fireAt(int row, int col) {
        if (board.isHit(row, col)) {
            totalHits++;
            missCounter = 0;
            if (board.isShipSunk(row, col)) {
                return "SUNK";
            }
            if (board.allShipsSunk()) {
                return "WIN";
            }
            return "HIT";
        } else {
            missCounter++;
            totalMisses++;
            if (missCounter == MAX_MISSES) {
                strikeCounter++;
                missCounter = 0;
            }
            if (strikeCounter == MAX_STRIKES) {
                return "LOSS";
            }
            return "MISS";
        }
    }

    public int getMissCounter() { return missCounter; }
    public int getStrikeCounter() { return strikeCounter; }
    public int getTotalMisses() { return totalMisses; }
    public int getTotalHits() { return totalHits; }
}
