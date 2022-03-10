package day7;

import java.io.IOException;
import java.util.Arrays;

import util.AOCTemplate;

public class Task2 extends AOCTemplate {

    public Task2(String filename) throws IOException {
        super(filename);
    }

    public static void main(String[] args) {
        final Task2 task;
        try {
            task = new Task2("day7.txt");
            task.solve();
        } catch (IOException e) {
            writer.println("Instantiation of task solver failed, aborting...");
        }
        writer.flush();
        writer.close();
    }

    private int[] parseInput() {
        String[] positions = reader.next().split(",");
        final var submarines = new int[positions.length];
        for (int i = 0; i < positions.length; i++) {
            var p = positions[i];
            submarines[i] = Integer.parseInt(p);
        }
        return submarines;
    }

    private void printOutput(int sum, int h) {
        writer.printf("It costs a total of %d fuel to align the crabs on position %d\n", sum, h);
        writer.flush();
    }

    private int findCost(int[] crabs, int pos) {
        int s1 = 0;
        int s2 = 0;
        for (int p : crabs) {
            int m1 = (int) Math.pow((pos - p), 2);
            s1 += m1;
            int m2 = Math.abs(pos - p);
            s2 += m2;
        }
        return (s1 + s2) / 2;
    }

    @Override
    public void solve() {
        int[] crabs = parseInput();
        // optimal position is somewhere in [l, r]
        int l = 0;
        int r = crabs.length - 1;
        int pos = (l + r) / 2;
        int cost = findCost(crabs, pos);
        while (l != r) {
            if (pos > l) {
                if (cost < findCost(crabs, pos - 1)) {
                    l = pos;
                } else {
                    r = pos - 1;
                }
            }
            if (pos < r) {
                if (cost < findCost(crabs, pos + 1)) {
                    r = pos;
                } else {
                    l = pos + 1;
                }
            }
            pos = (l + r) / 2;
            cost = findCost(crabs, pos);
        }
        printOutput(cost, pos);
    }
}
