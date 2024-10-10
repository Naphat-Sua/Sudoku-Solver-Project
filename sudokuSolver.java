class sudokuSolver extends SudokuV3{
    // Solve the Sudoku puzzle
    public boolean solveSudoku(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                if (isValid(board, row, col, num)) {
                    board[row][col] = num;
                if (solveSudoku(board)) {
                    return true;
                } else {
                    board[row][col] = 0;
                }
            }
        }
    return false;
        }
    }
    }
    return true;
    }
