import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class aoc_10 {
    static TreeMap<Integer, Integer> changeByCycle = new TreeMap<>();

    public static void main(String[] args) {
        int currentCycle = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (String line : br.lines().toList()) {
            if (line.split(" ")[0].equals("addx")) {
                currentCycle += 2;
                changeByCycle.put(currentCycle, Integer.parseInt(line.split(" ")[1]));
            }
            else currentCycle++;
        }

        int reg = 1;

        int p1 = 0;
        StringBuilder p2 = new StringBuilder();

        for (int i = 0; i < 240; i++) {
            if ((i-20)%40 == 0) {
                p1 += reg * i;
            }
            if (changeByCycle.containsKey(i))
                reg += changeByCycle.get(i);
            if (i != 0 && i%40 == 0)
                p2.append("\n");
            if (Math.abs(i%40 - reg) <= 1)
                p2.append("#");
            else p2.append(".");
        }
        System.out.println("P1: " + p1);
        System.out.println("P2:");
        System.out.println(p2);
    }
}
