import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class SudokuV3 extends JFrame {
    private JPanel boardPanel;
    private JTextField[][] cells;
    private JButton solveButton;
    private JButton clearButton;
    private JLabel introLabel;
    private JLabel timerLabel;
    private SudokuSolver sudokuSolver;
    private Timer timer;

    public static void main(String[] args) {
        new SudokuV2();
    }

    public SudokuV3() {
        setTitle("Sudoku Game");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(9, 9));

        add(boardPanel, BorderLayout.CENTER);

        timerLabel = new JLabel("Time left: 1:00");
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        timer = new Timer(1000, new ActionListener() {
            int time = 60;

            public void actionPerformed(ActionEvent e) {
                time--;
                timerLabel.setText("Time left: 0:" + (time < 10 ? "0" : "") + time);
                if (time == 0) {
                    timer.stop();
                    JOptionPane.showMessageDialog(SudokuV3.this, "Time's up!");
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

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1));
        introLabel = new JLabel("Welcome to Sudoku!");
        introLabel.setHorizontalAlignment(SwingConstants.CENTER);
        buttonPanel.add(introLabel);

        solveButton = new JButton("Solve");
        solveButton.addActionListener(new SolveButtonListener());
        buttonPanel.add(solveButton);

        clearButton = new JButton("Clear");
        clearButton.addActionListener(new ClearButtonListener());
        buttonPanel.add(clearButton);

        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        buttonPanel.add(timerLabel);

        add(buttonPanel, BorderLayout.EAST);

        sudokuSolver = new SudokuSolver();
        sudokuSolver.setDifficulty(2);

        JPanel shapesPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.RED);
                g.drawRect(10, 10, 50, 50);
                g.setColor(Color.BLUE);
                g.drawOval(70, 10, 50, 50);
            }
        };
        add(shapesPanel, BorderLayout.NORTH);

        JPanel container1 = new JPanel();
        container1.setLayout(new BorderLayout());

        JPanel container2 = new JPanel();
        container2.setLayout(new GridLayout(2,2));

        JPanel container3 = new JPanel();
        container3.setLayout(new FlowLayout());

        } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
        }
        System.exit(0);
        }
        }
        
    private class ClearButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
          // Clear text fields
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    cells[i][j].setText("");
                }
            }
        }

    public static void main(String[] args) {
        SudokuV2 sudoku = new SudokuV2();
        sudoku.setVisible(true);
    }
}
