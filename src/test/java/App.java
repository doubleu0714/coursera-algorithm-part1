import static org.junit.Assert.assertTrue;

import java.util.logging.Logger;

import org.junit.Test;

public class App {
    private Logger logger = Logger.getLogger(this.getClass().getName());
    @Test
    public void test() {
        logger.info("test test test test");
        String s = "test";

        if(s.startsWith("t")) {
            logger.info("start with 't'");
        } else if(s.endsWith("t")) {
            logger.info("end with 't'");
        }
    }
}