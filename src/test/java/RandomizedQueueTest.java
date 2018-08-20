import static org.junit.Assert.assertEquals;

import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

public class RandomizedQueueTest {
    private RandomizedQueue<String> rq;
    private Logger logger;

    @Before
    public void before() {
        rq = new RandomizedQueue<>();
        logger = Logger.getLogger(this.getClass().getName());
    }

    @Test
    public void enqueueTest() {
        rq.enqueue("A");
        rq.enqueue("B");
        rq.enqueue("C");
        rq.enqueue("D");
        rq.enqueue("E");
        rq.enqueue("F");
        assertEquals(6, rq.size());
    }

    @Test
    public void dequeueTest() {
        rq.enqueue("A");
        rq.enqueue("B");
        rq.enqueue("C");
        rq.enqueue("D");
        rq.enqueue("E");
        rq.enqueue("F");
        int idx = (int)(Math.random() * rq.size());
        logger.info("idx = " + idx);
        String s = rq.dequeue();
        logger.info("s = " + s);
        s = rq.dequeue();
        logger.info("s = " + s);

    }
    
}