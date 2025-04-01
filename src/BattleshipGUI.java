import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BattleshipGUI {
    private JFrame frame;
    private JButton[][] grid;
    private JLabel missLabel, strikeLabel, totalMissLabel, totalHitLabel;
    private BattleshipGame game;

    public BattleshipGUI() {
        game = new BattleshipGame();
        frame = new JFrame("Battleship Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 700);
        frame.setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(10, 10));
        grid = new JButton[10][10];

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                grid[row][col] = new JButton();
                grid[row][col].setBackground(Color.CYAN);
                final int r = row, c = col;
                grid[row][col].addActionListener(e -> handleFire(r, c));
                boardPanel.add(grid[row][col]);
            }
        }

        JPanel statusPanel = new JPanel(new GridLayout(1, 4));
        missLabel = new JLabel("Miss: 0");
        strikeLabel = new JLabel("Strikes: 0");
        totalMissLabel = new JLabel("Total Misses: 0");
        totalHitLabel = new JLabel("Total Hits: 0");

        statusPanel.add(missLabel);
        statusPanel.add(strikeLabel);
        statusPanel.add(totalMissLabel);
        statusPanel.add(totalHitLabel);

        JPanel controlPanel = new JPanel();
        JButton playAgain = new JButton("Play Again");
        JButton quit = new JButton("Quit");

        playAgain.addActionListener(e -> resetGame());
        quit.addActionListener(e -> System.exit(0));

        controlPanel.add(playAgain);
        controlPanel.add(quit);

        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(statusPanel, BorderLayout.NORTH);
        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void handleFire(int row, int col) {
        String result = game.fireAt(row, col);
        grid[row][col].setEnabled(false);

        if (result.equals("HIT")) {
            grid[row][col].setBackground(Color.RED);
        } else if (result.equals("MISS")) {
            grid[row][col].setBackground(Color.YELLOW);
        } else if (result.equals("SUNK")) {
            JOptionPane.showMessageDialog(frame, "Ship Sunk!");
        } else if (result.equals("WIN")) {
            JOptionPane.showMessageDialog(frame, "You won! Play again?");
            resetGame();
        } else if (result.equals("LOSS")) {
            JOptionPane.showMessageDialog(frame, "You lost! Play again?");
            resetGame();
        }

        updateStatus();
    }

    private void updateStatus() {
        missLabel.setText("Miss: " + game.getMissCounter());
        strikeLabel.setText("Strikes: " + game.getStrikeCounter());
        totalMissLabel.setText("Total Misses: " + game.getTotalMisses());
        totalHitLabel.setText("Total Hits: " + game.getTotalHits());
    }

    private void resetGame() {
        game.resetGame();
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                grid[row][col].setBackground(Color.CYAN);
                grid[row][col].setEnabled(true);
            }
        }
        updateStatus();
    }

    public static void main(String[] args) {
        new BattleshipGUI();
    }
}

