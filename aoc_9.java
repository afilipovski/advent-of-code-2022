import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

class Position {
    int x;
    int y;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    boolean isAdjacentTo(Position o) {
        return Math.abs(x - o.x) < 2 && Math.abs(y - o.y) < 2;
    }
    double distance(Position p) {
//        System.out.println("Dist " + (Math.pow(this.x-p.x,2) + Math.pow(this.y-p.y,2)));
        return Math.pow(this.x-p.x,2) + Math.pow(this.y-p.y,2);
    }

    void moveTo(int x, int y, ArrayList<Position> positions) {
//        System.out.println("Moving " + this + " to " + "(" + x + "," + y + ")");
        this.x = x;
        this.y = y;

        if (positions.indexOf(this)+1 >= positions.size()) return;
        Position nextPos = positions.get(positions.indexOf(this)+1);

        if (!this.isAdjacentTo(nextPos)) {
            List<Position> potentialPositions = new ArrayList<>();
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    potentialPositions.add(new Position(nextPos.x + i, nextPos.y + j));
                }
            }
            Position p = potentialPositions.stream().min(Comparator.comparingDouble(this::distance)).get();
            nextPos.moveTo(p.x, p.y, positions);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", x,y);
    }
}

public class aoc_9 {
    static List<String> commands;

    static int tailPositions(int ropeLength) {
        HashSet<Position> res = new HashSet<>();
        ArrayList<Position> pos = new ArrayList<>();

        for (int i = 0; i < ropeLength; i++) {
            pos.add(new Position(0,0));
        }
        Position head = pos.get(0);
        Position tail = pos.get(pos.size()-1);

        for (String command : commands) {
            String[] parts = command.split(" ");
            for (int i = 0; i < Integer.parseInt(parts[1]); i++) {
                switch (parts[0]) {
                    case "U" -> head.moveTo(head.x, head.y + 1, pos);
                    case "R" -> head.moveTo(head.x + 1, head.y, pos);
                    case "D" -> head.moveTo(head.x, head.y - 1, pos);
                    case "L" -> head.moveTo(head.x - 1, head.y, pos);
                }
                res.add(new Position(tail.x, tail.y));
            }
        }
        return res.size();
    }

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        commands = br.lines().collect(Collectors.toList());

        System.out.println("P1: " + tailPositions(2));
        System.out.println("P2: " + tailPositions(10));
    }
}
