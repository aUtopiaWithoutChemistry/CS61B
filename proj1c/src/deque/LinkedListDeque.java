package deque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {

    @Override
    public String toString(){
        return this.toList().toString();
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEqual;
        if (obj instanceof LinkedListDeque<?> lld) {
            if (lld.size == this.size){
                for(int i = 0; i < size; i++) {
                    if (lld.get(i) != this.get(i)) {
                        return false;
                    }
                }
                isEqual = true;
            }
            else {
                isEqual = false;
            }
        }
        else {
            isEqual = false;
        }
        return isEqual;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator {
        private int curPos;
        public Node curNode;

        // construction functioon
        public LinkedListDequeIterator() {
            curPos = 0;

            // point at the first element
            curNode = sentinel;
        }
        @Override
        public boolean hasNext() {
            if (curPos < size) {
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            T returnItem = curNode.next.item;
            curNode = curNode.next;
            curPos += 1;
            return returnItem;
        }
    }

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
    private Node cur;

    // constructor method
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.last = sentinel;
        cur = new Node(null, sentinel, null);
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
        if (isEmpty()) {
            return null;
        } else {
            T firstItem = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.last = sentinel;
            size -= 1;
            return firstItem;
        }
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            T lastItem = sentinel.last.item;
            sentinel.last = sentinel.last.last;
            sentinel.last.next = sentinel;
            size -= 1;
            return lastItem;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0) {
            return null;
        } else {
            for (int i = 0; i < index + 1; i++) {
                cur.next = cur.next.next;
            }
            T curItem = cur.next.item;
            cur.next = sentinel;
            return curItem;
        }

    }

    @Override
    public T getRecursive(int index) {
        if (index < 0) {
            return null;
        } else if (index == 0) {
            T curItem = cur.next.item;
            cur.next = sentinel;
            return curItem;
        }
        cur.next = cur.next.next;
        return getRecursive(index - 1);
    }
}