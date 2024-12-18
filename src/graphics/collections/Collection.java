package graphics.collections;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public interface Collection {
    public void create(ArrayList<Integer> elements);
    public void draw();
    public int getValue(int index);
    public void setValue(int index, int value);
    public void changeColor(int index, Color color);
}
