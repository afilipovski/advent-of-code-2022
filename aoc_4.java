import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class aoc_4 {
    public static void main(String[] args) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        AtomicInteger p1 = new AtomicInteger();
        AtomicInteger p2 = new AtomicInteger();

        bf.lines().forEach(line -> {
            int[] parts = Arrays.stream(line.split("[-,]")).mapToInt(Integer::parseInt).toArray();
            if ((parts[0] <= parts[2] && parts[1] >= parts[3]) ||
                    (parts[0] >= parts[2] && parts[1] <= parts[3]))
                p1.getAndIncrement();
            if ((parts[0] >= parts[2] && parts[0] <= parts[3]) ||
                    (parts[1] >= parts[2] && parts[1] <= parts[3]) ||
                    (parts[0] <= parts[2] && parts[1] >= parts[2]))
                p2.getAndIncrement();
        });

        System.out.println("P1: " + p1);
        System.out.println("P2: " + p2);
    }
}
