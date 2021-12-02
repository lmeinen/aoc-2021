package day2;

import java.io.IOException;
import util.AOCTemplate;

public class Task1 extends AOCTemplate {

    public Task1(String filename) throws IOException {
        super(filename);
    }

    public static void main(String[] args) {
        final Task1 task;
        try {
            task = new Task1("day2.txt");
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
        while (true) {
            try {
                var cmd = reader.next();
                var val = reader.nextInt();
                switch (cmd) {
                    case "forward" -> hPos += val;
                    case "down" -> vPos += val;
                    case "up" -> vPos -= val;
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
