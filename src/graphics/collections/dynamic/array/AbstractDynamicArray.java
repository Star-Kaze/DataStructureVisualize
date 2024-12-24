package graphics.collections.dynamic.array;

public interface AbstractDynamicArray {
    public void insert(int index, int value);
    public void append(int value);
    public void remove(int index);
    public void update(int index, int value);
    public void select(int index);
    public void sort(boolean Ascending);
    public void min();
    public void max();
}
