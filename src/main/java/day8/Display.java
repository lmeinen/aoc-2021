package day8;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Display {
    private final List<Set<Character>> combinations;
    private final List<Set<Character>> digits;
    private final Map<Character, Character> modToReal;

    public Display(List<Set<Character>> combinations, List<Set<Character>> digits) {
        this.combinations = combinations;
        this.digits = digits;
        this.modToReal = new HashMap<>(7);
    }

    public Map<Character, Character> getModToReal() {
        return modToReal;
    }

    public List<Set<Character>> getDigits() {
        return digits;
    }

    public List<Set<Character>> getCombinations() {
        return combinations;
    }

}