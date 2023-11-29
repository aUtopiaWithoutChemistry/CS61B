package deque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque<T> implements Deque<T> {

    @Override
    public boolean equals(Object obj) {
        boolean isEqual;
        if (obj instanceof ArrayDeque<?> ald) {
            if (this.size == ald.size()) {
                for(int i = 0; i < size; i++) {
                    if (ald.get(i) != this.get(i)) {
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
        return new ArrayListDequeIterator();
    }

    private class ArrayListDequeIterator implements Iterator {
        private int curPos;

        // constructor
        public ArrayListDequeIterator() {
            curPos = 0;
        }

        public boolean hasNext() {
            if (curPos < size) {
                return true;
            }
            return false;
        }

        public T next() {
            T returnItem = item[curPos];
            curPos += 1;
            return returnItem;
        }
    }

    /** an arrayDeque should have it current size, first and last place for insert. */
    private int size;
    private int length;
    private int nextLast;
    private int nextFirst;
    private T[] item;

//    public static void main(String[] args) {
//        Deque<Integer> al1 = new ArrayDeque();
//        al1.addLast(4);
//        al1.addLast(5);
//        al1.addLast(6);
//
//        for(int i : al1) {
//            System.out.println(i);
//        }
//    }
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
        size += 1;
        if (size == length) {
            resizing();
        }
        item[nextFirst] = x;
        if (nextFirst == 0) {
            nextFirst = length - 1;
        } else {
            nextFirst -= 1;
        }
    }

    @Override
    public void addLast(T x) {
        size += 1;
        if (size == length) {
            resizing();
        }
        item[nextLast] = x;
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
        // when the array is empty
        if (size == 0) {
            first = null;
        } else {
            if (nextFirst == length - 1) {
                nextFirst = 0;
                first = item[0];
                item[0] = null;
            } else {
                first = item[nextFirst + 1];
                nextFirst += 1;
                item[nextFirst] = null;
            }
            size -= 1;
            if (size < length / 4) {
                resizing();
            }
        }
        return first;
    }

    @Override
    public T removeLast() {
        T last;
        // when the array is empty
        if (size == 0) {
            last = null;
        } else {
            if (nextLast == 0) {
                nextLast = length - 1;
                last = item[length - 1];
                item[length - 1] = null;
            } else {
                last = item[nextLast - 1];
                nextLast -= 1;
                item[nextLast] = null;
            }
            size -= 1;
            if (size < length / 4) {
                resizing();
            }
        }
        return last;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > length){
            return null;
        } else {
            return item[index];
        }
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    public void resizing() {
        // making it bigger
        T[] newItem = (T[])new Object[length * 2];
        if (size == length) {
            for (int i = nextFirst + 1; i < nextFirst + 1 + length ; i++) {
                if (i < length) {
                    newItem[i - nextFirst - 1] = item[i];
                }
                else {
                    newItem[i - nextFirst - 1] = item[i - length];
                }
            }
            length = length * 2;
            nextFirst = length - 1;
            nextLast = size;
            item = newItem;
        }
        // making is smaller
        else if (size < length / 4) {
            for (int i = nextFirst + 1; i < nextFirst + 1 + length ; i++) {
                if (i - nextFirst - 1 < length / 4) {
                    if (i < length) {
                        newItem[i - nextFirst - 1] = item[i];
                    }
                    else {
                        newItem[i - nextFirst - 1] = item[i - length];
                    }
                }
            }
            length = length / 2;
            nextFirst = length - 1;
            nextLast = size;
            item = newItem;
        }
    }
}