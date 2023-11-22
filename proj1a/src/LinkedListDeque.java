import java.util.ArrayList;
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
        Node first = new Node(x, sentinel.next, sentinel);
        sentinel.next.last = first;
        sentinel.next = first;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        Node last = new Node(x, sentinel, sentinel.last);
        sentinel.last.next = last;
        sentinel.last = last;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node cur = sentinel.next;
        for (int i = 0; i < size; i++) {
            returnList.add(cur.item);
            cur = cur.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        sentinel.next = sentinel.next.next;
        sentinel.next.last = sentinel;
        size -= 1;
        return null;
    }

    @Override
    public T removeLast() {
        sentinel.last = sentinel.last.last;
        sentinel.last.next = sentinel;
        size -= 1;
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
