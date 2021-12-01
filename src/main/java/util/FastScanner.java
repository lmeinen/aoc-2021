package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

public class FastScanner {
    private final BufferedReader br;
    private StringTokenizer st = new StringTokenizer("");

    public FastScanner() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public FastScanner(String filename) {
        final var streamReader =
                new InputStreamReader(
                        getClass().getClassLoader().getResourceAsStream(filename),
                        StandardCharsets.UTF_8);
        br = new BufferedReader(streamReader);
    }

    public String next() {
        while (!st.hasMoreTokens())
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
            }
        return st.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}
