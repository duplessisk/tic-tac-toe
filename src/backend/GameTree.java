package backend;

import java.util.*;
import java.lang.*;

/**
 * This class generates a GameTree object that contains all possible moves
 * in the tic tac toe game in order to assist the ai
 */
public class GameTree {
    private GameTreeNode root;
    private GameTreeNode boardLocation;
    private int move;

    /**
     * Initializes the GameTree object
     * @param board - current game board
     */
    public GameTree(Board board) {
        move = 0;
        root = new GameTreeNode(0, board.unplayedMoves());
        root = buildGameTree(root, board);
        boardLocation = root;
    }

    /**
     * Builds GameTree object whose nodes contain all possible game states
     * @param currentNode - current node board state whose score and
     *                    children are being assigned
     * @param board - current game board
     * @return the current node
     */
    private GameTreeNode buildGameTree(GameTreeNode currentNode, Board board) {
        if (board.gameOver()) {
            currentNode = terminalNodeScore(currentNode, board,
                    board.unplayedMoves());
        } else {
            List<Integer> childrenScores = new ArrayList<>();
            int childIndex = -1;
            for (int row = 0; row <= 2; row++) {
                for (int column = 0; column <= 2; column++) {
                    if (board.get(row,column) == "-") {
                        childIndex += 1;
                        makeMove(board,row,column);
                        currentNode.children[childIndex] =
                                buildGameTree(new GameTreeNode(0,
                                        board.unplayedMoves()),board);
                        childrenScores.add(currentNode.children[childIndex].score);
                        undoMove(board,row,column);
                    }
                }
            }
            currentNode = branchNodeScore(currentNode,childrenScores);
        }
        return currentNode;
    }

    /**
     * Assigns a score for a terminal node, depending on whether the user
     * won (positive score) or ai won (negative score)
     * @param terminalNode - node whose score needs to be assigned
     * @param board - current game board
     * @param numUnplayedMoves - number of moves not played in the current game
     * @return current node
     */
    private GameTreeNode terminalNodeScore (GameTreeNode terminalNode,
                                            Board board, int numUnplayedMoves) {
        if (board.aiWon()) { // ai won
            terminalNode.score = -1*(numUnplayedMoves + 1);
        } else if (board.userWon()) { // ai lost
            terminalNode.score = numUnplayedMoves + 1;
        } else { // draw
            terminalNode.score = 0;
        }
        return terminalNode;
    }

    /**
     * Sets the branch node score so that the ai can minimize the maximum
     * score the user can achieve
     * @param branchNode - node whose score needs to be assigned
     * @param childrenScores - scores of all branchNode's children
     * @return the branch node
     */
    private GameTreeNode branchNodeScore (GameTreeNode branchNode,
                                      List<Integer> childrenScores) {
        if (move%2 == 0) {
            branchNode.score = Collections.max(childrenScores);
        } else {
            branchNode.score = Collections.min(childrenScores);
        }
        return branchNode;
    }

    /**
     * Simulates a user or ai move
     * @param board - current game board
     * @param row - row to be updated
     * @param column - column to be updated
     */
    private void makeMove(Board board, int row, int column) {
        move += 1;
        if (move%2 == 0) {
            board.set(row,column,"O");
        } else {
            board.set(row,column,"X");
        }
    }

    /**
     * Undoes the user or ai move to allow for backtracking
     * @param board - current game board
     * @param row - row to be updated
     * @param column - column to be updated
     */
    private void undoMove(Board board, int row, int column) {
        board.set(row,column,"-"); // undo choice
        move -= 1;
    }

    /**
     * Moves boardLocation to the current location in gameTree. Allows for
     * the ai to keep track of the state of the board
     * @param playerMoveIndex - index in gameTree node associated with the
     *                       player's move
     */
    public void updateCurrentBoardLocation(int playerMoveIndex) {
        boardLocation = boardLocation.children[playerMoveIndex];
    }

    /**
     * Returns the index associated with the child who contains the lowest
     * score of all the children. This index corresponds with the optimal move
     * for the ai
     * @return the index associated with the optimal move for the ai
     */
    public int minChildIndex() {
        int maxChildScore = Integer.MAX_VALUE;
        int aiMoveIndex = 0;
        for (int i = 0; i < boardLocation.children.length; i++) {
            if (boardLocation.children[i].score < maxChildScore) {
                maxChildScore = boardLocation.children[i].score;
                aiMoveIndex = i;
            }
        }
        return aiMoveIndex;
    }

    /**
     * This class generates a GameTreeNode object that serves as the node for
     * the GameTree object
     */
    private class GameTreeNode {
        public int score;
        public GameTreeNode[] children;

        /**
         * Initializes GameTreeNode object
         * @param score - current node score. Score set by min-max algorithm
         * @param numChildren - number of children a specific node contains
         */
        public GameTreeNode(int score, int numChildren) {
            this.score = score;
            children = new GameTreeNode[numChildren];
        }
    }
}

