import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {

    // Every DLListNode contains the content, next, and last;
    public class Node {
        public T item;
        public Node next;
        public Node last;
        // constructor of every node
        public Node(T i, Node n, Node l) {
            item = i;
            next = n;
            last = l;
        }
    }

    // The whole list at least have a sentinel node and a size
    private Node sentinel;
    private int size;

    // constructor method
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.last = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T x) {

    }

    @Override
    public void addLast(T x) {

    }

    @Override
    public List<T> toList() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
