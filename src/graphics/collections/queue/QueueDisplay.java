package graphics.collections.queue;

import data.structures.Queue;
import graphics.animations.AppearAnimation;
import graphics.animations.SelectionAnimation;
import graphics.collections.Collection;
import graphics.graphical.elements.GraphicalNumber;
import javafx.animation.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import java.util.ArrayList;

public class QueueDisplay implements Collection, AbstractQueue {
    private static final double X = 250;
    private static final double Y = 200;
    private static final double WIDTH = 100;
    private static final int MAX_CAPACITY = 10;
    public static boolean Existed = false;

    private Queue<GraphicalNumber> elements;
    private Pane parent;
    private GraphicalNumber[] lastPeek = new GraphicalNumber[2];

    public QueueDisplay(Pane parent) {
        this.elements = new Queue<GraphicalNumber>(new ArrayList<GraphicalNumber>());
        this.parent = parent;
    }

    @Override
    public void create(ArrayList<Integer> elements) {
        if (this.lastPeek[0] != null) {
            this.lastPeek[0] = null;
        }

        if (this.lastPeek[1] != null) {
            this.lastPeek[1] = null;
        }

        int index = this.elements.getSize();
        if (Existed) {
            this.dequeue(index);
        } else {
            // Draw lines
            Line topLine = new Line(X - (WIDTH / 2), Y, X + WIDTH * MAX_CAPACITY + WIDTH / 2, Y);
            topLine.setStroke(Color.BLACK);

            Line bottomLine = new Line(X - (WIDTH / 2), Y + WIDTH, X + WIDTH * MAX_CAPACITY + WIDTH / 2, Y + WIDTH);
            bottomLine.setStroke(Color.BLACK);

            this.parent.getChildren().addAll(topLine, bottomLine);
            Existed = true;
        }

        PauseTransition pause = new PauseTransition(Duration.seconds(0.5 * index));
        pause.setOnFinished(event -> {
            this.enqueue(elements);
        });
        pause.play();
    }

    @Override
    public void enqueue(ArrayList<Integer> values) {
        if (this.lastPeek[0] != null) {
            Timeline selected = SelectionAnimation.createUnselected(this.lastPeek[0], 0.1);
            selected.setOnFinished(event -> {
                this.lastPeek[0] = null;
            });
            selected.play();
        }

        if (this.lastPeek[1] != null) {
            Timeline selected = SelectionAnimation.createUnselected(this.lastPeek[1], 0.1);
            selected.setOnFinished(event -> {
                this.lastPeek[1] = null;
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
            GraphicalNumber element = new GraphicalNumber(X + WIDTH * (MAX_CAPACITY - 2) - order * WIDTH, Y, WIDTH, WIDTH);
            element.setValue(value);
            this.elements.enqueue(element);

            ParallelTransition appear = AppearAnimation.create(element, WIDTH, 0, 0.5);
            appearances.getChildren().add(appear);

            Timeline unselected = SelectionAnimation.createUnselected(element, 0.1);
            selections.getChildren().add(unselected);

            this.parent.getChildren().add(element.getContainer());
        }

        SequentialTransition animation = new SequentialTransition(appearances, selections);
        animation.play();
    }

    @Override
    public void dequeue(int k) {
        if (this.lastPeek[0] != null) {
            this.lastPeek[0] = null;
        }

        if (this.lastPeek[1] != null) {
            Timeline selected = SelectionAnimation.createUnselected(this.lastPeek[1], 0.1);
            selected.setOnFinished(event -> {
                this.lastPeek[1] = null;
            });
            selected.play();
        }

        if (this.elements.getSize() == 0) {
            return;
        }

        k = Math.min(k, this.elements.getSize());

        ParallelTransition selections = new ParallelTransition();
        SequentialTransition appearances = new SequentialTransition();

        for (int i = 0; i < k; i++) {
            GraphicalNumber element = this.elements.dequeue();
            ArrayList<GraphicalNumber> lst = this.elements.toList();

            ParallelTransition disappear = AppearAnimation.createDisappear(element, WIDTH, 0, 0.5);
            disappear.setOnFinished(event -> {
                this.parent.getChildren().remove(element.getContainer());
            });

            for (GraphicalNumber e:lst) {
                TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), e.getContainer());
                translateTransition.setByX(WIDTH);
                disappear.getChildren().add(translateTransition);
            }

            appearances.getChildren().add(disappear);

            Timeline selected = SelectionAnimation.create(element, 0.1);
            selections.getChildren().add(selected);
        }

        SequentialTransition animation = new SequentialTransition(selections, appearances);
        animation.play();
    }

    @Override
    public void peekFront() {
        if (this.lastPeek[1] != null) {
            Timeline selected = SelectionAnimation.createUnselected(this.lastPeek[1], 0.1);
            selected.setOnFinished(event -> {
                this.lastPeek[1] = null;
            });
            selected.play();
        }

        if (this.elements.getSize() == 0) {
            return;
        }

        GraphicalNumber element = this.elements.peekFront();
        Timeline selected = SelectionAnimation.create(element, 0.1);
        selected.setOnFinished(event -> {
            this.lastPeek[0] = element;
        });
        selected.play();
    }

    @Override
    public void peekBack() {
        if (this.lastPeek[0] != null) {
            Timeline selected = SelectionAnimation.createUnselected(this.lastPeek[0], 0.1);
            selected.setOnFinished(event -> {
                this.lastPeek[0] = null;
            });
            selected.play();
        }

        if (this.elements.getSize() == 0) {
            return;
        }

        GraphicalNumber element = this.elements.peekBack();
        Timeline selected = SelectionAnimation.create(element, 0.1);
        selected.setOnFinished(event -> {
            this.lastPeek[1] = element;
        });
        selected.play();
    }
}
