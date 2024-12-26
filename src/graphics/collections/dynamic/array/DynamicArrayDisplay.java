package graphics.collections.dynamic.array;

import data.structures.CustomCollections;
import data.structures.DynamicArray;
import graphics.animations.InsertAnimation;
import graphics.animations.SelectionAnimation;
import graphics.animations.ShiftAnimation;
import graphics.animations.SwapAnimation;
import graphics.collections.Collection;
import graphics.graphical.elements.GraphicalNumber;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import java.util.ArrayList;

public class DynamicArrayDisplay implements Collection, AbstractDynamicArray {
    private static final double X = 60;
    private static final double Y = 60;
    private static final double WIDTH = 70;
    private static final int MAX_CAPACITY = 20;
    private static boolean Freezing = false;
    public static boolean Existed = false;

    private DynamicArray<GraphicalNumber> elements;
    private Pane parent;
    private int capacity;
    private int size;
    private GraphicalNumber lastSelection = null;

    public DynamicArrayDisplay(Pane parent) {
        this.elements = CustomCollections.createDynamicArray(new ArrayList<GraphicalNumber>());
        this.parent = parent;
    }

    public void setValue(int index, int value) {
        this.elements.select(index).setValue(value);
    }

    public int getValue(int index) {
        return this.elements.select(index).getValue();
    }

    public int getSize() {
        return this.size;
    }

    public void draw() {
        int order = this.elements.getSize();

        GraphicalNumber element = new GraphicalNumber(X + WIDTH * order, Y, WIDTH, WIDTH);
        this.parent.getChildren().add(element.getContainer());
        this.elements.append(element);

        GraphicalNumber index = new GraphicalNumber(X + WIDTH * order, Y + WIDTH, WIDTH, WIDTH);
        index.setValue(order);
        index.getLabel().setStyle("-fx-font-size: " + (WIDTH / 3) + "px; "
                                + "-fx-text-fill: red; "
                                + "-fx-alignment: center;");
        index.getFrame().setHeight(WIDTH / 2);
        index.getFrame().setStroke(Color.TRANSPARENT);
        this.parent.getChildren().add(index.getContainer());
    }

    @Override
    public void create(ArrayList<Integer> elements) {
        this.size = elements.size();
        this.capacity = Math.max(this.size, 10);
        this.capacity = Math.min(this.capacity, 20);
        this.size = Math.min(this.size, this.capacity);

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
        if (Freezing) {
            return;
        }
        Freezing = true;

        if (this.lastSelection != null) {
            Timeline setNull = SelectionAnimation.createUnselected(this.lastSelection, 0.1);
            setNull.setOnFinished(event -> {
                this.lastSelection = null;
            });
            setNull.play();
        }

        if (this.size == MAX_CAPACITY || index > this.size || index < -this.size) {
            Freezing = false;
            return;
        }
        if (this.size == this.capacity) {
            this.capacity = Math.min(this.capacity * 2, MAX_CAPACITY);
            for (int i = 0; i < this.capacity - this.size; i++) {
                this.draw();
            }
        }
        if (index < 0) {
            index += this.size;
        }

        SequentialTransition process = new SequentialTransition();
        process.setOnFinished(event -> {
            Freezing = false;
        });
        for (int i = this.size; i > index; i--) {
            SequentialTransition shiftRight = ShiftAnimation.create(this.elements.select(i - 1), this.elements.select(i));
            PauseTransition pause = new PauseTransition(Duration.seconds(0.2));
            process.getChildren().addAll(shiftRight, pause);
        }
        SequentialTransition insert = InsertAnimation.create(this.elements.select(index), value);
        process.getChildren().add(insert);
        process.play();
        this.size++;
    }

    @Override
    public void append(int value) {
        this.insert(this.size, value);
    }

    @Override
    public void remove(int index) {
        if (Freezing) {
            return;
        }
        Freezing = true;

        if (this.lastSelection != null) {
            Timeline setNull = SelectionAnimation.createUnselected(this.lastSelection, 0.1);
            setNull.setOnFinished(event -> {
                this.lastSelection = null;
            });
            setNull.play();
        }
        if (this.size == 0 || index >= this.size || index < -this.size) {
            Freezing = false;
            return;
        }
        if (index < 0) {
            index += this.size;
        }

        this.size--;
        SequentialTransition process = new SequentialTransition();
        process.setOnFinished(event -> {
            Freezing = false;
        });
        SequentialTransition insert = SwapAnimation.create(this.elements.select(index), new GraphicalNumber(0, 0, 0, 0));
        process.getChildren().add(insert);
        for (int i = index; i < this.size; i++) {
            PauseTransition pause = new PauseTransition(Duration.seconds(0.2));
            SequentialTransition shiftLeft = ShiftAnimation.create(this.elements.select(i + 1), this.elements.select(i));
            process.getChildren().addAll(pause, shiftLeft);
        }
        process.play();
    }

