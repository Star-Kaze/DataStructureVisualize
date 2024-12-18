package graphics.collections.stack;

import graphics.collections.Collection;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.time.chrono.MinguoDate;
import java.util.ArrayList;

public class StackDisplay implements Collection, Stack {
    private static final double X = 700;
    private static final double Y = 500;
    private static final double WIDTH = 40;
    private static final int MAX_CAPACITY = 10;
    public static boolean Existed = false;

    private ArrayList<StackPane> elements;
    private Pane parent;
    private int size;

    public StackDisplay(Pane parent) {
        this.elements = new ArrayList<>();
        this.parent = parent;
    }

    public void setValue(int index, int value) {
        Label label = (Label) this.elements.get(index).getChildren().get(1);
        label.setText(Integer.toString(value));
    }

    public int getValue(int index) {
        Label label = (Label) this.elements.get(index).getChildren().get(1);
        return Integer.parseInt(label.getText());
    }

    @Override
    public void create(ArrayList<Integer> elements) {
        this.size = 0;

        if (Existed) {
            int index = this.size;
            for (int i = 0; i < index; i++) {
                this.pop();
            }
        } else {
            // Draw lines
            Line leftLine = new Line(X, Y, X, Y - (WIDTH * MAX_CAPACITY) - (WIDTH / 2));
            leftLine.setStroke(Color.BLACK);

            Line rightLine = new Line(X + WIDTH * 2, Y, X + WIDTH * 2, Y - (WIDTH * MAX_CAPACITY) - (WIDTH / 2));
            rightLine.setStroke(Color.BLACK);

            Line bottomLine = new Line(X, Y, X + WIDTH * 2, Y);
            bottomLine.setStroke(Color.BLACK);

            this.parent.getChildren().addAll(leftLine, rightLine, bottomLine);
            Existed = true;
        }

        for (int i = 0; i < elements.size(); i++) {
            this.push(elements.get(i));
        }
    }

    @Override
    public void draw() {
        int order = this.size;

        // Draw rectangle
        Rectangle rectangle = new Rectangle(2 * WIDTH, WIDTH);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.TRANSPARENT);

        // Create label
        Label label = new Label();
        label.setStyle("-fx-font-size: " + (WIDTH / 3) + "px; "
                + "-fx-text-fill: black; "
                + "-fx-alignment: center;");

        // Create container
        StackPane container = new StackPane();
        container.setLayoutX(X);
        container.setLayoutY(Y - WIDTH - (WIDTH * order));
        container.getChildren().addAll(rectangle, label);

        // Add container to parent
        this.parent.getChildren().add(container);
        this.elements.add(container);
    }

    @Override
    public void push(int value) {
        if (this.size == MAX_CAPACITY) {
            return;
        }
        this.draw();
        this.setValue(this.size, value);
        this.size++;
    }

    @Override
    public void pop() {
        if (this.size == 0) {
            return;
        }
        int index = this.size - 1;
        this.parent.getChildren().remove(this.elements.get(index));
        this.elements.remove(index);
        this.size--;
    }

    @Override
    public void peek() {
        int index = this.size - 1;
        Label label = (Label) this.elements.get(index).getChildren().get(1);
        label.setTextFill(Color.RED);
    }
}
