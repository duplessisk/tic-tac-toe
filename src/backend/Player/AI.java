package backend.Player;

import backend.*;

/**
 * This class represents an ai that uses a min-max algorithm to play tic
 * tac toe against a user
 */
public class AI {

    /**
     * Updates the board with the ai's move
     * @param board - current game board
     * @param gameTree - decision tree representing the entire state space of
     *                the current game.
     */
    public static String move(Board board, GameTree gameTree) {
        int aiMoveIndex = gameTree.minChildIndex();
        String moveString = placeMove(board,aiMoveIndex);
        gameTree.updateCurrentBoardLocation(aiMoveIndex);
        return moveString;
    }

    /**
     * Uses the aiMoveIndex to make the appropriate move on the board
     * @param board - current game board
     * @param aiMoveIndex - index in the current node in gameTree associated
     *                    with the ai's move
     * @return string representing the ai column and row moves
     */
    private static String placeMove(Board board, int aiMoveIndex) {
        int boardIndex = 0;
        for (int row = 0; row <= 2; row++) {
            for (int column = 0; column <= 2; column++) {
                if (board.get(row,column) == "-") {
                    if (boardIndex == aiMoveIndex) {
                        board.set(row,column,"O");
                        return Integer.toString(row) + Integer.toString(column);
                    } else {
                        boardIndex += 1;
                    }
                }
            }
        }
        return "NA";
    }
}
