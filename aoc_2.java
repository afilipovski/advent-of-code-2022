import java.util.Scanner;

public class aoc_2 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int pointsPtOne = 0;
        int pointsPtTwo = 0;

        while(s.hasNextLine()) {
            String[] a = s.nextLine().split("\\s+");
            int[] game = {a[0].charAt(0) - 'A', a[1].charAt(0) - 'X'};

            pointsPtOne += game[1] + 1;
            if (game[0] == game[1]) pointsPtOne += 3;
            else if (game[1] == (game[0]+1)%3) pointsPtOne += 6;

            pointsPtTwo += 3 * game[1];
            //0 -> -1, gubi, 1 -> 0 nereseno, 2 -> 1 pobeda
            pointsPtTwo += (3 + game[0] + --game[1])%3 + 1;

        }
        System.out.println("P1: " + pointsPtOne);
        System.out.println("P2: " + pointsPtTwo);
    }
}
