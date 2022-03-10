package day6;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import util.AOCTemplate;

public class Task2 extends AOCTemplate {

    public Task2(String filename) throws IOException {
        super(filename);
    }

    public static void main(String[] args) {
        final Task2 task;
        try {
            task = new Task2("day6.txt");
            task.solve();
        } catch (IOException e) {
            writer.println("Instantiation of task solver failed, aborting...");
        }
        writer.flush();
        writer.close();
    }

    private List<Long> parseInput() {
        final var fish = new ArrayList<Long>(9);
        for (int i = 0; i < 9; i++) {
            fish.add(0l);
        }
        String[] timers = reader.next().split(",");
        for (var t : timers) {
            int val = Integer.parseInt(t);
            fish.set(val, (long) fish.get(val) + 1);
        }
        return fish;
    }

    private void printOutput(List<Long> fish, int day) {
        long sum = 0;
        for (long f : fish) {
            sum += f;
        }
        writer.printf("There are %d fish on day %d\n", sum, day);
        writer.flush();
    }

    @Override
    public void solve() {
        List<Long> fish = parseInput();
        int day0 = 0;
        int day = 0;
        int len = fish.size();
        for (; day < 256; day++) {
            printOutput(fish, day);
            long numBirths = fish.get(day0);
            day0 = (day0 + 1) % len;
            int day6 = (day0 + 6) % len;
            fish.set(day6, fish.get(day6) + numBirths);
        }
        printOutput(fish, day);
    }
}
