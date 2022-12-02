import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class aoc_1 {
    static List<Integer> l = new ArrayList<>();

    public static void main(String[] args) {
        int calories = 0;
        Scanner s = new Scanner(System.in);
        while(s.hasNextLine()) {
            String input = s.nextLine();
            if (!input.isEmpty())
                calories += Integer.parseInt(input);
            else {
                l.add(calories);
                calories = 0;
            }
        }
        l = l.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println("P1: " + l.get(0));
        System.out.println("P2: " + (l.get(0) + l.get(1) + l.get(2)));
    }
}
