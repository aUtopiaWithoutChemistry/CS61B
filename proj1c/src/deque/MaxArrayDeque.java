package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private int size = 0;
    private Comparator comparator;

    public static class ArrayDequeComparator<T> implements Comparator<T> {

        // compare method
        @Override
        public int compare(T o1, T o2) {
            return o1.toString().compareTo(o2.toString());
        }

        // comparator construction method
        public static Comparator getComparator() {
            return new ArrayDequeComparator();
        }
    }

    // construction method for MaxArrayDeque
    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    public T max() {
        size = this.size();
        if (size == 0) {
            System.out.println(size);
            return null;
        }
        else {
            int maxPos = 0;
            T max = this.get(0);
            for(int i = 0; i < size; i++) {
                if (get(i).toString().compareTo(get(maxPos).toString()) > 0) {
                    maxPos = i;
                    max = get(i);
                }
            }
            return max;
        }
    }

    public T max(Comparator<T> c) {
        size = size();
        if (size == 0) {
            return null;
        }
        else {
            int maxPos = 0;
            T max = get(0);
            for(int i = 1; i < size(); i++) {
                if (c.compare(get(i), get(maxPos)) > 0) {
                    max = get(i);
                    maxPos= i;
                }
            }
            return max;
        }
    }
}
