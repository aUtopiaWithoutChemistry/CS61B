import jh61b.utils.Reflection;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDequeTest {

     @Test
     @DisplayName("ArrayDeque has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }

    @Test
    public void testAddFirst() {
        Deque<Integer> al1 = new ArrayDeque<>();
        al1.addFirst(3); // [null, null, null, null, null, null, null,    3]
        al1.addFirst(4); // [null, null, null, null, null, null,    4,    3]
        assertThat(al1.toList()).containsExactly(null, null, null, null, null, null, 4, 3).inOrder();
    }

    @Test
    public void testAddLast() {
         Deque<Integer> al1 = new ArrayDeque<>();
         al1.addLast(5); // [   5, null, null, null, null, null, null, null]
         al1.addLast(3); // [   5,    3, null, null, null, null, null, null]
         assertThat(al1.toList()).containsExactly(5, 3, null, null, null, null, null, null).inOrder();
    }

    @Test
    public void testIsEmpty() {
         Deque<Integer> al1 = new ArrayDeque<>();
         Deque<Integer> al2 = new ArrayDeque<>();

         al1.addLast(4);

         assertThat(al1.isEmpty()).isFalse();
         assertThat(al2.isEmpty()).isTrue();
    }

    @Test
    public void testSize() {
         Deque<Integer> al1 = new ArrayDeque<>();
         al1.addLast(6);
         al1.addLast(5);
         assertThat(al1.size()).isEqualTo(2);
    }

    @Test
    public void removeFirstTest() {
         Deque<Integer> al1 = new ArrayDeque<>();
         al1.addLast(4);  // [   4, null, null, null, null, null, null, null]
         al1.addLast(6);  // [   4,    6, null, null, null, null, null, null]
         al1.addFirst(5); // [   4,    6, null, null, null, null, null,    5]
         al1.addFirst(8); // [   4,    6, null, null, null, null,    8,    5]
         assertThat(al1.toList()).containsExactly(4, 6, null, null, null, null, 8, 5);

         assertThat(al1.removeFirst()).isEqualTo(8);
         assertThat(al1.toList()).containsExactly(4, 6, null, null, null, null, null, 5);
    }

    @Test
    public void removeLastTest() {
        Deque<Integer> al1 = new ArrayDeque<>();
        al1.addLast(4);  // [   4, null, null, null, null, null, null, null]
        al1.addLast(6);  // [   4,    6, null, null, null, null, null, null]
        al1.addFirst(5); // [   4,    6, null, null, null, null, null,    5]
        al1.addFirst(8); // [   4,    6, null, null, null, null,    8,    5]
        assertThat(al1.toList()).containsExactly(4, 6, null, null, null, null, 8, 5);

        assertThat(al1.removeLast()).isEqualTo(6);
        assertThat(al1.toList()).containsExactly(4, null, null, null, null, null, 8, 5);
    }

    @Test
    public void getTest() {
         Deque<Integer> al1 = new ArrayDeque<>();
         al1.addFirst(4);
         al1.addFirst(5);
         al1.addFirst(6);
         al1.addFirst(7);

         assertThat(al1.get(0)).isEqualTo(4);
         assertThat(al1.get(-1)).isEqualTo(null);
         assertThat(al1.get(9)).isEqualTo(null);
    }
}
