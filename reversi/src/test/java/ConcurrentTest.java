import org.arthan.desktop.reversi.Main;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by ashamsiev on 23.10.2015
 */
public class ConcurrentTest {

    @Test
    public void testProcessors() throws Exception {
        System.out.println("Number of available processors: " + Runtime.getRuntime().availableProcessors());
    }

    @Test
    public void testPrimes() throws Exception {
//        new SequentialPrimeFinder().timeAndCompute(10000000);
        new ConcurrentPrimeFinder(12, 24).timeAndCompute(10000000);
        new ConcurrentPrimeFinder(12, 48).timeAndCompute(10000000);
    }

    static abstract class AbstractPrimeFinder {
        protected boolean isPrime(final int number) {
            if (number <= 1) return false;

            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number % i == 0) return false;
            }
            return true;
        }

        protected int countPrimesInRange(final int lower, final int upper) {
            int total = 0;
            for (int i = lower; i <= upper; i++) {
                if (isPrime(i)) total++;
            }
            return total;
        }

        protected void timeAndCompute(final int number) {
            final long start = System.nanoTime();
            final int primes = countPrimes(number);
            final long end = System.nanoTime();

            System.out.printf("Number of primes under %d is %d\n", number, primes);
            System.out.println("Time (seconds) taken is: " + (end - start) / 1.0e9);
        }

        protected abstract int countPrimes(final int number);
    }

    static class SequentialPrimeFinder extends AbstractPrimeFinder {
        @Override
        protected int countPrimes(final int number) {
            return countPrimesInRange(1, number);
        }
    }

    static class ConcurrentPrimeFinder extends AbstractPrimeFinder {

        private final int poolSize;
        private final int numberOfParts;

        public ConcurrentPrimeFinder(int poolSize, int numberOfParts) {
            this.poolSize = poolSize;
            this.numberOfParts = numberOfParts;
        }

        @Override
        protected int countPrimes(final int number) {
            int count = 0;

            try {
                final List<Callable<Integer>> partitions = new ArrayList<>();
                final int chunksPerPartition = number / numberOfParts;

                for (int i = 0; i < numberOfParts; i++) {
                    final int lower = (i * chunksPerPartition) + 1;
                    final int upper = (i == numberOfParts-1) ? number : lower + chunksPerPartition - 1;
                    partitions.add(() -> countPrimesInRange(lower, upper));
                }

                final ExecutorService executorPool = Executors.newFixedThreadPool(poolSize);
                final List<Future<Integer>> resultFromParts = executorPool.invokeAll(partitions, 10000, TimeUnit.SECONDS);
                executorPool.shutdown();
                for (Future<Integer> integerFuture : resultFromParts) {
                    count += integerFuture.get();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return count;
        }
    }
}
