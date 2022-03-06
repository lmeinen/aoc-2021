package day4;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import util.AOCTemplate;

public class Task2 extends AOCTemplate {

    public Task2(String filename) throws IOException {
        super(filename);
    }

    public static void main(String[] args) {
        final Task2 task;
        try {
            task = new Task2("day4.txt");
            task.solve();
        } catch (IOException e) {
            writer.println("Instantiation of task solver failed, aborting...");
        }
        writer.flush();
        writer.close();
    }

    private void preprocess(List<Integer> drawings, HashSet<Integer> boardSet,
        HashMap<Integer, List<Board>> boardMap) {
        writer.println("preprocessing boards...");
        writer.flush();
        // first read drawings
        final var drawingTokens = new StringTokenizer(reader.next(), ",");
        while (drawingTokens.hasMoreTokens()) {
            int draw = Integer.parseInt(drawingTokens.nextToken());
            drawings.add(draw);
            boardMap.put(draw, new LinkedList<>());
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
                boardSet.add(id);
                id++;
            } catch (NullPointerException e) {
                break;
            }
        }
    }

    private int findLastBingo(final LinkedList<Integer> drawings,
        HashSet<Integer> boardSet, final HashMap<Integer, List<Board>> boardMap) {
        // TODO: Why does this work for all inputs but my own?
        writer.println("finding optimal board...");
        writer.flush();
        for (int val : drawings) {
            final var boards = boardMap.get(val);
            for (var board : boards) {
                int sum = board.markField(val);
                if (sum > -1) {
                    if (boardSet.contains(board.getId())) {
                        boardSet.remove(board.getId());
                        writer.flush();
                        if (boardSet.isEmpty()) {
                            writer.println(String.format("solution is %d x %d = %d", sum, val, sum * val));
                            writer.flush();
                            return sum * val;
                        }
                    }
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
        final var boardSet = new HashSet<Integer>();
        final var boardMap = new HashMap<Integer, List<Board>>();
        preprocess(drawings, boardSet, boardMap);
        findLastBingo(drawings, boardSet, boardMap);
    }
}