    @Override
    public void update(int index, int value) {
        if (Freezing) {
            return;
        }
        Freezing = true;

        if (this.lastSelection != null) {
            Timeline setNull = SelectionAnimation.createUnselected(this.lastSelection, 0.1);
            setNull.setOnFinished(event -> {
                this.lastSelection = null;
            });
            setNull.play();
        }
        if (index >= this.size || index < -this.size) {
            Freezing = false;
            //handle
            return;
        }
        if (index < 0) {
            index += this.size;
        }
        SequentialTransition updated = InsertAnimation.createUpdated(this.elements.select(index), value);
        updated.setOnFinished(event -> {
            Freezing = false;
        });
        updated.play();
    }

    @Override
    public void select(int index) {
        if (Freezing) {
            return;
        }
        Freezing = true;

        if (this.lastSelection != null) {
            Timeline setNull = SelectionAnimation.createUnselected(this.lastSelection, 0.1);
            setNull.setOnFinished(event -> {
                this.lastSelection = null;
            });
            setNull.play();
        }

        if (index >= this.size || index < -this.size) {
            Freezing = false;
            return;
        }
        if (index < 0) {
            index += this.size;
        }

        GraphicalNumber element = this.elements.select(index);
        Timeline selected = SelectionAnimation.create(element, 0.1);
        selected.setOnFinished(event -> {
            this.lastSelection = element;
            Freezing = false;
        });
        selected.play();
    }

    @Override
    public void sort(boolean reverse) {
        if (Freezing) {
            return;
        }
        Freezing = true;

        if (this.lastSelection != null) {
            Timeline setNull = SelectionAnimation.createUnselected(this.lastSelection, 0.1);
            setNull.setOnFinished(event -> {
                this.lastSelection = null;
            });
            setNull.play();
        }

        if (this.size <= 1) {
            Freezing = false;
            return;
        }

        ArrayList<GraphicalNumber> copy = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            copy.add(this.elements.select(i));
        }

        boolean swapped;
        SequentialTransition process = new SequentialTransition();
        process.setOnFinished(event -> {
            Freezing = false;
        });
        for (int i = 0; i < this.size - 1; i++) {
            swapped = false;
            for (int j = 0; j < this.size - 1 - i; j++) {
                boolean ascending = (copy.get(j).compareTo(copy.get(j + 1)) > 0) & (!reverse);
                boolean descending = (copy.get(j).compareTo(copy.get(j + 1)) < 0) & (reverse);

                if (ascending || descending) {
                    SequentialTransition swap = SwapAnimation.create(this.elements.select(j), this.elements.select(j + 1), copy.get(j + 1).getValue(), copy.get(j).getValue());
                    PauseTransition pause = new PauseTransition(Duration.seconds(0.2));
                    process.getChildren().addAll(swap, pause);

                    GraphicalNumber temp = copy.get(j);
                    copy.set(j, copy.get(j + 1));
                    copy.set(j + 1, temp);

                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        process.play();
    }

    @Override
    public void min() {
        if (Freezing) {
            return;
        }

        if (this.lastSelection != null) {
            Timeline setNull = SelectionAnimation.createUnselected(this.lastSelection, 0.1);
            setNull.setOnFinished(event -> {
                this.lastSelection = null;
            });
            setNull.play();
        }

        if (this.size == 0) {
            return;
        }

        Timeline selected = SelectionAnimation.create(this.elements.select(0), 0.4);
        Timeline unselected;
        SequentialTransition process = new SequentialTransition(selected);
        int minIndex = 0;

        if (this.size == 1) {
            process.play();
            return;
        }

        for (int i = 1; i < this.size; i++) {
            selected = SelectionAnimation.create(this.elements.select(i), 0.4);
            unselected = SelectionAnimation.createUnselected(this.elements.select(getValue(i) < getValue(minIndex) ? minIndex : i), 0.4);
            if (getValue(i) < getValue(minIndex)) {
                minIndex = i;
            }
            PauseTransition pause = new PauseTransition(Duration.seconds(0.3));
            process.getChildren().addAll(selected, pause, unselected);
        }

        lastSelection = this.elements.select(minIndex);
        process.play();
    }

    @Override
    public void max() {
        if (Freezing) {
            return;
        }

        if (this.lastSelection != null) {
            Timeline setNull = SelectionAnimation.createUnselected(this.lastSelection, 0.1);
            setNull.setOnFinished(event -> {
                this.lastSelection = null;
            });
            setNull.play();
        }

        if (this.size == 0) {
            return;
        }

        Timeline selected = SelectionAnimation.create(this.elements.select(0), 0.4);
        Timeline unselected;
        SequentialTransition process = new SequentialTransition(selected);
        int maxIndex = 0;

        if (this.size == 1) {
            process.play();
            return;
        }

        for (int i = 1; i < this.size; i++) {
            selected = SelectionAnimation.create(this.elements.select(i), 0.4);
            unselected = SelectionAnimation.createUnselected(this.elements.select(getValue(i) > getValue(maxIndex) ? maxIndex : i), 0.4);
            if (getValue(i) > getValue(maxIndex)) {
                maxIndex = i;
            }
            PauseTransition pause = new PauseTransition(Duration.seconds(0.3));
            process.getChildren().addAll(selected, pause, unselected);
        }

        lastSelection = this.elements.select(maxIndex);
        process.play();
    }
}