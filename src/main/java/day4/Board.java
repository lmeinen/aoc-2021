package day4;

import java.io.PrintWriter;

public class Board {

    public static final PrintWriter writer = new PrintWriter(System.out);

    private int[][] fields;
    private int[] rowSums;
    private int[] colSums;
    private int sum;
    private int id;

    public Board(int[][] fields, int id) {
        this.fields = fields;
        this.rowSums = new int[fields.length];
        this.colSums = new int[fields.length];
        this.id = id;
        for (var i = 0; i < fields.length; i++) {
            for (var j = 0; j < fields.length; j++) {
                this.sum += fields[i][j];
                this.rowSums[i] += fields[i][j];
                this.colSums[j] += fields[i][j];
            }
        }
    }

    public void setValue(int i, int j, int value) {
        int oldVal = this.fields[i][j];
        this.fields[i][j] = value;
        this.sum += value - oldVal;
        this.rowSums[i] += value - oldVal;
        this.colSums[j] += value - oldVal;
    }


    /**
     * @param value drawing to be marked off
     * @return sum of all unmarked values if bingo, -1 otherwise
     */
    public int markField(int value) {
        for (int i = 0; i < this.fields.length; i++) {
            for (int j = 0; j < this.fields.length; j++) {
                if (this.fields[i][j] == value) {
                    this.sum -= value;
                    this.rowSums[i] -= value;
                    this.colSums[j] -= value;
                    if (this.rowSums[i] == 0 || this.colSums[j] == 0) {
                        return this.sum;
                    } else {
                        return -1;
                    }
                }
            }
        }
        return -1;
    }

    public int getId() {
        return id;
    }
}
