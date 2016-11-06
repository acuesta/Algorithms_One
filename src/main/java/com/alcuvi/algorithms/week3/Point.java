package com.alcuvi.algorithms.week3;


import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

	private int x;
	private int y;

	// constructs the point (x, y)
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// draws this point
	public void draw() {
		StdDraw.point(x, y);
	}

	// draws the line segment from this point to that point
	public void drawTo(Point that) {
		StdDraw.line(this.x, this.y, that.x, that.y);
	}
	

	public Comparator<Point> slopeOrder() {
		return new SlopeOrder();
	}
	
	// string representation
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}

	// compare two points by y-coordinates, breaking ties by x-coordinates
	// the invoking point (x0, y0) is less than the argument point (x1, y1)
	// if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
	public int compareTo(Point that) {
		if (this.y < that.y) {
			return -1;
		} else if (this.y > that.y) {
			return 1;
		}
		if (this.x < that.x) {
			return -1;
		} else if (this.x > that.x) {
			return 1;
		}
		return 0;

	}

	// the slope between this point and that point
	// The slopeTo() method should return the slope between the invoking point (x0, y0)
	// and the argument point (x1, y1), which is given by the formula (y1 − y0) / (x1 − x0).
	// Treat the slope of a horizontal line segment as positive zero;
	// treat the slope of a vertical line segment as positive infinity;
	// treat the slope of a degenerate line segment (between a point and itself)
	// as negative infinity.
	public double slopeTo(Point that) {

		double slope;
		double horizontalDistance = that.x - this.x;
		double verticalDistance = that.y - this.y;

		if (verticalDistance == 0 && horizontalDistance == 0) {
			slope = Double.NEGATIVE_INFINITY;
			
		} else if (horizontalDistance == 0) {
			slope = Double.POSITIVE_INFINITY;
			
		} else if (verticalDistance == 0) {
			slope = Double.valueOf(0d);
			
		} else {
			slope = verticalDistance / horizontalDistance;
		}
		return slope;
	}
	
	public static void main(String[] args) {
	}
	
	private class SlopeOrder implements Comparator<Point> {

        public int compare(final Point q, final Point r) {
            int compare = 0;
            final double slopeThisToQ = slopeTo(q);
            final double slopeThisToR = slopeTo(r);

            if (slopeThisToQ < slopeThisToR) {
                compare = -1;
            } else if (slopeThisToQ > slopeThisToR) {
                compare = 1;
            }
            return compare;
        }
    }

}
