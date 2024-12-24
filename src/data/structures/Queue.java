package data.structures;

import java.util.ArrayList;

public class Queue<T> {
    private ArrayList<T> elements;

    public Queue(ArrayList<T> elements) {
        this.elements = elements;
    }

    public Queue() {
        this.elements = new ArrayList<>();
    }

    public int getSize() {
        return elements.size();
    }

    public ArrayList<T> toList() {
        return new ArrayList<>(elements);
    }

    public void enqueue(T value) {
        elements.add(value);
    }

    public T dequeue() {
        if (elements.isEmpty()) {
            return null;
        }
        T elementToRemove = elements.get(0);
        elements.remove(0);
        return elementToRemove;
    }

    public T peekFront() {
        if (elements.isEmpty()) {
            return null;
        }
        return elements.get(0);
    }

    public T peekBack() {
        if (elements.isEmpty()) {
            return null;
        }
        return elements.get(elements.size() - 1);
    }
}
