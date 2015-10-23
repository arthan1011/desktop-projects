import org.junit.Test;

/**
 * Created by ashamsiev on 23.10.2015
 */
public class ConcurrentTest {

    @Test
    public void testProcessors() throws Exception {
        System.out.println("Number of available processors: " + Runtime.getRuntime().availableProcessors());
    }
}
