package day3;

import java.io.IOException;
import util.AOCTemplate;

public class Task1 extends AOCTemplate {

    public Task1(String filename) throws IOException {
        super(filename);
    }

    public static void main(String[] args) {
        final Task1 task;
        try {
            task = new Task1("day3.txt");
            task.solve();
        } catch (IOException e) {
            writer.println("Instantiation of task solver failed, aborting...");
        }
        writer.flush();
        writer.close();
    }

    @Override
    public void solve() {
        int[] counters = null;
        while (true) {
            try {
                var bitstring = reader.next();
                if (counters == null) {
                    counters = new int[bitstring.length()];
                }
                for (int i = 0; i < counters.length; i++) {
                    if (bitstring.charAt(i) == '1') {
                        counters[i]++;
                    } else {
                        counters[i]--;
                    }
                }
            } catch (NullPointerException e) {
                break;
            }
        }
        var gamma = 0;
        var epsilon = 0;
        for (int x : counters) {
            gamma <<= 1;
            epsilon <<= 1;
            if (x < 0) {
                epsilon += 1;
            } else {
                gamma += 1;
            }
        }
        writer.printf("%d x %d = %d\n", gamma, epsilon, gamma * epsilon);
    }
}