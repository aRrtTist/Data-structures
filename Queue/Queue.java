public interface Queue<E> {
    int getSize();
    boolean isEmpty();
    E dequeue();
    E getFront();
    void enqueue(E e);
}
