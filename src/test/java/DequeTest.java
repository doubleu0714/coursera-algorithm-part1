import static org.junit.Assert.assertEquals;

import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

public class DequeTest {
    private Deque<String> deque;
    private Logger logger;

    @Before
    public void before() {
        deque = new Deque<>();
        logger = Logger.getLogger(this.getClass().getName());
    }

    @Test
    public void addFirstTest() {
        deque.addFirst("A");
        deque.addFirst("B");
        deque.addFirst("C");
        deque.addFirst("D");
        deque.addFirst("E");

        assertEquals(5, deque.size());
        for (String item : deque) {
            logger.info("item is [" + item + "]");
        }
    }

    @Test
    public void addLastTest() {
        deque.addLast("A");
        deque.addLast("B");
        deque.addLast("C");
        deque.addLast("D");
        deque.addLast("E");

        assertEquals(5, deque.size());
        for (String item : deque) {
            logger.info("item is [" + item + "]");
        }
    }
    
    @Test
    public void removeTest() {
        deque.addFirst("A");
        assertEquals(1, deque.size());
        assertEquals("A", deque.removeFirst());

        deque.addLast("1");
        assertEquals(1, deque.size());
        assertEquals("1", deque.removeLast());
        
        deque.addFirst("B");
        deque.addLast("C");
        deque.addLast("D");
        deque.addFirst("E");
        deque.addFirst("F");
        assertEquals("D", deque.removeLast());
        assertEquals("C", deque.removeLast());
        assertEquals("F", deque.removeFirst());
        assertEquals("E", deque.removeFirst());
        assertEquals("B", deque.removeFirst());
        assertEquals(0, deque.size());
    }
}