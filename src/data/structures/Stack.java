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

    public ArrayList<T> toList() {
        return new ArrayList<>(elements);
    }

    public void push(T value) {
        elements.add(value);
    }

    public T pop() {
        if (elements.isEmpty()) {
            return null;
        }
        int indexToRemove = elements.size() - 1;
        T elementToRemove = elements.get(indexToRemove);
        elements.remove(indexToRemove);
        return elementToRemove;
    }

    public T peek() {
        if (elements.isEmpty()) {
            return null;
        }
        return elements.get(elements.size() - 1);
    }
}
