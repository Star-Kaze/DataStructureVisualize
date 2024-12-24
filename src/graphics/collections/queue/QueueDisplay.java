package graphics.collections.queue;

import data.structures.Queue;
import graphics.collections.Collection;
import graphics.graphical.elements.GraphicalNumber;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

public class QueueDisplay implements Collection, AbstractQueue {
    private static final double X = 250;
    private static final double Y = 200;
    private static final double WIDTH = 100;
    private static final int MAX_CAPACITY = 10;
    public static boolean Existed = false;

    private Queue<GraphicalNumber> elements;
    private Pane parent;

    public QueueDisplay(Pane parent) {
        this.elements = new Queue<GraphicalNumber>(new ArrayList<GraphicalNumber>());
        this.parent = parent;
    }

    @Override
    public void create(ArrayList<Integer> elements) {
        if (Existed) {
            int index = this.elements.getSize();
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

//        // Draw rectangle
//        Rectangle rectangle = new Rectangle(WIDTH, WIDTH);
//        rectangle.setStroke(Color.BLACK);
//        rectangle.setFill(Color.TRANSPARENT);
//
//        // Create label
//        Label label = new Label();
//        label.setStyle("-fx-font-size: " + (WIDTH / 3) + "px; "
//                + "-fx-text-fill: black; "
//                + "-fx-alignment: center;");
//
//        // Create container
//        StackPane container = new StackPane();
//        container.setLayoutX(X + WIDTH * (MAX_CAPACITY - 1) - order * WIDTH);
//        container.setLayoutY(Y);
//        container.getChildren().addAll(rectangle, label);
//
//        // Add container to parent
//        this.parent.getChildren().add(container);
//        this.elements.add(container);
        int order = this.elements.getSize();
        GraphicalNumber element = new GraphicalNumber(X + WIDTH * (MAX_CAPACITY - 1) - order * WIDTH, Y, WIDTH, WIDTH);

        this.parent.getChildren().add(element.getContainer());
        this.elements.enqueue(element);
    }

    @Override
    public void enqueue(int value) {
        if (this.elements.getSize() != 0) {
            this.changeColor(this.elements.getSize() - 1, Color.BLACK);
            this.changeColor(0, Color.BLACK);
        }
        if (this.elements.getSize() == MAX_CAPACITY) {
            return;
        }
        this.draw();
        this.elements.peekBack().setValue(value);
    }

    @Override
    public void dequeue() {
        if (this.elements.getSize() == 0) {
            return;
        }
        this.changeColor(this.elements.getSize() - 1, Color.BLACK);
        this.changeColor(0, Color.BLACK);
        this.parent.getChildren().remove(this.elements.dequeue().getContainer());

        ArrayList<GraphicalNumber> lst = this.elements.toList();
        for (int i = 0; i < this.elements.getSize(); i++) {
            lst.get(i).getContainer().setLayoutX(X + WIDTH * (MAX_CAPACITY - 1) - i * WIDTH);
        }
    }

    @Override
    public void peekFront() {
        if (this.elements.getSize() == 0) {
            return;
        }
        this.changeColor(this.elements.getSize() - 1, Color.BLACK);
        this.changeColor(0, Color.RED);
    }

    @Override
    public void peekBack() {
        if (this.elements.getSize() == 0) {
            return;
        }
        this.changeColor(0, Color.BLACK);
        this.changeColor(this.elements.getSize() - 1, Color.RED);
    }

    @Override
    public void changeColor(int index, Color color) {
        this.elements.toList().get(index).getLabel().setTextFill(color);
    }
}
