package graphics.graphical.elements;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GraphicalNumber {
    private Label label;
    private Rectangle frame;
    private StackPane container;

    public GraphicalNumber(float x, float y, float width, float height) {
        // Create frame
        this.frame = new Rectangle(width, height);
        this.frame.setFill(Color.TRANSPARENT);
        this.frame.setStroke(Color.BLACK);

        // Create label
        this.label = new Label();
        this.label.setStyle("-fx-font-size: " + (height / 3) + "px; "
                + "-fx-text-fill: black; "
                + "-fx-alignment: center;");

        // Create container
        this.container = new StackPane();
        this.container.setLayoutX(x);
        this.container.setLayoutY(y);
        this.container.getChildren().addAll(this.frame, this.label);
    }

    public Label getLabel() {
        return label;
    }

    public Rectangle getFrame() {
        return frame;
    }

    public StackPane getContainer() {
        return container;
    }

    public void setValue(int value) {
        this.label.setText(Integer.toString(value));
    }

    public int getValue() {
        return Integer.parseInt(this.label.getText());
    }
}
