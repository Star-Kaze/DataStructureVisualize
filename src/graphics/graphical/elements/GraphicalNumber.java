package graphics.graphical.elements;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GraphicalNumber implements Comparable<GraphicalNumber> {
    private Label label;
    private Rectangle frame;
    private StackPane container;

    public GraphicalNumber(double x, double y, double width, double height) {
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
        String text = this.label.getText();
        if (text.isEmpty()) {
            throw new NumberFormatException();
        }
        return Integer.parseInt(text);
    }

    @Override
    public int compareTo(GraphicalNumber other) {
        try {
            int value1 = this.getValue();
            int value2 = other.getValue();
            return value1 - value2;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
