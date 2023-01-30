import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.function.Function;

class Monkey {
    int ordinal;

    ArrayList<Integer> items = new ArrayList<>();
    Function<Integer, Integer> operation;
    int testDivisor;

    int sendOnTrue;
    int sendOnFalse;
    
    int timesInspected = 0;

    public Monkey(Scanner s) {
        String[] parts = s.nextLine().split("\\s+");
        ordinal = Integer.parseInt(parts[1].substring(0,parts[1].length()-1));

        parts = s.nextLine().split("\\s+");
        for (int i = 3; i < parts.length; i++) {
            String toParse = parts[i];
            if (i != parts.length-1)
                toParse = toParse.substring(0,toParse.length()-1);
            items.add(Integer.parseInt(toParse));
        }

        parts = s.nextLine().split("\\s+");
        String secondOperand = parts[6];
        if (parts[5].equals("*") && secondOperand.equals("old"))
            operation = old -> old * old;
        else if (parts[5].equals("*"))
            operation = old -> old * Integer.parseInt(secondOperand);
        else if (parts[5].equals("+") && secondOperand.equals("old"))
            operation = old -> old + old;
        else
            operation = old -> old + Integer.parseInt(secondOperand);

        testDivisor = Integer.parseInt(s.nextLine().split("\\s+")[4]);

        sendOnTrue = Integer.parseInt(s.nextLine().split("\\s+")[6]);
        sendOnFalse = Integer.parseInt(s.nextLine().split("\\s+")[6]);
    }

    void turn(ArrayList<Monkey> monkeys) {
        for (int i = 0; i < items.size(); i++) {
            timesInspected++;
            items.set(i, operation.apply(items.get(i))); //monkey inspects item
//            items.set(i, items.get(i)/3); //monkey gets bored with item
            int item = items.get(i);
            items.remove(i--);
            if (item % testDivisor == 0)
                monkeys.get(sendOnTrue).items.add(item);
            else monkeys.get(sendOnFalse).items.add(item);
        }
    }

    @Override
    public String toString() {
        return "Monkey{" +
                "ordinal=" + ordinal +
                ", items=" + items +
                ", operation=" + operation +
                ", testDivisor=" + testDivisor +
                ", sendOnTrue=" + sendOnTrue +
                ", sendOnFalse=" + sendOnFalse +
                ", timesInspected=" + timesInspected +
                '}';
    }
}
public class aoc_11 {
    public static void main(String[] args) {
        ArrayList<Monkey> monkeys = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            monkeys.add(new Monkey(s));
            if (s.hasNextLine())
                s.nextLine();
        }

        for (int i = 0; i < 10000; i++) {
            for (Monkey monkey : monkeys) {
                monkey.turn(monkeys);
            }
        }

        monkeys.forEach(System.out::println);
        int max = monkeys.stream().mapToInt(m -> m.timesInspected).max().orElse(0);
        int min = monkeys.stream().map(m -> m.timesInspected).sorted(Comparator.reverseOrder()).skip(1).findFirst().orElse(0);

        System.out.println("max " + max + "min " + min);
    }
}
