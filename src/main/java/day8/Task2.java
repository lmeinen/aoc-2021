package day8;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import util.AOCTemplate;

public class Task2 extends AOCTemplate {

    public Task2(String filename) throws IOException {
        super(filename);
    }

    public static void main(String[] args) {
        final Task2 task;
        try {
            task = new Task2("day8.txt");
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
        writer.printf("the total sum is %d\n", sum);
        writer.flush();
    }

    private List<Character> difference(Collection<Character> a, Collection<Character> b) {
        final var tmp = new HashSet<Character>(a);
        tmp.removeAll(b);
        return new ArrayList<Character>(tmp);
    }

    private List<Character> union(Collection<Character> a, Collection<Character> b) {
        final var tmp = new HashSet<Character>(a);
        tmp.addAll(b);
        return new ArrayList<Character>(tmp);
    }

    private void computeWireMapping(Display d) {
        List<Set<Character>> combinations = d.getCombinations();
        Map<Character, Character> modToReal = d.getModToReal();
        Set<Character> m1 = null, m4 = null, m7 = null, m8 = null;
        Character mod_a, mod_b, mod_c, mod_d = null, mod_e = null, mod_f = null, mod_g = null;
        for (var c : combinations) {
            if (c.size() == 2) {
                m1 = c;
            } else if (c.size() == 3) {
                m7 = c;
            } else if (c.size() == 4) {
                m4 = c;
            } else if (c.size() == 7) {
                m8 = c;
            }
        }
        mod_a = difference(m7, m1).get(0);
        modToReal.put(mod_a, 'a');
        final var b_and_d = difference(m4, m1);
        for (var c : combinations) {
            List<Character> i = difference(c, union(m1, union(Set.of(mod_a), b_and_d)));
            if (i.size() == 1) {
                mod_g = i.get(0);
                modToReal.put(mod_g, 'g');
                List<Character> j = difference(c, union(Set.of(mod_a, mod_g), b_and_d));
                if (j.size() == 1) { // 5
                    mod_f = j.get(0);
                    modToReal.put(mod_f, 'f');
                    break;
                }
            }
        }

        mod_c = difference(m1, Set.of(mod_f)).get(0);
        modToReal.put(mod_c, 'c');

        mod_e = difference(m8, union(m1, union(Set.of(mod_a, mod_g), b_and_d))).get(0);
        modToReal.put(mod_e, 'e');

        for (var c : combinations) {
            List<Character> i = difference(c, union(m1, Set.of(mod_a, mod_g)));
            if (i.size() == 1) {
                mod_d = i.get(0);
                modToReal.put(mod_d, 'd');
                break;
            }
        }

        mod_b = difference(b_and_d, Set.of(mod_d)).get(0);
        modToReal.put(mod_b, 'b');
    }

    private Map<Set<Character>, Integer> getDigitMapping() {
        final var mapping = new HashMap<Set<Character>, Integer>(10);
        final var s0 = new HashSet<Character>();
        final var s1 = new HashSet<Character>();
        final var s2 = new HashSet<Character>();
        final var s3 = new HashSet<Character>();
        final var s4 = new HashSet<Character>();
        final var s5 = new HashSet<Character>();
        final var s6 = new HashSet<Character>();
        final var s7 = new HashSet<Character>();
        final var s8 = new HashSet<Character>();
        final var s9 = new HashSet<Character>();
        s0.add('a');
        s0.add('b');
        s0.add('c');
        s0.add('e');
        s0.add('f');
        s0.add('g');
        s1.add('c');
        s1.add('f');
        s2.add('a');
        s2.add('c');
        s2.add('d');
        s2.add('e');
        s2.add('g');
        s3.add('a');
        s3.add('c');
        s3.add('d');
        s3.add('f');
        s3.add('g');
        s4.add('b');
        s4.add('c');
        s4.add('d');
        s4.add('f');
        s5.add('a');
        s5.add('b');
        s5.add('d');
        s5.add('f');
        s5.add('g');
        s6.add('a');
        s6.add('b');
        s6.add('d');
        s6.add('e');
        s6.add('f');
        s6.add('g');
        s7.add('a');
        s7.add('c');
        s7.add('f');
        s8.add('a');
        s8.add('b');
        s8.add('c');
        s8.add('d');
        s8.add('e');
        s8.add('f');
        s8.add('g');
        s9.add('a');
        s9.add('b');
        s9.add('c');
        s9.add('d');
        s9.add('f');
        s9.add('g');
        mapping.put(s0, 0);
        mapping.put(s1, 1);
        mapping.put(s2, 2);
        mapping.put(s3, 3);
        mapping.put(s4, 4);
        mapping.put(s5, 5);
        mapping.put(s6, 6);
        mapping.put(s7, 7);
        mapping.put(s8, 8);
        mapping.put(s9, 9);
        return mapping;
    }

    @Override
    public void solve() {
        final var digitMapping = getDigitMapping();
        List<Display> displays = parseInput();
        int sum = 0;
        for (var display : displays) {
            computeWireMapping(display);
            var digits = display.getDigits();
            var val = 0;
            for (int i = 0; i < digits.size(); i++) {
                final var mappedSet = new HashSet<Character>();
                final var d = digits.get(i);
                for (var c : d) {
                    mappedSet.add(display.getModToReal().get(c));
                }
                int mappedVal = digitMapping.get(mappedSet);
                double posMultiplier = Math.pow(10, digits.size() - i - 1);
                val += posMultiplier * mappedVal;
            }
            sum += val;
        }
        printOutput(sum);
    }
}
