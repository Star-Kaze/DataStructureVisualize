package graphics.collections.queue;

import graphics.collections.Collection;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class QueueDisplay implements Collection, Queue {
    private static final double X = 250;
    private static final double Y = 200;
    private static final double WIDTH = 100;
    private static final int MAX_CAPACITY = 10;
    public static boolean Existed = false;

    private ArrayList<StackPane> elements;
    private Pane parent;
    private int size;

    public QueueDisplay(Pane parent) {
        this.elements = new ArrayList<>();
        this.parent = parent;
    }

    @Override
    public void setValue(int index, int value) {
        Label label = (Label) this.elements.get(index).getChildren().get(1);
        label.setText(Integer.toString(value));
    }

    @Override
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
                this.dequeue();
            }
        } else {
            // Draw lines
            Line topLine = new Line(X - (WIDTH / 2), Y, X + WIDTH * MAX_CAPACITY + WIDTH / 2, Y);
            topLine.setStroke(Color.BLACK);

            Line bottomLine = new Line(X - (WIDTH / 2), Y + WIDTH, X + WIDTH * MAX_CAPACITY + WIDTH / 2, Y + WIDTH);
            bottomLine.setStroke(Color.BLACK);

            this.parent.getChildren().addAll(topLine, bottomLine);
            Existed = true;
        }

        for (int i = 0; i < elements.size(); i++) {
            this.enqueue(elements.get(i));
        }
    }

    @Override
    public void draw() {
        int order = this.size;

        // Draw rectangle
        Rectangle rectangle = new Rectangle(WIDTH, WIDTH);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.TRANSPARENT);

        // Create label
        Label label = new Label();
        label.setStyle("-fx-font-size: " + (WIDTH / 3) + "px; "
                + "-fx-text-fill: black; "
                + "-fx-alignment: center;");

        // Create container
        StackPane container = new StackPane();
        container.setLayoutX(X + WIDTH * (MAX_CAPACITY - 1) - order * WIDTH);
        container.setLayoutY(Y);
        container.getChildren().addAll(rectangle, label);

        // Add container to parent
        this.parent.getChildren().add(container);
        this.elements.add(container);
    }

    @Override
    public void enqueue(int value) {
        if (this.size != 0) {
            this.changeColor(this.size - 1, Color.BLACK);
            this.changeColor(0, Color.BLACK);
        }
        if (this.size == MAX_CAPACITY) {
            return;
        }
        this.draw();
        this.setValue(this.size, value);
        this.size++;
    }

    @Override
    public void dequeue() {
        if (this.size == 0) {
            return;
        }
        int index = this.size - 1;
        this.changeColor(index, Color.BLACK);
        this.changeColor(0, Color.BLACK);
        for (int i = 0; i < index; i++){
            this.setValue(i, this.getValue(i + 1));
        }
        this.parent.getChildren().remove(this.elements.get(index));
        this.elements.remove(index);
        this.size--;
    }

    @Override
    public void peekFront() {
        if (this.size == 0) {
            return;
        }
        this.changeColor(0, Color.BLACK);
    }

    @Override
    public void peekBack() {
        if (this.size == 0) {
            return;
        }
        this.changeColor(this.size - 1, Color.BLACK);
    }

    @Override
    public void changeColor(int index, Color color) {
        Label label = (Label) this.elements.get(index).getChildren().get(1);
        label.setTextFill(color);
    }
}
