import edu.princeton.cs.algs4.In;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

     @Test
     @DisplayName("LinkedListDeque has no fields besides nodes and primitives")
     void noNonTrivialFields() {
         Class<?> nodeClass = NodeChecker.getNodeClass(LinkedListDeque.class, true);
         List<Field> badFields = Reflection.getFields(LinkedListDeque.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(nodeClass) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not nodes or primitives").that(badFields).isEmpty();
     }

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque<String> lld1 = new LinkedListDeque<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque<String> lld1 = new LinkedListDeque<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque<Integer> lld1 = new LinkedListDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

    // Below, you'll write your own tests for LinkedListDeque.

    // test the size method only after using add
    @Test
    public void sizeAfterAddTest() {
         Deque<Integer> lld1 = new LinkedListDeque<>();

         lld1.addFirst(0); // 1
         lld1.addLast(3);  // 2
         lld1.addFirst(4); // 3
         lld1.addLast(4);  // 4

        assertThat(lld1.size()).isEqualTo(4);
    }

    // test the empty method
    @Test
    public void emptyTest() {
         Deque<Integer> lld1 = new LinkedListDeque<>();
         Deque<Integer> lld2 = new LinkedListDeque<>();

         lld1.addLast(4);

         assertThat(lld1.isEmpty()).isFalse();
         assertThat(lld2.isEmpty()).isTrue();
    }

    // test the removeFirst method
    @Test
    public void removeFirstTest() {
        Deque<Integer> lld1 = new LinkedListDeque<>();
        Deque<Integer> lld2 = new LinkedListDeque<>();

        lld1.addFirst(5); // [5]
        lld1.addFirst(7); // [7, 5]
        lld1.addFirst(9); // [9, 7, 5]

        assertThat(lld1.removeFirst()).isEqualTo(9); // [7, 5]
        assertThat(lld1.toList()).containsExactly(7, 5).inOrder();
        assertThat(lld2.removeFirst()).isEqualTo(null);
    }

    // test the removeLast method
    @Test
    public void removeLastTest() {
         Deque<Integer> lld1 = new LinkedListDeque<>();
         Deque<Integer> lld2 = new LinkedListDeque<>();

         lld1.addFirst(3); // [3]
         lld1.addFirst(9); // [9, 3]
         lld1.addFirst(4); // [4, 9, 3]

         assertThat(lld1.removeLast()).isEqualTo(3);   // [4, 9]
         assertThat(lld1.toList()).containsExactly(4, 9).inOrder();
         assertThat(lld2.removeLast()).isEqualTo(null);
    }
}