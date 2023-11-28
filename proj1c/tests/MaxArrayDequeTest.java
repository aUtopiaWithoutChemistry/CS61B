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

}
