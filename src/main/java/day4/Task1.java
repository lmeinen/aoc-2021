package day4;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import util.AOCTemplate;

public class Task1 extends AOCTemplate {

    public Task1(String filename) throws IOException {
        super(filename);
    }

    public static void main(String[] args) {
        final Task1 task;
        try {
            task = new Task1("day4.txt");
            task.solve();
        } catch (IOException e) {
            writer.println("Instantiation of task solver failed, aborting...");
        }
        writer.flush();
        writer.close();
    }

    private void preprocess(List<Integer> drawings, HashMap<Integer, List<Board>> boardMap) {
        writer.println("preprocessing boards...");
        writer.flush();
        // first read drawings
        final var drawingTokens = new StringTokenizer(reader.next(), ",");
        while (drawingTokens.hasMoreTokens()) {
            int draw = Integer.parseInt(drawingTokens.nextToken());
            drawings.add(draw);
            boardMap.put(draw, new LinkedList<Board>());
        }
        // then the actual boards
        int id = 0;
        while (true) {
            try {
                int[][] board = new int[5][5];
                final var currBoard = new Board(board, id);
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board.length; j++) {
                        int value = reader.nextInt();
                        currBoard.setValue(i, j, value);
                        boardMap.get(value).add(currBoard);
                    }
                }
                id++;
            } catch (NullPointerException e) {
                break;
            }
        }
    }

    private int findBingo(final LinkedList<Integer> drawings, final HashMap<Integer, List<Board>> boardMap) {
        writer.println("finding optimal board...");
        writer.flush();
        for (int val : drawings) {
            final var boards = boardMap.get(val);
            for (var board : boards) {
                int sum = board.markField(val);
                if (sum > -1) {
                    writer.println(String.format("solution is %d x %d = %d", sum, val, sum * val));
                    writer.flush();
                    return sum * val;
                } else if (sum < -1) {
                    writer.println("something went wrong...");
                    writer.flush();
                    return 0;
                }
            }
        }
        return -1;
    }

    @Override
    public void solve() {
        final var drawings = new LinkedList<Integer>();
        final var boardMap = new HashMap<Integer, List<Board>>();
        preprocess(drawings, boardMap);
        findBingo(drawings, boardMap);
    }
}
