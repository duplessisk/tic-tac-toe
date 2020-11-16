package backend;

/**
 This class generates the tic tac toe board. The state of the board can be
 altered by actions from the user and ai
 * */
public class Board  {

    private String[][] board = new String[3][3];

    /**
     * Constructs a Tic-Tac-Toe board with 3 rows and 3 columns
     */
    public Board() {
        for (int row = 0; row <= 2; row++) {
            for (int column = 0; column <= 2; column++) {
                board[row][column] = "-";
            }
        }
    }

    /**
     * Returns the current board
     * @return the current board
     */
    public String[][] getBoard() {
        return board;
    }

    /**
     * Checks to see if the ai won
     * @return true if ai won, false otherwise
     */
    public boolean aiWon() {
        return gameString().contains("OOO");
    }

    /**
     * Checks to see if the user won
     * @return true if user won, false otherwise
     */
    public boolean userWon() {
        return gameString().contains("XXX");
    }

    /**
     * Returns the value of the board at the specified row and column
     * @param row - row whose value is to be returned
     * @param column - column whose value is to be returned
     */
    public String get(int row, int column) {
        return board[row][column];
    }

    /**
     * Sets the value of the board at the specified row and column
     * @param row - row whose value is to be returned
     * @param column - column whose value is to be returned
     */
    public void set(int row, int column, String id) {
        board[row][column] = id;
    }

    /**
     * Returns a string representing the current state of the game
     * @return the game string
     */
    private String gameString() {
        String gameString = "";
        for (int row = 0; row <= 2; row++) {
            int column = row;
            gameString += buildRowString(row) +
                    "-" + buildColumnString(column) + "-";
        }
        gameString += buildDiagonalString();
        return gameString;
    }

    /**
     * Builds the strings for each board row and determines if a winning move
     * has been played
     * @param row - row to be investigated
     * @return string formed from row columns
     */
    private String buildRowString(int row) {
        String rowString = "";
        for (int column = 0; column <= 2; column++) {
            rowString += board[row][column];
        }
        return rowString;
    }

    /**
     * Builds the strings for each board column and determines if a winning move
     * has been played
     * @param column - column to be investigated
     * @return string formed from board columns
     */
    private String buildColumnString(int column) {
        String columnString = "";
        for (int row = 0; row <= 2; row++) {
            columnString += board[row][column];
        }
        return columnString;
    }

    /**
     * Builds diagonal string
     * @return diagonalString
     */
    private String buildDiagonalString() {
        String diagonalString = "";
        diagonalString += board[0][0] + board[1][1] + board[2][2] + "-";
        diagonalString += board[2][0] + board[1][1] + board[0][2];
        return diagonalString;
    }

    /**
     * Counts the number of unPlayedMoves (empty spaces) in the board
     * @return number of unplayed moves
     */
    public int unplayedMoves() {
        int numUnplayedMoves = 0;
        for (int row = 0; row <= 2; row++) {
            for (int column = 0; column <= 2; column++) {
                if (board[row][column] == "-") {
                    numUnplayedMoves += 1;
                }
            }
        }
        return numUnplayedMoves;
    }

    /**
     * Determines whether or not the game is over by checking rows, columns,
     * and diagonals
     * @return - true if game is over, false otherwise
     */
    public boolean gameOver() {
        return userWon() || aiWon() || tieGame();
    }

    /**
     * Checks if a tie-game has been achieved
     * @return true if a time game, false otherwise
     */
    public boolean tieGame() {
        String tieString = "";
        for (int row = 0; row <= 2; row++) {
            for (int col = 0; col <= 2; col++) {
                tieString += board[row][col];
            }
        }
        return !tieString.contains("-");
    }
}
