import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ArrayStackTests {

    private ArrayStack<Integer> stack;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        stack = new ArrayStack<>();
    }

    // Tests correct exception is thrown for null element on push
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testPushNullException() {
        stack.push(null);
    }

    // Tests correct exception is thrown for empty stack on pop
    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testPopEmptyException() {
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
        stack.pop();
    }

    // Tests null is returned for empty stack on peek
    @Test(timeout = TIMEOUT)
    public void testPeekEmptyReturnValue() {
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
        assertNull(stack.peek());
    }

    // Tests whether backing array is initially created with correct length
    @Test(timeout = TIMEOUT)
    public void testBackingArrayInitialization() {
        assertEquals(StackInterface.INITIAL_CAPACITY,
                stack.getBackingArray().length);
    }

    // Tests push below initial capacity
    @Test(timeout = TIMEOUT)
    public void testPushBelowInitialCapacity() {
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
        stack.push(465);
        stack.push(134);
        stack.push(234);
        stack.push(129);
        stack.push(472);
        assertEquals(5, stack.size());

        Object[] expected = new Object[StackInterface.INITIAL_CAPACITY];
        expected[0] = 465;
        expected[1] = 134;
        expected[2] = 234;
        expected[3] = 129;
        expected[4] = 472;

        assertArrayEquals(expected, stack.getBackingArray());
    }

    // Tests push to exactly initial capacity
    @Test(timeout = TIMEOUT)
    public void testPushAtInitialCapacity() {
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
        stack.push(465);
        stack.push(134);
        stack.push(234);
        stack.push(129);
        stack.push(472);
        stack.push(234);
        stack.push(999);
        stack.push(819);
        stack.push(285);
        stack.push(281);
        stack.push(812);
        assertEquals(11, stack.size());

        Object[] expected = new Object[StackInterface.INITIAL_CAPACITY];
        expected[0] = 465;
        expected[1] = 134;
        expected[2] = 234;
        expected[3] = 129;
        expected[4] = 472;
        expected[5] = 234;
        expected[6] = 999;
        expected[7] = 819;
        expected[8] = 285;
        expected[9] = 281;
        expected[10] = 812;

        assertArrayEquals(expected, stack.getBackingArray());
    }

    // Tests push beyond initial capacity
    @Test(timeout = TIMEOUT)
    public void testPushPastInitialCapacity() {
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
        stack.push(465);
        stack.push(134);
        stack.push(234);
        stack.push(129);
        stack.push(472);
        stack.push(234);
        stack.push(999);
        stack.push(819);
        stack.push(285);
        stack.push(281);
        stack.push(812);
        stack.push(482);
        stack.push(291);
        assertEquals(13, stack.size());

        Object[] expected = new Object[StackInterface.INITIAL_CAPACITY * 2];
        expected[0] = 465;
        expected[1] = 134;
        expected[2] = 234;
        expected[3] = 129;
        expected[4] = 472;
        expected[5] = 234;
        expected[6] = 999;
        expected[7] = 819;
        expected[8] = 285;
        expected[9] = 281;
        expected[10] = 812;
        expected[11] = 482;
        expected[12] = 291;

        assertArrayEquals(expected, stack.getBackingArray());
    }

    // Tests push beyond one resize
    @Test(timeout = TIMEOUT)
    public void testPushPastOneResize() {
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
        for (int i = 100; i < 200; i++) {
            stack.push(i);
        }
        assertEquals(100, stack.size());

        Object[] expected = new Object[StackInterface.INITIAL_CAPACITY * 16];
        for (int i = 100; i < 200; i++) {
            expected[i - 100] = i;
        }

        assertArrayEquals(expected, stack.getBackingArray());
    }

    // Tests peek with an empty stack
    @Test(timeout = TIMEOUT)
    public void testPeekEmpty() {
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
        assertNull(stack.peek());
    }

    // Tests peek below initial capacity
    @Test(timeout = TIMEOUT)
    public void testPeekBelowInitalCapacity() {
        assertEquals(0, stack.size());
        stack.push(465);
        stack.push(134);
        stack.push(234);
        stack.push(129);
        stack.push(472);
        assertEquals(5, stack.size());

        assertEquals(new Integer(472), stack.peek());

        Object[] expected = new Object[StackInterface.INITIAL_CAPACITY];
        expected[0] = 465;
        expected[1] = 134;
        expected[2] = 234;
        expected[3] = 129;
        expected[4] = 472;

        assertArrayEquals(expected, stack.getBackingArray());
    }

    // Tests peek at initial capacity
    @Test(timeout = TIMEOUT)
    public void testPeekAtInitialCapacity() {
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
        stack.push(465);
        stack.push(134);
        stack.push(234);
        stack.push(129);
        stack.push(472);
        stack.push(234);
        stack.push(999);
        stack.push(819);
        stack.push(285);
        stack.push(281);
        stack.push(812);
        assertEquals(11, stack.size());

        assertEquals(new Integer(812), stack.peek());

        Object[] expected = new Object[StackInterface.INITIAL_CAPACITY];
        expected[0] = 465;
        expected[1] = 134;
        expected[2] = 234;
        expected[3] = 129;
        expected[4] = 472;
        expected[5] = 234;
        expected[6] = 999;
        expected[7] = 819;
        expected[8] = 285;
        expected[9] = 281;
        expected[10] = 812;

        assertArrayEquals(expected, stack.getBackingArray());
    }

    // Tests peek beyond initial capacity
    @Test(timeout = TIMEOUT)
    public void testPeekPastInitialCapacity() {
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
        stack.push(465);
        stack.push(134);
        stack.push(234);
        stack.push(129);
        stack.push(472);
        stack.push(234);
        stack.push(999);
        stack.push(819);
        stack.push(285);
        stack.push(281);
        stack.push(812);
        stack.push(482);
        stack.push(291);
        assertEquals(13, stack.size());

        assertEquals(new Integer(291), stack.peek());

        Object[] expected = new Object[StackInterface.INITIAL_CAPACITY * 2];
        expected[0] = 465;
        expected[1] = 134;
        expected[2] = 234;
        expected[3] = 129;
        expected[4] = 472;
        expected[5] = 234;
        expected[6] = 999;
        expected[7] = 819;
        expected[8] = 285;
        expected[9] = 281;
        expected[10] = 812;
        expected[11] = 482;
        expected[12] = 291;

        assertArrayEquals(expected, stack.getBackingArray());
    }

    // Tests peek beyond one resize
    @Test(timeout = TIMEOUT)
    public void testPeekPastOneResize() {
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
        for (int i = 100; i < 200; i++) {
            stack.push(i);
        }
        assertEquals(100, stack.size());

        assertEquals(new Integer(199), stack.peek());

        Object[] expected = new Object[StackInterface.INITIAL_CAPACITY * 16];
        for (int i = 100; i < 200; i++) {
            expected[i - 100] = i;
        }

        assertArrayEquals(expected, stack.getBackingArray());
    }

    // Tests pop with one element
    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testPopOneElement() {
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
        stack.push(913);
        assertEquals(1, stack.size());
        assertEquals(new Integer(913), stack.pop());
        assertEquals(0, stack.size());

        Object[] expected = new Object[StackInterface.INITIAL_CAPACITY];
        assertArrayEquals(expected, stack.getBackingArray());
        stack.pop();
    }

    // Tests pop below initial capacity
    @Test(timeout = TIMEOUT)
    public void testPopBelowInitialCapacity() {
        assertEquals(0, stack.size());
        stack.push(465);
        stack.push(134);
        stack.push(234);
        stack.push(129);
        stack.push(472);
        assertEquals(5, stack.size());

        assertEquals(new Integer(472), stack.pop());
        assertEquals(new Integer(129), stack.pop());
        assertEquals(new Integer(234), stack.pop());

        assertEquals(2, stack.size());

        Object[] expected = new Object[StackInterface.INITIAL_CAPACITY];
        expected[0] = 465;
        expected[1] = 134;

        assertArrayEquals(expected, stack.getBackingArray());
    }

    // Tests pop at initial capacity
    @Test(timeout = TIMEOUT)
    public void testPopAtInitialCapacity() {
        assertEquals(0, stack.size());
        stack.push(465);
        stack.push(134);
        stack.push(234);
        stack.push(129);
        stack.push(472);
        stack.push(234);
        stack.push(999);
        stack.push(819);
        stack.push(285);
        stack.push(281);
        stack.push(812);
        assertEquals(11, stack.size());

        assertEquals(new Integer(812), stack.pop());
        assertEquals(new Integer(281), stack.pop());
        assertEquals(new Integer(285), stack.pop());

        assertEquals(8, stack.size());

        Object[] expected = new Object[StackInterface.INITIAL_CAPACITY];
        expected[0] = 465;
        expected[1] = 134;
        expected[2] = 234;
        expected[3] = 129;
        expected[4] = 472;
        expected[5] = 234;
        expected[6] = 999;
        expected[7] = 819;

        assertArrayEquals(expected, stack.getBackingArray());
    }

    // Tests pop past initial capacity
    @Test(timeout = TIMEOUT)
    public void testPopPastInitialCapacity() {
        assertEquals(0, stack.size());
        stack.push(465);
        stack.push(134);
        stack.push(234);
        stack.push(129);
        stack.push(472);
        stack.push(234);
        stack.push(999);
        stack.push(819);
        stack.push(285);
        stack.push(281);
        stack.push(812);
        stack.push(482);
        stack.push(291);
        assertEquals(13, stack.size());

        assertEquals(new Integer(291), stack.pop());
        assertEquals(new Integer(482), stack.pop());
        assertEquals(new Integer(812), stack.pop());

        assertEquals(10, stack.size());

        Object[] expected = new Object[StackInterface.INITIAL_CAPACITY * 2];
        expected[0] = 465;
        expected[1] = 134;
        expected[2] = 234;
        expected[3] = 129;
        expected[4] = 472;
        expected[5] = 234;
        expected[6] = 999;
        expected[7] = 819;
        expected[8] = 285;
        expected[9] = 281;

        assertArrayEquals(expected, stack.getBackingArray());
    }

    // Tests pop beyond one resize
    @Test(timeout = TIMEOUT)
    public void testPopPastOneResize() {
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
        for (int i = 100; i < 200; i++) {
            stack.push(i);
        }
        assertEquals(100, stack.size());

        for (int i = 199; i >= 150; i--) {
            assertEquals(new Integer(i), stack.pop());
        }

        assertEquals(50, stack.size());

        Object[] expected = new Object[StackInterface.INITIAL_CAPACITY * 16];
        for (int i = 100; i < 150; i++) {
            expected[i - 100] = i;
        }

        assertArrayEquals(expected, stack.getBackingArray());
    }
}
