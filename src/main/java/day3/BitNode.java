package day3;

public class BitNode {

    private int numTouches;

    private BitNode zero = null;
    private BitNode one = null;

    public BitNode getZero() {
        return zero;
    }

    public BitNode getOne() {
        return one;
    }

    public BitNode addZero() {
        if (zero == null) {
            zero = new BitNode();
        }
        return zero;
    }

    public BitNode addOne() {
        if (one == null) {
            one = new BitNode();
        }
        return one;
    }

    public int getNumTouches() {
        return numTouches;
    }

    public void incrNumTouches() {
        this.numTouches++;
    }
}
