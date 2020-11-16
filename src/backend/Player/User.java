package backend.Player;

import backend.Board;
import backend.GameTree;

/**
 * This class represents a user that is able to play against the ai
 */
public class User {

    /**
     * Updates the board with the player's move
     * @param userRow - row that the user played
     * @param userColumn - column that the user played
     * @param board - current game board
     * @param gameTree - decision tree representing the entire state space of
     *                the current game.
     */
    public static void move(int userRow, int userColumn, Board board,
                     GameTree gameTree) {
        board.set(userRow,userColumn,"X");
        int userMoveIndex = getUserMoveIndex(board,userRow,userColumn);
        gameTree.updateCurrentBoardLocation(userMoveIndex);
    }

    /**
     * Returns the appropriate index in the current gameTree node based on
     * the users move
     * @param board - current game board
     * @param userRow - row that the user played
     * @param userColumn - column that the user played
     * @return the index associated with the nextNode in the gameTree
     */
    private static int getUserMoveIndex(Board board, int userRow,
                                        int userColumn) {
        int userMoveIndex = 0;
        for (int row = 0; row <= 2; row++) {
            for (int column = 0; column <= 2; column++) {
                if (row <= userRow && board.get(row,column) == "-") {
                    if (row == userRow) {
                        if ((column < userColumn)) {
                            userMoveIndex += 1;
                        }
                    } else {
                        userMoveIndex += 1;
                    }
                }
            }
        }
        return userMoveIndex;
    }
}
