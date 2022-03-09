package day5;

import java.util.HashSet;
import java.util.Set;

public class Vent {
    private final Point p1;
    private final Point p2;

    public Vent(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Point getP2() {
        return p2;
    }

    public Point getP1() {
        return p1;
    }

    // Assume (x,y) already lies in valid interval
    private boolean liesOnVent(int x, int y) {
        return p1.getX() == p2.getX() || p1.getY() == p2.getY() || Math.abs(p1.getX() - x) == Math.abs(p1.getY() - y);
    }

    private int[] intersectIntervals(int a1, int a2, int b1, int b2) {
        int c1 = a1, c2 = a2, d1 = b1, d2 = b2;
        if (a2 < a1) {
            c1 = a2;
            c2 = a1;
        }
        if (b2 < b1) {
            d1 = b2;
            d2 = b1;
        }
        if (c1 > d2 || c1 > d2) {
            return null;
        } else {
            return new int[] { Math.max(c1, d1), Math.min(c2, d2) };
        }
    }

    public Set<Point> intersect(Vent other) {
        final var intersection = new HashSet<Point>();
        Point p1 = this.p1;
        Point p2 = this.p2;
        Point q1 = other.getP1();
        Point q2 = other.getP2();
        final int[] x_int = intersectIntervals(p1.getX(), p2.getX(), q1.getX(), q2.getX());
        final int[] y_int = intersectIntervals(p1.getY(), p2.getY(), q1.getY(), q2.getY());
        if (x_int != null && y_int != null) {
            for (int i = x_int[0]; i <= x_int[1]; i++) {
                for (int j = y_int[0]; j <= y_int[1]; j++) {
                    if (this.liesOnVent(i, j) && other.liesOnVent(i, j)) {
                        intersection.add(new Point(i, j));
                    }
                }
            }
        }
        return intersection;
    }
}