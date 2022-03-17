package day9;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import util.AOCTemplate;

public class Task1 extends AOCTemplate {

    public Task1(String filename) throws IOException {
        super(filename);
    }

    public static void main(String[] args) {
        final Task1 task;
        try {
            task = new Task1("day9.txt");
            task.solve();
        } catch (IOException e) {
            writer.println("Instantiation of task solver failed, aborting...");
        }
        writer.flush();
        writer.close();
    }

    private int[][] parseInput() {
        final var in = new ArrayList<List<Integer>>();
        while (true) {
            try {
                final var s = reader.next();
                final var l = new ArrayList<Integer>();
                for (char c : s.toCharArray()) {
                    l.add(Integer.parseInt(String.valueOf(c)));
                }
                in.add(l);
            } catch (NullPointerException e) {
                writer.println("reached EOF");
                writer.flush();
                break;
            }
        }

        int[][] arr = in.stream()
                .map(l -> l.stream().mapToInt(Integer::intValue).toArray())
                .toArray(int[][]::new);
        return arr;
    }

    private void printOutput(int product) {
        writer.printf("product is %d\n", product);
        writer.flush();
    }

    private int sizeOfBasin(int[][] map, int i, int j) {
        int sum = 1;
        int height = map[i][j];
        map[i][j] = 9; // avoid recursion
        if (i != 0 && map[i - 1][j] != 9 && map[i - 1][j] > height) {
            sum += sizeOfBasin(map, i - 1, j);
        }
        if (i != map.length - 1 && map[i + 1][j] != 9 && map[i + 1][j] > height) {
            sum += sizeOfBasin(map, i + 1, j);
        }
        if (j != 0 && map[i][j - 1] != 9 && map[i][j - 1] > height) {
            sum += sizeOfBasin(map, i, j - 1);
        }
        if (j != map[i].length - 1 && map[i][j + 1] != 9 && map[i][j + 1] > height) {
            sum += sizeOfBasin(map, i, j + 1);
        }
        return sum;
    }

    @Override
    public void solve() {
        final int[][] fieldMap = parseInput();
        // find low points
        final var lowPoints = new ArrayList<int[]>();
        for (int i = 0; i < fieldMap.length; i++) {
            for (int j = 0; j < fieldMap[i].length; j++) {
                if (i == 0 || fieldMap[i - 1][j] > fieldMap[i][j]) {
                    if (j == fieldMap[i].length - 1 || fieldMap[i][j + 1] > fieldMap[i][j]) {
                        if (i == fieldMap.length - 1 || fieldMap[i + 1][j] > fieldMap[i][j]) {
                            if (j == 0 || fieldMap[i][j - 1] > fieldMap[i][j]) {
                                lowPoints.add(new int[] { i, j });
                            }
                        }
                    }
                }
            }
        }

        final var topThreeBasins = new ArrayList<Integer>(3);
        topThreeBasins.add(0);
        topThreeBasins.add(0);
        topThreeBasins.add(0);
        // for each low point, grow basin by following increasing numbers, always
        // setting them to 9 to avoid recursion, and increase size of basin
        // check size of basin against top 3
        for (var lp : lowPoints) {
            int s = sizeOfBasin(fieldMap, lp[0], lp[1]);
            writer.printf("size of basin with low point (%d,%d) is %d\n", lp[0], lp[1], s);
            writer.flush();
            for (int i = 0; i < 3; i++) {
                if (s > topThreeBasins.get(i)) {
                    topThreeBasins.add(i, s);
                    topThreeBasins.remove(3);
                    break;
                }
            }
        }
        printOutput(topThreeBasins.get(0) * topThreeBasins.get(1) * topThreeBasins.get(2));
    }
}
