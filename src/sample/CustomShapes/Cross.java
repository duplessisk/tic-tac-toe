package sample.CustomShapes;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 This class represent the user's moves in the UI
 */
public class Cross {

    /**
     * Draws the cross to the scene
     * @param root - children are UI objects
     */
    public static void drawCross(Group root, double x, double y) {
        x = adjustCross(x);
        y = adjustCross(y);
        Line leftCross = new Line(x+10, y+10, x-10, y-10);
        setLineProperty(leftCross, 5);
        Line rightCross = new Line(x-10, y+10, x+10, y-10);
        setLineProperty(rightCross, 5);
        root.getChildren().addAll(leftCross,rightCross);
    }

    /**
     * Adjusts location of users' cross
     * @param coord - coordinate of the users' click
     * @return coordinate of cross
     */
    private static int adjustCross(double coord) {
        if (coord >= 80 && coord < 160) {
            return 120;
        } else if (coord >= 160 && coord < 240) {
            return 200;
        } else {
            return 280;
        }
    }

    /**
     * Sets the color and width of each line composing the game board
     * @param line - line whose properties are to be modified
     * @param strokeWidth - width of the line
     */
    private static void setLineProperty(Line line, int strokeWidth) {
        line.setStroke(Color.GREEN);
        line.setStrokeWidth(strokeWidth);
    }
}
