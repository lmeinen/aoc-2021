package day6;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import util.AOCTemplate;

public class Task1 extends AOCTemplate {

    public Task1(String filename) throws IOException {
        super(filename);
    }

    public static void main(String[] args) {
        final Task1 task;
        try {
            task = new Task1("day6.txt");
            task.solve();
        } catch (IOException e) {
            writer.println("Instantiation of task solver failed, aborting...");
        }
        writer.flush();
        writer.close();
    }

    private List<Integer> parseInput() {
        final var fish = new ArrayList<Integer>(9);
        for (int i = 0; i < 9; i++) {
            fish.add(0);
        }
        String[] timers = reader.next().split(",");
        for (var t : timers) {
            int val = Integer.parseInt(t);
            fish.set(val, fish.get(val) + 1);
        }
        return fish;
    }

    private void printOutput(List<Integer> fish, int day) {
        int sum = 0;
        for (int f : fish) {
            sum += f;
        }
        writer.printf("There are %d fish on day %d\n", sum, day);
        writer.flush();
    }

    @Override
    public void solve() {
        List<Integer> fish = parseInput();
        int day0 = 0;
        int day = 0;
        int len = fish.size();
        for (; day < 80; day++) {
            printOutput(fish, day);
            int numBirths = fish.get(day0);
            day0 = (day0 + 1) % len;
            int day6 = (day0 + 6) % len;
            fish.set(day6, fish.get(day6) + numBirths);
        }
        printOutput(fish, day);
    }
}
