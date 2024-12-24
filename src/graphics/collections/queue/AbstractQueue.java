package graphics.collections.queue;

public interface AbstractQueue {
    public void enqueue(int value);
    public void dequeue();
    public void peekFront();
    public void peekBack();
}
