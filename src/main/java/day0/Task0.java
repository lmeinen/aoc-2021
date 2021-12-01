package day0;

import java.io.IOException;
import util.AOCTemplate;

public class Task0 extends AOCTemplate {

    public Task0(String filename) throws IOException {
        super(filename);
    }

    public static void main(String[] args) {
        final Task0 task;
        try {
            task = new Task0("day0_task0.txt");
            task.solve();
        } catch (IOException e) {
            writer.println("Instantiation of task solver failed, aborting...");
        }
        writer.flush();
        writer.close();
    }

    @Override
    public void solve() {
        writer.println("Read: " + reader.next());
    }
}
