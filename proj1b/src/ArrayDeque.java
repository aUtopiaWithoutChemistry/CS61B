import java.util.ArrayList;
import java.util.List;

public class ArrayDeque<T> implements Deque<T> {

    /** an arrayDeque should have it current size, first and last place for insert. */
    private int size;
    private int length;
    private int nextLast;
    private int nextFirst;
    private T[] item;

    /** constructor */
    public ArrayDeque() {
        item = (T[])new Object[8];
        size = 0;
        length = 8;
        nextFirst = length - 1;
        nextLast = 0;
    }

    @Override
    public void addFirst(T x) {
        item[nextFirst] = x;
        size += 1;
        if (nextFirst == 0) {
            nextFirst = length - 1;
        } else {
            nextFirst -= 1;
        }
    }

    @Override
    public void addLast(T x) {
        item[nextLast] = x;
        size += 1;
        if (nextLast == length -1) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            returnList.add(item[i]);
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
        T first;
        if (nextFirst < length) {
            first = item[nextFirst + 1];
            item[nextFirst + 1] = null;
        } else {
            first = item[0];
            item[0] = null;
        }

        return first;
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

    public ArrayDeque<T> resizing() {
        return null;
    }
}
