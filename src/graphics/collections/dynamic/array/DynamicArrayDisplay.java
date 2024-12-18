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
        SequentialTransition sequentialTransition = new SequentialTransition();
        for (int i = this.size - 1; i >= index; i--) {
            sequentialTransition.getChildren().add(this.createShiftRightAnimation(i));
        }
        sequentialTransition.setOnFinished(event -> {
            this.setValue(index, value);
            this.size++;
        });
        sequentialTransition.play();
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

    private SequentialTransition createShiftRightAnimation(int i) {
        Rectangle rectangle1 = new Rectangle(WIDTH, WIDTH);
        rectangle1.setStroke(Color.GREEN);
        rectangle1.setStrokeWidth(2);
        rectangle1.setFill(Color.GREEN);

        Label label1 = new Label(Integer.toString(this.getValue(i)));
        label1.setStyle("-fx-font-size: " + (WIDTH / 3) + "px; "
                + "-fx-text-fill: white; "
                + "-fx-alignment: center;");

        StackPane container1 = new StackPane();
        container1.getChildren().addAll(rectangle1, label1);

        Rectangle rectangle2 = new Rectangle(WIDTH, WIDTH);
        rectangle2.setStrokeWidth(2);
        rectangle2.setStroke(Color.GREEN);
        rectangle2.setFill(Color.GREEN);

        Label label2 = new Label(Integer.toString(this.getValue(i)));
        label2.setStyle("-fx-font-size: " + (WIDTH / 3) + "px; "
                + "-fx-text-fill: white; "
                + "-fx-alignment: center;");
        label2.setOpacity(0);

        StackPane container2 = new StackPane();
        container2.getChildren().addAll(rectangle2, label2);

        HBox container = new HBox();
        container.setLayoutX(X + WIDTH * i);
        container.setLayoutY(Y);
        container.setOpacity(0);
        container.getChildren().addAll(container1, container2);
        this.parent.getChildren().add(container);

        FadeTransition fd1 = new FadeTransition(Duration.seconds(1), container);
        fd1.setFromValue(0);
        fd1.setToValue(1);

        FadeTransition fd2 = new FadeTransition(Duration.seconds(1), label1);
        fd2.setFromValue(1);
        fd2.setToValue(0);

        FadeTransition fd3 = new FadeTransition(Duration.seconds(1), label2);
        fd3.setFromValue(0);
        fd3.setToValue(1);

        FadeTransition fd4 = new FadeTransition(Duration.seconds(1), container);
        fd4.setFromValue(1);
        fd4.setToValue(0);

        ParallelTransition parallel = new ParallelTransition(fd2, fd3);

        SequentialTransition sequential = new SequentialTransition(fd1, parallel, fd4);

        sequential.setOnFinished(event -> {
            this.setValue(i + 1, this.getValue(i));
            this.removeValue(i);
            this.parent.getChildren().remove(container);
        });

        return sequential;
    }

    @Override
    public void changeColor(int index, Color color) {
        Label label = (Label) this.elements.get(index).getChildren().get(1);
        label.setTextFill(color);
    }
}