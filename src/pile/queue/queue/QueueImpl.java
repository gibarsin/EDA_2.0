package pile.queue.queue;

import pile.stack.Stack;
import pile.stack.StackImpl;

import java.util.NoSuchElementException;

public class QueueImpl<T> implements Queue<T> {
    private Stack<T> stack1;
    private Stack<T> stack2;

    public QueueImpl() {
        stack1 = new StackImpl<>();
        stack2 = new StackImpl<>();
    }

    @Override
    public void enqueue(T elem) {
        stack1.push(elem);
    }

    @Override
    public T dequeue() {
        if (stack1.isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }

        T elem = stack1.pop();

        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        return elem;
    }

    @Override
    public boolean isEmpty() {
        return stack1.isEmpty();
    }
}
