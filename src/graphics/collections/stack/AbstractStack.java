package graphics.collections.stack;

import java.util.ArrayList;

public interface AbstractStack {
    public void push(ArrayList<Integer> values);
    public void pop(int k);
    public void peek();
}
