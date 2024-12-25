package graphics.collections.stack;

import data.structures.Stack;
import graphics.animations.AppearAnimation;
import graphics.animations.SelectionAnimation;
import graphics.collections.Collection;
import graphics.graphical.elements.GraphicalNumber;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import java.util.ArrayList;

public class StackDisplay implements Collection, AbstractStack {
    private static final double X = 700;
    private static final double Y = 700;
    private static final double WIDTH = 60;
    private static final int MAX_CAPACITY = 10;
    public static boolean Existed = false;

    private Stack<GraphicalNumber> elements;
    private Pane parent;
    private GraphicalNumber lastPeek = null;

    public StackDisplay(Pane parent) {
        this.elements = new Stack<GraphicalNumber>(new ArrayList<GraphicalNumber>());
        this.parent = parent;
    }

    @Override
    public void create(ArrayList<Integer> elements) {
        if (this.lastPeek != null) {
            this.lastPeek = null;
        }

        int index = this.elements.getSize();
        if (Existed) {
            this.pop(index);
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
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5 * index));
        pause.setOnFinished(event -> {
            this.push(elements);
        });
        pause.play();
    }

    @Override
    public void push(ArrayList<Integer> values) {
        if (this.lastPeek != null) {
            Timeline selected = SelectionAnimation.createUnselected(this.lastPeek, 0.1);
            selected.setOnFinished(event -> {
                this.lastPeek = null;
            });
            selected.play();
        }

        if (this.elements.getSize() == MAX_CAPACITY) {
            return;
        }

        ParallelTransition selections = new ParallelTransition();
        SequentialTransition appearances = new SequentialTransition();

        for (int value:values) {
            int order = this.elements.getSize();
            if (order == MAX_CAPACITY) {
                continue;
            }
            GraphicalNumber element = new GraphicalNumber(X, Y - WIDTH * 2 - WIDTH * order, 2 * WIDTH, WIDTH);
            element.setValue(value);
            this.elements.push(element);

            ParallelTransition appear = AppearAnimation.create(element, 0, WIDTH, 0.5);
            appearances.getChildren().add(appear);

            Timeline unselected = SelectionAnimation.createUnselected(element, 0.1);
            selections.getChildren().add(unselected);

            this.parent.getChildren().add(element.getContainer());
        }

        SequentialTransition animation = new SequentialTransition(appearances, selections);
        animation.play();
    }

    @Override
    public void pop(int k) {
        if (this.lastPeek != null) {
            this.lastPeek = null;
        }

        if (this.elements.getSize() == 0) {
            return;
        }

        k = Math.min(k, this.elements.getSize());

        ParallelTransition selections = new ParallelTransition();
        SequentialTransition appearances = new SequentialTransition();

            for (int i = 0; i < k; i++) {
                GraphicalNumber element = this.elements.pop();

                ParallelTransition disappear = AppearAnimation.createDisappear(element, 0, -WIDTH, 0.5);
                disappear.setOnFinished(event -> {
                    this.parent.getChildren().remove(element.getContainer());
                });
                appearances.getChildren().add(disappear);

                Timeline selected = SelectionAnimation.create(element, 0.1);
                selections.getChildren().add(selected);
            }

        SequentialTransition animation = new SequentialTransition(selections, appearances);
        animation.play();
    }

    @Override
    public void peek() {
        if (this.elements.getSize() == 0) {
            return;
        }

        GraphicalNumber element = this.elements.peek();
        Timeline selected = SelectionAnimation.create(element, 0.1);
        selected.setOnFinished(event -> {
            this.lastPeek = element;
        });
        selected.play();
    }
}
