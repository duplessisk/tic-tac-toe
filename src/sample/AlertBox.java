package sample;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;

/**
 This class notifies the user when an invalid move has been played
 */
public class AlertBox {
    
    /**
     * Displays the AlertBox to the scene
     * @param title - title of the alert box
     * @param message - message that the alert box remain
     */
    public static void display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        Label label = new Label();
        label.setText(message);
        label.setLayoutX(30);
        label.setLayoutY(17);
        Button closeButton = new Button("Close Window");
        closeButton.setLayoutX(55);
        closeButton.setLayoutY(45);
        closeButton.setOnAction(e -> window.close());

        Group root = new Group();
        root.getChildren().addAll(label,closeButton);
        Scene scene = new Scene(root,200,100);
        window.setTitle("Error");
        window.setScene(scene);
        window.showAndWait();
    }
}
