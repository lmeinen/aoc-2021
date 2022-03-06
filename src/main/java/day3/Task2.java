package day3;

import java.io.IOException;
import util.AOCTemplate;

public class Task2 extends AOCTemplate {

    public Task2(String filename) throws IOException {
        super(filename);
    }

    public static void main(String[] args) {
        final Task2 task;
        try {
            task = new Task2("day3.txt");
            task.solve();
        } catch (IOException e) {
            writer.println("Instantiation of task solver failed, aborting...");
        }
        writer.flush();
        writer.close();
    }

    @Override
    public void solve() {
        final var top = new BitNode();
        var cntr = 0;
        while (true) {
            try {
                var bitstring = reader.next();
                cntr++;
                var currnode = top;
                top.incrNumTouches();
                for (int i = 0; i < bitstring.length(); i++) {
                    if (bitstring.charAt(i) == '1') {
                        currnode = currnode.addOne();
                        currnode.incrNumTouches();
                    } else {
                        currnode = currnode.addZero();
                        currnode.incrNumTouches();
                    }
                }
            } catch (NullPointerException e) {
                break;
            }
        }
        // TODO: Exercise essentially asks to always take node with most/fewest numTouches, that's
        // it.
        writer.printf("Finished processing %d strings\n", cntr);
        var oxy = getVal(top, true);
        var co2 = getVal(top, false);
        writer.printf("%d x %d = %d\n", oxy, co2, oxy * co2);
    }

    private int getVal(BitNode top, boolean takeMsb) {
        var val = 0;
        BitNode curr = top;
        while (curr.getZero() != null || curr.getOne() != null) {
            val <<= 1;
            if (curr.getZero() == null) {
                val += 1;
                curr = curr.getOne();
            } else if (curr.getOne() == null) {
                curr = curr.getZero();
            } else if (curr.getZero().getNumTouches() <= curr.getOne().getNumTouches()) {
                val += takeMsb ? 1 : 0;
                curr = takeMsb ? curr.getOne() : curr.getZero();
            } else {
                val += takeMsb ? 0 : 1;
                curr = takeMsb ? curr.getZero() : curr.getOne();
            }
        }
        writer.printf("returning val %d\n", val);
        return val;
    }
}
