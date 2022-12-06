import java.util.*;

public class aoc_5 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        List<LinkedList<Character>> p1 = null;
        List<LinkedList<Character>> p2 = null;

        String row = s.nextLine();
        while(row.charAt(1) != '1') {
            if (p1 == null) {
                p1 = new ArrayList<>();
                for (int i = 0; i < (row.length()+1)/4; i++) {
                    p1.add(new LinkedList<>());
                }
                p2 = new ArrayList<>();
                for (int i = 0; i < (row.length()+1)/4; i++) {
                    p2.add(new LinkedList<>());
                }
            }
            for (int i = 1; i < row.length(); i+=4) {
                if (Character.isUpperCase(row.charAt(i))) {
                    p1.get((i + 1) / 4).addFirst(row.charAt(i));
                    p2.get((i + 1) / 4).addFirst(row.charAt(i));
                }
            }
            row = s.nextLine();
        }

        s.nextLine();

        try {
            while (s.hasNextLine()) {
                s.next();
                int quantity = s.nextInt();
                s.next();
                int from = s.nextInt() - 1;
                s.next();
                int to = s.nextInt() - 1;

                for (int i = 0; i < quantity; i++) {
                    assert p1 != null;
                    p1.get(to).addLast(p1.get(from).removeLast());
                }
                LinkedList<Character> lTemp = new LinkedList<>();
                for (int i = 0; i < quantity; i++) {
                    lTemp.addFirst(p2.get(from).removeLast());
                }
                for (int i = 0; i < quantity; i++) {
                    p2.get(to).addLast(lTemp.removeFirst());
                }
            }
        }
        catch (Exception ignored) {}

        System.out.print("P1: ");
        assert p1 != null;
        for (LinkedList<Character> list : p1) {
            System.out.print(list.getLast());
        }
        System.out.println();
        System.out.print("P2: ");
        for (LinkedList<Character> list : p2) {
            System.out.print(list.getLast());
        }
    }
}
