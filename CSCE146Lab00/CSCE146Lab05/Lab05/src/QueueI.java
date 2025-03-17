// Justin Schlag 2025
// Queue interface for generic queue operations.

public interface QueueI<T> {
    void enqueue(T data);
    T dequeue();
    T peek();
    void print();
}
