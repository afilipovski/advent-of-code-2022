import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Item {
    Directory parent;
    String name;

    public Item(Directory parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    abstract int getSize();
}
class Directory extends Item {
    List<Item> contents = new ArrayList<>();

    public Directory(Directory parent, String name) {
        super(parent,name);
    }

    int getSize() {
        return contents.stream()
                .mapToInt(Item::getSize).sum();
    }
    int p1() {
        int res = 0;
        if (getSize() <= 100000)
            res += getSize();
        for (Item i : contents) {
            if (i instanceof Directory)
                res += ((Directory) i).p1();
        }
        return res;
    }
    int p2(int rootSize) {
        if (getSize() < rootSize - 40000000)
            return Integer.MAX_VALUE;
        int res = getSize();
        for(Item i : contents) {
            if (i instanceof Directory)
                res = Math.min(res, ((Directory) i).p2(rootSize));
        }
        return res;
    }
}
class File extends Item {
    int size;

    public File(Directory parent, String name, int size) {
        super(parent,name);
        this.size = size;
    }

    int getSize() {
        return size;
    }
}
public class aoc_7 {
    public static void main(String[] args) {
        Directory root = new Directory(null, "/");

        Directory current = root;
        Scanner s = new Scanner(System.in);
        while(s.hasNextLine()) {
            String[] lineParts = s.nextLine().split("\\s+");
            if (lineParts[1].equals("cd")) {
                if (lineParts[2].equals("/"))
                    current = root;
                if (lineParts[2].equals(".."))
                    current = current.parent;
                else {
                    Directory newDirectory = new Directory(current, lineParts[2]);
                    current.contents.add(newDirectory);
                    current = newDirectory;
                }
            }
            if (Character.isDigit(lineParts[0].charAt(0))) {
                current.contents.add(
                        new File(current,lineParts[1],Integer.parseInt(lineParts[0])));
            }
        }

        System.out.println("P1: " + root.p1());
        System.out.println("P2: " + root.p2(root.getSize()));
    }
}
