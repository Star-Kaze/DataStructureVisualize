package graphics.collections.dynamic.array;

import graphics.collections.Collection;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

public class DynamicArrayDisplay implements Collection, DynamicArray {
    private static final double X = 60;
    private static final double Y = 60;
    private static final double WIDTH = 70;
    private static final int MAX_CAPACITY = 20;
    public static boolean Existed = false;

    private ArrayList<StackPane> elements;
    private Pane parent;
    private int capacity;
    private int size;

    public DynamicArrayDisplay(Pane parent) {
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

    public void removeValue(int index) {
        Label label = (Label) this.elements.get(index).getChildren().get(1);
        label.setText("");
    }
    @Override
    public void draw() {
        int order = this.elements.size();

        // Create square
        Rectangle square = new Rectangle(WIDTH, WIDTH);
        square.setStroke(Color.BLACK);
        square.setFill(Color.TRANSPARENT);

        // Create label
        Label label = new Label();
        label.setStyle("-fx-font-size: " + (WIDTH / 3) + "px; "
                + "-fx-text-fill: black; "
                + "-fx-alignment: center;");

        // Create container
        StackPane container = new StackPane();
        container.setLayoutX(X + WIDTH * order);
        container.setLayoutY(Y);
        container.getChildren().addAll(square, label);

        // Add container to parent
        this.parent.getChildren().add(container);
        this.elements.add(container);
    }

    @Override
    public void create(ArrayList<Integer> elements) {
        this.size = elements.size();
        this.capacity = Math.max(this.size, 10);

        for (int i = 0; i < this.capacity; i++) {
            this.draw();
        }

        for (int i = 0; i < this.size; i++) {
            this.setValue(i, elements.get(i));
        }
        Existed = true;
    }

    @Override
    public void insert(int index, int value) {
        if (index > this.size) {
            //handle
            return;
        }
        if (this.size == 0) {
            this.setValue(0, value);
            this.size++;
            return;
        }
        if (this.size == MAX_CAPACITY) {
            return;
        } else if (this.size == this.capacity) {
            this.capacity = Math.min(this.capacity * 2, MAX_CAPACITY);
            for (int i = 0; i < this.capacity - this.size; i++) {
                this.draw();
            }
        }
        for (int i = this.size - 1; i >= index; i--) {
            this.setValue(i + 1, this.getValue(i));
            this.removeValue(i);
        }
        this.setValue(index, value);
        this.size++;
    }

    @Override
    public void append(int value) {
        this.insert(this.size, value);
    }

    @Override
    public void remove(int index) {
        if (index >= this.size) {
            return;
        }
        if (size == 0) {
            return;
        }
        if (size == 1) {
            this.removeValue(0);
            this.size--;
            return;
        }
        this.removeValue(index);
        for (int i = index; i < size - 1; i++) {
            this.setValue(i, this.getValue(i + 1));
            this.removeValue(i + 1);
        }
        this.size--;
    }

    @Override
    public void update(int index, int value) {
        if (index >= this.size) {
            //handle
            return;
        }
        this.setValue(index, value);
    }

    @Override
    public void changeColor(int index, Color color) {
        Label label = (Label) this.elements.get(index).getChildren().get(1);
        label.setTextFill(color);
    }
}