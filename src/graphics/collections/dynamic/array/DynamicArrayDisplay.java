package graphics.collections.dynamic.array;

import data.structures.CustomCollections;
import data.structures.DynamicArray;
import graphics.animations.ShiftAnimation;
import graphics.animations.SwapAnimation;
import graphics.animations.UpdatedAnimation;
import graphics.collections.Collection;
import graphics.graphical.elements.GraphicalNumber;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;

public class DynamicArrayDisplay implements Collection, AbstractDynamicArray {
    private static final double X = 60;
    private static final double Y = 60;
    private static final double WIDTH = 70;
    private static final int MAX_CAPACITY = 20;
    public static boolean Existed = false;

    private DynamicArray<GraphicalNumber> elements;
    private Pane parent;
    private int capacity;
    private int size;
    private int lastSelection;

    public DynamicArrayDisplay(Pane parent) {
        this.elements = CustomCollections.createDynamicArray(new ArrayList<GraphicalNumber>());
        this.parent = parent;
        this.lastSelection = 0;
    }

    public void setValue(int index, int value) {
        this.elements.select(index).setValue(value);
    }

    public int getValue(int index) {
        return this.elements.select(index).getValue();
    }

    public void removeValue(int index) {
        this.elements.select(index).getLabel().setText("");
    }

    public ArrayList<Integer> toList() {
        ArrayList<Integer> arrLst = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            arrLst.add(this.getValue(i));
        }
        return arrLst;
    }

    @Override
    public void draw() {
//        int order = this.elements.size();
//
//        // Create square
//        Rectangle square = new Rectangle(WIDTH, WIDTH);
//        square.setStroke(Color.BLACK);
//        square.setFill(Color.TRANSPARENT);
//
//        // Create label
//        Label label = new Label();
//        label.setStyle("-fx-font-size: " + (WIDTH / 3) + "px; "
//                + "-fx-text-fill: black; "
//                + "-fx-alignment: center;");
//
//        // Create container
//        StackPane container = new StackPane();
//        container.setLayoutX(X + WIDTH * order);
//        container.setLayoutY(Y);
//        container.getChildren().addAll(square, label);
//
//        // Add container to parent
//        this.parent.getChildren().add(container);
//        this.elements.add(container);
        int order = this.elements.getSize();
        GraphicalNumber element = new GraphicalNumber(X + WIDTH * order, Y, WIDTH, WIDTH);

        this.parent.getChildren().add(element.getContainer());
        this.elements.append(element);
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
//        if (this.size == 0) {
//            SequentialTransition insertion = UpdatedAnimation.create(this.elements.select(0), value);
////            this.setValue(0, value);
//            this.size++;
//            return;
////        }
//        if (index > this.size) {
//            //handle
//            return;
//        }
////        this.changeColor(this.lastSelection, Color.BLACK);
        if (this.size == MAX_CAPACITY || index > this.size) {
            return;
        } else if (this.size == this.capacity) {
            this.capacity = Math.min(this.capacity * 2, MAX_CAPACITY);
            for (int i = 0; i < this.capacity - this.size; i++) {
                this.draw();
            }
        }
//        for (int i = this.size - 1; i >= index; i--) {
//            this.setValue(i + 1, this.getValue(i));
//            this.removeValue(i);
//        }
//        this.setValue(index, value);
//        this.size++;
        SequentialTransition process = new SequentialTransition();
        for (int i = this.size; i > index; i--) {
            SequentialTransition shiftRight = ShiftAnimation.create(this.elements.select(i - 1), this.elements.select(i));
            PauseTransition pause = new PauseTransition(Duration.seconds(0.2));
            process.getChildren().addAll(shiftRight, pause);
        }
        SequentialTransition insert = UpdatedAnimation.create(this.elements.select(index), value);
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
        if (this.size == 0 || index >= this.size) {
            return;
        }
//        this.changeColor(this.lastSelection, Color.BLACK);
//        if (this.size == 1) {
//            this.removeValue(0);
//            this.size--;
//            return;
//        }
//        if (index >= this.size) {
//            return;
//        }
//        this.removeValue(index);
//        for (int i = index; i < size - 1; i++) {
//            this.setValue(i, this.getValue(i + 1));
//            this.removeValue(i + 1);
//        }
        this.size--;
        SequentialTransition process = new SequentialTransition();
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
        if (this.size != 0) {
            this.changeColor(this.lastSelection, Color.BLACK);
        }
        if (index >= this.size) {
            //handle
            return;
        }
        this.setValue(index, value);
    }

    @Override
    public void select(int index) {
        if (this.size != 0) {
            this.changeColor(this.lastSelection, Color.BLACK);
        }
        if (index >= this.size) {
            return;
        }
        this.changeColor(index, Color.RED);
        this.lastSelection = index;
    }

    @Override
    public void sort(boolean reverse) {
        if (this.size != 0) {
            this.changeColor(this.lastSelection, Color.BLACK);
        }
//        ArrayList<Integer> lst = this.toList();
//        if (ascending) {
//            Collections.sort(lst);
//        } else {
//            Collections.sort(lst, Collections.reverseOrder());
//        }
//        for (int i = 0; i < this.size; i++) {
//            this.setValue(i, lst.get(i));
//        }
        this.elements.sort(reverse);
        for (int i = 0; i < this.size; i++) {
            this.elements.select(i).getContainer().setLayoutX(X + WIDTH * i);
        }
    }

    @Override
    public void min() {
        int min_value = this.elements.min().getValue();
        for (int i = 0; i < this.size; i++) {
            if (this.getValue(i) == min_value) {
                this.select(i);
                break;
            }
        }
    }

    @Override
    public void max() {
        int max_value = this.elements.max().getValue();
        for (int i = 0; i < this.size; i++) {
            if (this.getValue(i) == max_value) {
                this.select(i);
                break;
            }
        }
    }

    @Override
    public void changeColor(int index, Color color) {
        this.elements.select(index).getLabel().setTextFill(color);
    }
}