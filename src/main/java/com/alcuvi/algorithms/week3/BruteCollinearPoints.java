package com.alcuvi.algorithms.week3;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

public class BruteCollinearPoints {

	private Point[] points;

	private List<LineSegment> segments;

	// finds all line segments containing 4 points
	public BruteCollinearPoints(Point[] points) {
		if (points == null) {
			throw new NullPointerException();
		}
		
		this.initializePoints(points);
		this.segments = new ArrayList<LineSegment>();
		this.buildFourPointsCollinearSegments(this.points);
	}

	private void initializePoints(final Point[] basePoints) {

		this.points = new Point[basePoints.length];
		int pointsIndex = 0;
		
		for (final Point point : basePoints) {
		
			if (point == null) {
				throw new IllegalArgumentException();
				
			} else {
				// TODO: check all points in new array. 
				// Better solution prevent loop over Null points allready not copied.
				for (Point allreadyAddedPoint : this.points) {
					if ( allreadyAddedPoint != null && point.equals(allreadyAddedPoint)) {
						throw new IllegalArgumentException(" Point: " + point + " allready exist.");
					}
				}
				points[pointsIndex++] = point;
			}
		}
	}

	// the number of line segments
	public int numberOfSegments() {
		return segments.size();
	}

	// the line segments
	public LineSegment[] segments() {
		
		return buildLineSegmentArray();
	}
	
	private LineSegment[] buildLineSegmentArray() {
		LineSegment[] lineSegments = new LineSegment[segments.size()];
		int i = 0;
		for (LineSegment lineSegment : segments) {
			lineSegments[i++] = lineSegment;
		}
		return lineSegments;
	}

	private void buildFourPointsCollinearSegments(final Point[] points) {
		for (int i = 0; i < points.length; i++) {
			for (int j = i + 1; j < points.length; j++) {
				for (int k = j + 1; k < points.length; k++) {
					for (int l = k + 1; l < points.length; l++) {
						final Point[] tuple = { points[i], points[j],
								points[k], points[l] };
						if (areCollinear(tuple)) {
							// Order the 4 points
							Arrays.sort(tuple);
							// Ad the first and last points in the collinear line
							this.segments.add(new LineSegment(tuple[0], tuple[3]));
						}
					}
				}
			}
		}
	}

	private boolean areCollinear(final Point[] points) {
		final double firstSlope = points[0].slopeTo(points[1]);
		boolean collinear = true;
		int i = 2;
		while (i < points.length && collinear) {
			final double slope = points[0].slopeTo(points[i]);
			if (slope != firstSlope) {
				collinear = false;
			}
			i++;
		}
		return collinear;
	}

	private void drawSegments() {
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		for (final LineSegment segment : this.segments) {
			segment.draw();
			System.out.println(segment);
		}
		StdDraw.setPenColor();
	}

	private void drawPoints() {
		StdDraw.setPenColor(StdDraw.BLUE);
		for (final Point p : this.points) {
			p.draw();
		}
		StdDraw.setPenColor();
	}

	public static void main(String[] args) {
		final Point[] points = buildPoints(args);
		final BruteCollinearPoints collinear = new BruteCollinearPoints(points);
		setUpDraw();
		collinear.drawPoints();
		collinear.drawSegments();
		StdDraw.show();
	}

	private static void setUpDraw() {
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		StdDraw.setPenRadius(0.005);
	}

	private static Point[] buildPoints(String[] args) {

		In in = new In(args[0]);
		int n = in.readInt();
		Point[] points = new Point[n];

		for (int i = 0; i < n; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
		}
		return points;
	}
}
