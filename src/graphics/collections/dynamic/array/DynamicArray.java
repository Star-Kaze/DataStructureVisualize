package graphics.collections.dynamic.array;

public interface DynamicArray {
    public void insert(int index, int value);
    public void append(int value);
    public void remove(int index);
    public void update(int index, int value);
}
