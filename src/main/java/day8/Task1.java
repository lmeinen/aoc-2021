package day8;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import util.AOCTemplate;

public class Task1 extends AOCTemplate {

    public Task1(String filename) throws IOException {
        super(filename);
    }

    public static void main(String[] args) {
        final Task1 task;
        try {
            task = new Task1("day8.txt");
            task.solve();
        } catch (IOException e) {
            writer.println("Instantiation of task solver failed, aborting...");
        }
        writer.flush();
        writer.close();
    }

    private List<Display> parseInput() {
        final var challenge = new ArrayList<Display>();
        while (true) {
            try {
                final var combinations = new ArrayList<Set<Character>>(10);
                final var digits = new ArrayList<Set<Character>>(4);
                for (int i = 0; i < 10; i++) {
                    final var uniqueComb = new HashSet<Character>();
                    String s = reader.next();
                    for (int j = 0; j < s.length(); j++) {
                        uniqueComb.add(s.charAt(j));
                    }
                    combinations.add(uniqueComb);
                }
                reader.next();
                for (int i = 0; i < 4; i++) {
                    final var digit = new HashSet<Character>();
                    String s = reader.next();
                    for (int j = 0; j < s.length(); j++) {
                        digit.add(s.charAt(j));
                    }
                    digits.add(digit);
                }
                challenge.add(new Display(combinations, digits));
            } catch (NullPointerException e) {
                writer.println("reached EOF");
                writer.flush();
                break;
            }
        }
        return challenge;
    }

    private void printOutput(int sum) {
        writer.printf("digits 1, 4, 7, or 8 appear %d times\n", sum);
        writer.flush();
    }

    private Map<Set<Character>, Integer> getDigitMapping() {
        final var mapping = new HashMap<Set<Character>, Integer>(10);
        final var s1 = new HashSet<Character>();
        // TODO: Instantiate
        return mapping;
    }

    @Override
    public void solve() {
        final var digitMapping = getDigitMapping();
        // TODO: Solve (see notes - check for more efficient solutions)
        List<Display> displays = parseInput();
        int sum = 0;
        for (var display : displays) {
            for (var d : display.getDigits()) {
                if (d.size() == 2 || d.size() == 3 || d.size() == 4 || d.size() == 7) {
                    sum++;
                }
            }
        }
        printOutput(sum);
    }
}
