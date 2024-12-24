package graphics.collections;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public interface Collection {
    public void create(ArrayList<Integer> elements);
    public void draw();
    public void changeColor(int index, Color color);
}
