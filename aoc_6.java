import java.util.Scanner;
import java.util.stream.IntStream;

public class aoc_6 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String string = s.next();
        int i;
        for (i = 3; i < string.length(); i++) {
            long count = IntStream.range(i-3, i+1)
                    .mapToObj(string::charAt)
                    .distinct().count();
            if (count == 4) break;
        }
        System.out.println("P1: " + (i+1));
        int j;
        for (j = 13; j < string.length(); j++) {
            long count = IntStream.range(j-13, j+1)
                    .mapToObj(string::charAt)
                    .distinct().count();
            if (count == 14) break;
        }
        System.out.println("P2: " + (j+1));
    }
}
