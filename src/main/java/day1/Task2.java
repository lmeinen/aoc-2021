package day1;

import java.io.IOException;
import util.AOCTemplate;

public class Task2 extends AOCTemplate {

    public Task2(String filename) throws IOException {
        super(filename);
    }

    public static void main(String[] args) {
        final Task2 task;
        try {
            task = new Task2("day1.txt");
            task.solve();
        } catch (IOException e) {
            writer.println("Instantiation of task solver failed, aborting...");
        }
        writer.flush();
        writer.close();
    }

    private int nextIndex(int i) {
        return (i + 1) % 3;
    }

    @Override
    public void solve() {
        Integer runningSum = null;
        var currWindow = new int[3];
        var i = 0;
        var numIncreases = 0;
        while (true) {
            try {
                var prevVal = currWindow[i];
                currWindow[i] = reader.nextInt();
                if (runningSum == null && i == 2) {
                    runningSum = currWindow[0] + currWindow[1] + currWindow[2];
                } else if (runningSum != null) {
                    var prevSum = runningSum;
                    runningSum = runningSum - prevVal + currWindow[i];
                    if (prevSum < runningSum) {
                        numIncreases++;
                    }
                }
                i = nextIndex(i);
            } catch (NullPointerException e) {
                break;
            }
        }
        writer.println("Number of window increases: " + numIncreases);
    }
}
