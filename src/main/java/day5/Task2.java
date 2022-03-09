package day5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.AOCTemplate;

public class Task2 extends AOCTemplate {

    public Task2(String filename) throws IOException {
        super(filename);
    }

    public static void main(String[] args) {
        final Task2 task;
        try {
            task = new Task2("day5.txt");
            task.solve();
        } catch (IOException e) {
            writer.println("Instantiation of task solver failed, aborting...");
        }
        writer.flush();
        writer.close();
    }

    private ArrayList<Vent> parseInput() {
        final var vents = new ArrayList<Vent>();
        while (true) {
            try {
                String[] p1 = reader.next().split(",");
                reader.next();
                String[] p2 = reader.next().split(",");
                Point p = new Point(Integer.parseInt(p1[0]), Integer.parseInt(p1[1]));
                Point q = new Point(Integer.parseInt(p2[0]), Integer.parseInt(p2[1]));
                final var v = new Vent(p, q);
                vents.add(v);
            } catch (NullPointerException e) {
                writer.println("Reached EOF");
                writer.flush();
                break;
            }
        }
        return vents;
    }

    private void printOutput(Set<Point> intersections) {
        writer.printf("Detected %d intersections\n", intersections.size());
        writer.flush();
    }

    @Override
    public void solve() {
        final List<Vent> vents = parseInput();
        final var intersections = new HashSet<Point>();
        int len = vents.size();
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                intersections.addAll(vents.get(i).intersect(vents.get(j)));
            }
        }
        printOutput(intersections);
    }
}
