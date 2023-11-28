import org.junit.jupiter.api.*;
import deque.Deque;
import deque.ArrayDeque;
import deque.LinkedListDeque;

import java.util.Iterator;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class MaxArrayDequeTest {
    @Test
    public void arrayListDequeIteratorTest() {
        Deque<Integer> al1 = new ArrayDeque();
        al1.addLast(4); // [4, null, null, null, null, null, null, null]
        al1.addLast(5); // [4,    5, null, null, null, null, null, null]
        al1.addLast(6); // [4,    5,    6, null, null, null, null, null]

        for(int i : al1) {
            System.out.println(i);
        }
    }

    @Test
    public void LinkedListDequeTest() {
        Deque<Integer> ll1 = new LinkedListDeque();
        ll1.addLast(8);
        ll1.addLast(9);
        ll1.addLast(6);
        ll1.addLast(4);

        for(int i : ll1) {
            System.out.println(i);
        }
    }

    @Test
    public void testEqualDeques() {
        Deque<String> lld1 = new LinkedListDeque<>();
        Deque<String> lld2 = new LinkedListDeque<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");

        assertThat(lld1).isEqualTo(lld2);
    }
}
