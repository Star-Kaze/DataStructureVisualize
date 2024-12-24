package graphics.collections.stack;

import data.structures.Stack;
import graphics.collections.Collection;
import graphics.graphical.elements.GraphicalNumber;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import java.util.ArrayList;

public class StackDisplay implements Collection, AbstractStack {
    private static final double X = 700;
    private static final double Y = 700;
    private static final double WIDTH = 60;
    private static final int MAX_CAPACITY = 10;
    public static boolean Existed = false;

    private Stack<GraphicalNumber> elements;
    private Pane parent;

    public StackDisplay(Pane parent) {
        this.elements = new Stack<GraphicalNumber>(new ArrayList<GraphicalNumber>());
        this.parent = parent;
    }

    @Override
    public void create(ArrayList<Integer> elements) {

        if (Existed) {
            int index = this.elements.getSize();
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

//        // Draw rectangle
//        Rectangle rectangle = new Rectangle(2 * WIDTH, WIDTH);
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
//        container.setLayoutX(X);
//        container.setLayoutY(Y - WIDTH - (WIDTH * order));
//        container.getChildren().addAll(rectangle, label);
//
//        // Add container to parent
//        this.parent.getChildren().add(container);
//        this.elements.add(container);
        int order = this.elements.getSize();
        GraphicalNumber element = new GraphicalNumber(X, Y - WIDTH - WIDTH * order, 2 * WIDTH, WIDTH);

        this.parent.getChildren().add(element.getContainer());
        this.elements.push(element);
    }

    @Override
    public void push(int value) {
        if (this.elements.getSize() != 0) {
            this.changeColor(this.elements.getSize() - 1, Color.BLACK);
        }
        if (this.elements.getSize() == MAX_CAPACITY) {
            return;
        }
        this.draw();
        this.elements.peek().setValue(value);
    }

    @Override
    public void pop() {
        if (this.elements.getSize() == 0) {
            return;
        }
        this.changeColor(this.elements.getSize() - 1, Color.BLACK);
        this.parent.getChildren().remove(this.elements.pop().getContainer());
    }

    @Override
    public void peek() {
        if (this.elements.getSize() == 0) {
            return;
        }
        this.changeColor(this.elements.getSize() - 1, Color.RED);
    }

    @Override
    public void changeColor(int index, Color color) {
        this.elements.toList().get(index).getLabel().setTextFill(color);
    }
}
