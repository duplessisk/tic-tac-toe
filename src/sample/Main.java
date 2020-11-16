package sample;

import sample.CustomShapes.*;
import backend.AccessBoard;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

// C:\Program Files\javafx-sdk-15.0.1\lib\src.zip\javafx.graphics\javafx

/**
 Driver class for the tic tac toe game
 */

public class Main extends Application {

    @Override
    /**
     * Main method for UI Application
     * @window - Stage where all UI-based scenes are set
     */
    public void start(Stage window) {
        AccessBoard board = new AccessBoard();
        Group root = new Group();
        Board.drawBoard(root);
        Scene gameScene = new Scene(root,400,400,Color.BLACK);
        Button newGameButton = createButton("New Game",112,340);
        Button endGameButton = createButton("End Game",210,340);

        gameScene.setOnMousePressed(e -> {
            boolean validUserTurn = userTurn(e, root, board);
            aiTurn(root, board, validUserTurn);
            endGame(board, root, newGameButton, endGameButton);
        });
        newGameButton.setOnMouseClicked(event ->
                restartGame(root, window, gameScene,board));
        endGameButton.setOnMouseClicked(event -> window.close());

        setStage(window,gameScene);
    }

    /**
     * Initializes a button
     * @param label - button label
     * @param x - button x position
     * @param y - button y position
     * @return the initialized button
     */
    public static Button createButton(String label, int x, int y) {
        Button button = new Button(label);
        button.setLayoutX(x);
        button.setLayoutY(y);
        return button;
    }

    /**
     * Initializes the window
     * @param window - window to be initialized
     * @param gameScene - scene set in window
     */
    public static void setStage(Stage window, Scene gameScene) {
        window.setTitle("Tic Tac Toe");
        window.setScene(gameScene);
        window.show();
    }

    /**
     * Determines whether the game is over, and initializes the appropriate
     * options if it is
     * @param board - board being modified in the backend
     * @param root - children are UI objects
     * @param newGameButton - Button that starts a new game
     * @param endGameButton - Button that closes the window
     */
    public static void endGame(AccessBoard board, Group root,
                                Button newGameButton, Button endGameButton) {
        if (board.gameOver()) {
            Text message = new Text(board.reportWinner());
            message.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
            message.setLayoutX(145);
            message.setLayoutY(65);
            message.setFill(Color.LIGHTBLUE);
            root.getChildren().addAll(message, newGameButton, endGameButton);
        }
    }

    /**
     * obtains user turn and makes the appropriate move
     * @param e - mouse event
     * @param root - children are UI objects
     * @param board - board being modified in the backend
     * @return whether or not the user turn was valid
     */
    public static boolean userTurn(MouseEvent e, Group root,
                                    AccessBoard board) {
        double x = e.getX();
        double y = e.getY();
        int userRow = getRowCol(x);
        int userCol = getRowCol(y);
        if (validUserMove(x, y, userRow,userCol, board)) {
            Cross.drawCross(root,x,y);
            board.setUserMove(userRow, userCol);
            return true;
        } else {
            AlertBox.display("Error","Please select a valid move");
            return false;
        }
    }

    /**
     * Determines whether or not the intended user move is a valid move. The
     * user move is valid if the following conditions have been met:
     * the intended move hasn't been played previously
     * the intended move is on the board
     * the game isn't over
     * @param x - button x position
     * @param y - button y position
     * @param userRow - board row user intends to play
     * @param userCol - board column user intends to play
     * @param board - board being modified in the backend
     * @return true if the user move is valid, false otherwise
     */
    public static boolean validUserMove(double x, double y, int userRow,
                                         int userCol, AccessBoard board) {
        return x >= 80 && x <= 320 && y >= 80 && y <= 320 &&
                !board.gameOver() &&
                moveNotPlayed(board,userRow,userCol);
    }

    /**
     * Determines whether or not the users' intended move has been played
     * @param board - board being modified in the backend
     * @param userRow - board row user intends to play
     * @param userCol - board column user intends to play
     * @return true if the intended user move hasn't been played, false
     * otherwise
     */
    public static boolean moveNotPlayed(AccessBoard board,
                                         int userRow, int userCol) {
        return board.getBoard()[userRow][userCol].equals("-");
    }

    /**
     * Plays a single round (1 player move, 1 ai move)
     * @param root - Group that the cross object will be added to
     * @param board - AccessBoard object that interacts with the backend
     *                   code
     * @param validUserTurn - true if the player selected an empty square on
     *                      the gameboard, false otherwise
     */
    public static void aiTurn(Group root, AccessBoard board,
                               Boolean validUserTurn) {
        if (!board.gameOver() && validUserTurn) {
            String aiString = board.getAiMove();
            Nought.drawNought(root,aiString);
        }
    }

    /**
     * Restarts the game
     * @param root - Group that the cross object will be added to
     * @param window - stage where all the scenes are set
     * @param gameScene - scene containing the UI components associated with
     *                  the gameplay
     * @param board - AccessBoard object that interacts with the backend
     *                   code
     */
    public static void restartGame(Group root, Stage window,
                                    Scene gameScene, AccessBoard board) {
        root.getChildren().clear();
        Board.drawBoard(root);
        window.setScene(gameScene);
        board.restartGame();
    }

    /**
     * Returns the column/row associated with the users' move
     * @param userCoord - coordinate of the user's click
     * @return the column/row associated with the coordinate of the users' click
     */
    public static int getRowCol(double userCoord) {
        if (userCoord >= 80 && userCoord < 160) {
            return 0;
        } else if (userCoord >= 160 && userCoord < 240) {
            return 1;
        } else {
            return 2;
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
