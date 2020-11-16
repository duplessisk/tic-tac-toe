package backend;

import backend.Player.AI;
import backend.Player.User;

/**
 * This class instantiates an AccessBoard object that serves as the link
 * between the backend (which records game progress) and the UI front-end.
 */
public class AccessBoard {

    Board board;
    GameTree gameTree;

    /**
     * Constructs the Access Board object
     */
    public AccessBoard() {
        board = new Board();
        gameTree = new GameTree(board);
    }

    /**
     * Returns the current state of the board
     * @return the current state of the board
     */
    public String[][] getBoard() {
        return board.getBoard();
    }

    /**
     * Uses the user's move to update the current board
     * @param userRow - row that the user played
     * @param userColumn - column that the user played
     */
    public void setUserMove(int userRow, int userColumn) {
        User.move(userRow, userColumn, board, gameTree);
    }

    /**
     * Returns a string that represents the ai's move. Specifically, the string
     * contains the ai's row and column
     * @return a string that represents the ai's move
     */
    public String getAiMove() {
        return AI.move(board, gameTree);
    }

    /**
     * Returns whether or not the game is over
     * @return true if the game is over, false otherwise
     */
    public boolean gameOver() {
        return board.gameOver();
    }

    /**
     * Returns a message reporting whether the ai won, or the game ended in a
     * tie. Observe that a user win isn't reported. This is because the ai is
     * unbeatable.
     * @return a message reporting whether the ai won, or the game ended in a
     * tie
     */
    public String reportWinner() {
        if (board.aiWon()) {
            return "AI Wins!";
        } else {
            return "Tie Game!";
        }
    }

    /**
     * Restarts the game
     */
    public void restartGame() {
        board = new Board();
        gameTree = new GameTree(board);
    }
}

