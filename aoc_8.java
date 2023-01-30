import java.util.Scanner;

public class aoc_8 {
    static int[][] trees = null;

    static boolean isVisible(int y, int x) {
        int i;
        for (i = 0; i < x; i++)
            if (trees[y][i] >= trees[y][x]) break;
        if (i == x) return true;
        for (i = x+1; i < trees.length; i++)
            if (trees[y][i] >= trees[y][x]) break;
        if (i == trees.length) return true;
        for (i = 0; i < y; i++)
            if (trees[i][x] >= trees[y][x]) break;
        if (i == y) return true;
        for (i = y+1; i < trees.length; i++)
            if (trees[i][x] >= trees[y][x]) break;
        return i == trees.length;
    }
    static int directionalScenic(int y, int x, char axis, int direction) {
        int res = 0;
        for (int i = axis == 'X' ? x+direction : y+direction;
             direction == 1 ? (i < trees.length) : (i >= 0);
             i += direction) {
            res++;
            if ((axis == 'X' ? trees[y][i] : trees[i][x]) >= trees[y][x]) break;
        }
        return res;
    }
    static int scenicScore(int y, int x) {
        return directionalScenic(y,x,'X', -1) *
                directionalScenic(y,x,'X', +1) *
                directionalScenic(y,x,'Y', -1) *
                directionalScenic(y,x,'Y', +1);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        for (int i = 0; true; i++) {
            String line = s.nextLine();
            if (trees == null) trees = new int[line.length()][line.length()];
            for (int j = 0; j < line.length(); j++) {
                trees[i][j] = line.charAt(j) - '0';
            }
            if (i == trees.length-1) break;
        }
        int p1 = 0; int p2 = 0;
        for (int i = 0; i < trees.length; i++) {
            for (int j = 0; j < trees.length; j++) {
                p1 = p1 + (isVisible(i, j) ? 1 : 0);
                p2 = Math.max(p2, scenicScore(i, j));
            }
        }
        System.out.printf("P1: %d\nP2: %d", p1, p2);
    }
}