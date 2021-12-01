package util;

import java.io.IOException;
import java.io.PrintWriter;
import util.FastScanner;

public abstract class AOCTemplate {

    public final FastScanner reader;
    public static final PrintWriter writer = new PrintWriter(System.out);

    protected AOCTemplate() {
        reader = new FastScanner();
    }

    protected AOCTemplate(String filename) {
        reader = new FastScanner(filename);
    }

    public abstract void solve();
}
