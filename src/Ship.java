public class Ship {
    private int row, col, size;
    private boolean horizontal;

    public Ship(int row, int col, int size, boolean horizontal) {
        this.row = row;
        this.col = col;
        this.size = size;
        this.horizontal = horizontal;
    }

    public boolean contains(int r, int c) {
        if (horizontal) {
            return r == row && c >= col && c < col + size;
        } else {
            return c == col && r >= row && r < row + size;
        }
    }

    public boolean isSunk(char[][] board) {
        for (int i = 0; i < size; i++) {
            int r = row, c = col;
            if (horizontal) c += i;
            else r += i;
            if (board[r][c] != 'H') return false;
        }
        return true;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public int getSize() { return size; }
    public boolean isHorizontal() { return horizontal; }
}
