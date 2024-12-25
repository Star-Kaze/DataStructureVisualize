package graphics.collections.queue;

import java.util.ArrayList;

public interface AbstractQueue {
    public void enqueue(ArrayList<Integer> values);
    public void dequeue(int value);
    public void peekFront();
    public void peekBack();
}
