package sample.CustomShapes;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 This class represents ai moves in the UI
 */
public class Nought {

    /**
     * Draws a "Nought" to the scene
     * @param root - children are UI objects
     * @param aiString - string giving the row and column of the ai's move
     */
    public static void drawNought(Group root, String aiString) {
        int aiRow = (int) aiString.charAt(0) - '0';
        int aiCol = (int) aiString.charAt(1) - '0';
        int x = getCoord(aiRow);
        int y = getCoord(aiCol);
        root.getChildren().addAll(new Circle(x, y, 15,
                Color.RED), new Circle(x, y, 12, Color.BLACK));
    }

    /**
     * returns the coordinate of the move
     * @param coord - coordinate of move
     * @return the coordinate of the movee
     */
    private static int getCoord(int coord) {
        if (coord == 0) {
            return 120;
        } else if (coord == 1) {
            return 200;
        } else {
            return 280;
        }
    }
}
