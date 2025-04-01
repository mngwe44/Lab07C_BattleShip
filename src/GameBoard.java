import java.util.ArrayList;
import java.util.Random;

public class GameBoard {
    private char[][] board;
    private ArrayList<Ship> ships;
    private Random random = new Random();

    public GameBoard() {
        board = new char[10][10];
        ships = new ArrayList<>();
        initializeBoard();
        placeShips();
    }

    private void initializeBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = ' ';
            }
        }
    }

    void placeShips() {
        int[] shipSizes = {5, 4, 3, 3, 2}; // Standard Battleship ship sizes

        for (int size : shipSizes) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(10);
                int col = random.nextInt(10);
                boolean horizontal = random.nextBoolean();

                if (canPlaceShip(row, col, size, horizontal)) {
                    Ship ship = new Ship(row, col, size, horizontal);
                    ships.add(ship);
                    markShipOnBoard(ship);
                    placed = true;
                }
            }
        }
    }

    private boolean canPlaceShip(int row, int col, int size, boolean horizontal) {
        if (horizontal) {
            if (col + size > 10) return false; // Out of bounds
            for (int i = 0; i < size; i++) {
                if (board[row][col + i] != ' ') return false; // Overlapping
            }
        } else {
            if (row + size > 10) return false; // Out of bounds
            for (int i = 0; i < size; i++) {
                if (board[row + i][col] != ' ') return false; // Overlapping
            }
        }
        return true;
    }

    private void markShipOnBoard(Ship ship) {
        int row = ship.getRow();
        int col = ship.getCol();
        for (int i = 0; i < ship.getSize(); i++) {
            board[row][col] = 'S';
            if (ship.isHorizontal()) col++;
            else row++;
        }
    }

    public boolean isHit(int row, int col) {
        if (board[row][col] == 'S') {
            board[row][col] = 'H'; // Mark hit
            return true;
        }
        board[row][col] = 'M'; // Mark miss
        return false;
    }

    public boolean isShipSunk(int row, int col) {
        for (Ship ship : ships) {
            if (ship.contains(row, col) && ship.isSunk(board)) {
                return true;
            }
        }
        return false;
    }

    public boolean allShipsSunk() {
        for (Ship ship : ships) {
            if (!ship.isSunk(board)) return false;
        }
        return true;
    }
}
