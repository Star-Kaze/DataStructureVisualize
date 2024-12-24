package data.structures;

import java.util.ArrayList;

public class CustomCollections {
    public static <T extends Comparable<T>> DynamicArray<T> createDynamicArray(ArrayList<T> elements) {
        return (elements.isEmpty()) ? new DynamicArray<>() : new DynamicArray<>(elements);
    }

    public static <T> Stack<T> createStack(ArrayList<T> elements) {
        return (elements.isEmpty()) ? new Stack<>() : new Stack<>(elements);
    }

    public static <T> Queue<T> createQueue(ArrayList<T> elements) {
        return (elements.isEmpty()) ? new Queue<>() : new Queue<>(elements);
    }
}
