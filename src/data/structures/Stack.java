package data.structures;

import java.util.ArrayList;

public class Stack<T> {
    private ArrayList<T> elements;

    public Stack(ArrayList<T> elements) {
        this.elements = elements;
    }

    public Stack() {
        this.elements = new ArrayList<>();
    }

    public int getSize() {
        return elements.size();
    }


}
