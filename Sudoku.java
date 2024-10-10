import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Sudoku extends JFrame {
    private JPanel boardPanel;
    private JTextField[][] cells;
    private JButton solveButton;
    private JButton clearButton;
    private SudokuSolver sudokuSolver;
    protected JLabel timerLabel;
    protected Timer timer;

    public Sudoku() {
        setTitle("Sudoku Game");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(9, 9));

        timerLabel = new JLabel("Time left: 1:00");
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 24));

        timer = new Timer(1000, new ActionListener() {
            int time = 60;

            public void actionPerformed(ActionEvent e) {
                time--;
                timerLabel.setText("Time left: 0:" + (time < 10 ? "0" : "") + time);
                if (time == 0) {
                    timer.stop();
                    JOptionPane.showMessageDialog(Sudoku.this, "Time's up!");

                }
            }
        });
        timer.start();

        cells = new JTextField[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j] = new JTextField();
                cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                cells[i][j].setFont(new Font("Arial", Font.PLAIN, 18));
                cells[i][j].addKeyListener(new CellKeyListener());
                boardPanel.add(cells[i][j]);
            }
        }

        solveButton = new JButton("Solve");
        solveButton.addActionListener(new SolveButtonListener());

        clearButton = new JButton("Clear");
        clearButton.addActionListener(new ClearButtonListener());

        add(timerLabel, BorderLayout.WEST);
        add(boardPanel, BorderLayout.CENTER);
        add(solveButton, BorderLayout.NORTH);
        add(clearButton, BorderLayout.SOUTH);

        sudokuSolver = new SudokuSolver();
    }

    private class CellKeyListener implements KeyListener {
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!Character.isDigit(c) || c == '0') {
                e.consume();
            }
        }

        public void keyPressed(KeyEvent e) {
        }

        public void keyReleased(KeyEvent e) {
        }
    }

    private class SolveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int[][] board = new int[9][9];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    String text = cells[i][j].getText();
                    if (!text.isEmpty()) {
                        board[i][j] = Integer.parseInt(text);
                    }
                }
            }

            boolean solved = sudokuSolver.solveSudoku(board);

            if (solved) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        cells[i][j].setText(Integer.toString(board[i][j]));
                    }
                }
                timer.stop();
                JOptionPane.showMessageDialog(Sudoku.this, "Sudoku puzzle solved successfully!");
            } else {
                timer.stop();
                JOptionPane.showMessageDialog(Sudoku.this, "Failed to solve Sudoku puzzle.");
            }
        }
    }

    private class ClearButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    cells[i][j].setText("");
                }
            }
        }
    }

    private class SudokuSolver {
    public boolean solveSudoku(int[][] board) {
        int[] emptyCell = findEmptyCell(board);
        int row = emptyCell[0];
        int col = emptyCell[1];

        if (row == -1 && col == -1) {
            return true;
        }

        for (int num = 1; num <= 9; num++) {
            if (isValidMove(board, row, col, num)) {

                board[row][col] = num;

                if (solveSudoku(board)) {
                    return true;
                }

                board[row][col] = 0;
            }
        }

        return false;
    }

    private int[] findEmptyCell(int[][] board) {
        int[] emptyCell = new int[2];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    emptyCell[0] = row;
                    emptyCell[1] = col;
                    return emptyCell;
                }
            }
        }
        emptyCell[0] = -1;
        emptyCell[1] = -1;
        return emptyCell;
    }

    private boolean isValidMove(int[][] board, int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num
                    || board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == num) {
                return false;
            }
        }
        return true;
    }
}

    public static void main(String[] args) {
        Sudoku sudokuGame = new Sudoku();
        sudokuGame.setVisible(true);
    }
}

