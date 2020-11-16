package sample.CustomShapes;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 This class represents the board in the tic tac toe UI
 */
public class Board {

    /**
     * Draws a tic tac toe board
     * @param root - children are UI objects
     */
    public static void drawBoard(Group root) {
        Line line1 = drawLine(160,80,160,320);
        Line line2 = drawLine(240,80,240,320);
        Line line3 = drawLine(80,160,320,160);
        Line line4 = drawLine(80,240,320,240);
        root.getChildren().addAll(line1,line2,line3,line4);
    }

    /**
     * Returns each line (element) composing the game board
     * @param startX - The starting x-coordinate for the line
     * @param startY - The starting y-coordinate for the line
     * @param endX - The ending x-coordinate for the line
     * @param endY - The ending y-coordinate for the line
     * @return Line composing the game board
     */
    private static Line drawLine(int startX, int startY, int endX, int endY) {
        Line line = new Line(startX,startY,endX,endY);
        line.setStroke(Color.GREEN);
        line.setStrokeWidth(2);
        return line;
    }
}
