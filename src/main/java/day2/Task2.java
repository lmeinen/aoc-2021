package day2;

import java.io.IOException;
import util.AOCTemplate;

public class Task2 extends AOCTemplate {

    public Task2(String filename) throws IOException {
        super(filename);
    }

    public static void main(String[] args) {
        final Task2 task;
        try {
            task = new Task2("day2.txt");
            task.solve();
        } catch (IOException e) {
            writer.println("Instantiation of task solver failed, aborting...");
        }
        writer.flush();
        writer.close();
    }

    @Override
    public void solve() {
        var hPos = 0;
        var vPos = 0;
        var aim = 0;
        while (true) {
            try {
                var cmd = reader.next();
                var val = reader.nextInt();
                switch (cmd) {
                    case "forward" -> {
                        hPos += val;
                        vPos += aim * val;
                    }
                    case "down" -> aim += val;
                    case "up" -> aim -= val;
                    default -> throw new UnsupportedOperationException("Unknown cmd: " + cmd);
                }
            } catch (NullPointerException e) {
                writer.println("Reached EOF");
                break;
            }
        }
        writer.printf("At (H: %d, V: %d) --> %d%n", hPos, vPos, hPos * vPos);
    }
}
