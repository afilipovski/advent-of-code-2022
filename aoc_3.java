import java.util.Scanner;

public class aoc_3 {
    static int itemPriority(char c) {
        if (Character.isLowerCase(c))
            return c - 'a' + 1;
        else return c - 'A' + 27;
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String[] strings = new String[3];

        int p1 = 0;
        int p2 = 0;

        while(s.hasNextLine()) {
            //fill array, for each string update p1 result
            for (int k = 0; k < 3; k++) {
                strings[k] = s.nextLine();
                for (int i = 0; i < strings[k].length()/2; i++) {
                    int j;
                    for (j = strings[k].length()/2; j < strings[k].length(); j++) {
                        if (strings[k].charAt(i) == strings[k].charAt(j)) {
                            //System.out.println("For char " + strings[k.charAt(i) + " we add " + itemPriority(strings[k.charAt(i)));
                            p1 += itemPriority(strings[k].charAt(i));
                            break;
                        }
                    }
                    if (j != strings[k].length())
                        break;
                }
            }
            //for each group of 3 strings, find common item type and update p2 result

            for (int i = 0; i < strings[0].length(); i++) {
                int j;
                for (j = 0; j < strings[1].length(); j++) {
                    if (strings[0].charAt(i) == strings[1].charAt(j)) break;
                }
                if (j == strings[1].length()) continue;
                for (j = 0; j < strings[2].length(); j++) {
                    if (strings[0].charAt(i) == strings[2].charAt(j)) break;
                }
                if (j == strings[2].length()) continue;

                p2 += itemPriority(strings[0].charAt(i));
                break;
            }

        }
        System.out.println("P1: " + p1);
        System.out.println("P2: " + p2);
    }
}
