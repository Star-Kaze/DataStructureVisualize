package data.structures;


import java.util.ArrayList;
import java.util.Arrays;

public class DynamicArray<T extends Comparable<T>> {
    private Object[] elements;
    private int capacity;
    private int size;

    public DynamicArray(ArrayList<T> elements) {
        this.capacity = Math.max(elements.size(), 10);
        this.size = elements.size();
        this.elements = new Object[this.capacity];
        for (int i = 0; i < this.size; i++) {
            this.elements[i] = elements.get(i);
        }
    }

    public DynamicArray() {
        this.capacity = 10;
        this.size = 0;
        this.elements = new Object[this.capacity];
    }

    public int getSize() {
        return size;
    }

    private void doubleCapacity() {
        this.capacity *= 2;
        Object[] newElements = new Object[this.capacity];
        System.arraycopy(elements, 0, newElements, 0, this.size);
        elements = newElements;
    }

    public void insert(int index, T value) {
        // Deal with index-out-of-bound problem
        if (index > this.size || index < -this.size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");
        }

        // Deal with negative-index problem
        if (index < 0) {
            index += this.size;
        }

        // Double size if reaching limit
        if (this.size == this.capacity) {
            doubleCapacity();
        }

        // Insertion processing
        for (int i = this.size; i > index; i--) {
            elements[i] = elements[i - 1]; // Shift right elements
        }
        elements[index] = value; // Insert value
        this.size++;
    }

    public void append(T value) {
        // Double size if reaching limit
        if (this.size == this.capacity) {
            doubleCapacity();
        }

        // Add value to the end of the list
        elements[this.size] = value;
        this.size++;
    }

    public void remove(int index) {
        // Deal with index-out-of-bound problem
        if (index >= this.size || index < -this.size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");
        }

        // Deal with negative-index problem
        if (index < 0) {
            index += this.size;
        }

        // Removal processing
        this.size--;
        elements[index] = null; // Remove value
        for (int i = index; i < this.size; i++) {
            elements[i] = elements[i + 1]; // Shift left elements
        }
    }

    public void update(int index, T value) {
        // Deal with index-out-of-bound problem
        if (index >= this.size || index < -this.size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");
        }

        // Deal with negative-index problem
        if (index < 0) {
            index += this.size;
        }

        elements[index] = value; // Update value
    }

    public T select(int index) {
        // Deal with index-out-of-bound problem
        if (index >= this.size || index < -this.size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");
        }

        // Deal with negative-index problem
        if (index < 0) {
            index += this.size;
        }

        // Return elements selected by index
        try {
            return (T) elements[index];
        } catch (ClassCastException e) {
            return null;
        }
    }

    public void sort(Boolean reverse) {
        // Only sort if the list has at least 2 elements
        if (this.size <= 1) {
            return;
        }

        // Use bubble sort to implement this method
        boolean swapped;
        for (int i = 0; i < this.size - 1; i++) {
            swapped = false;
            for (int j = 0; j < this.size - 1 - i; j++) {
                boolean ascending = (select(j).compareTo(select(j + 1)) > 0) & (!reverse);
                boolean descending = (select(j).compareTo(select(j + 1)) < 0) & (reverse);
                if (ascending || descending) {
                    Object temp = elements[j];
                    elements[j] = elements[j + 1];
                    elements[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public T min() {
        // Check if list is empty or has only 1 element
        if (this.size == 0) {
            return null;
        }
        if (this.size == 1) {
            return select(0);
        }

        // Loop for all elements to find min value
        T min_value = select(0);
        for (int i = 1; i < this.size; i++) {
            if (select(i).compareTo(min_value) < 0) {
                min_value = select(i);
            }
        }
        return min_value;
    }

    public T max() {
        // Check if list is empty or has only 1 element
        if (this.size == 0) {
            return null;
        }
        if (this.size == 1) {
            return select(0);
        }

        // Loop for all elements to find max value
        T max_value = select(0);
        for (int i = 1; i < this.size; i++) {
            if (select(i).compareTo(max_value) > 0) {
                max_value = select(i);
            }
        }
        return max_value;
    }
}
