package day7;

import java.io.IOException;
import java.util.Arrays;

import util.AOCTemplate;

public class Task1 extends AOCTemplate {

    public Task1(String filename) throws IOException {
        super(filename);
    }

    public static void main(String[] args) {
        final Task1 task;
        try {
            task = new Task1("day7.txt");
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

    @Override
    public void solve() {
        int[] crabs = parseInput();
        Arrays.sort(crabs);
        int median;
        if (crabs.length % 2 == 1) {
            median = crabs[crabs.length / 2 + 1];
        } else {
            median = Math.round((crabs[crabs.length / 2 - 1] + crabs[crabs.length / 2]) / 2);
        }
        int cost = 0;
        for (int p : crabs) {
            cost += Math.abs(p - median);
        }
        printOutput(cost, median);
    }
}
