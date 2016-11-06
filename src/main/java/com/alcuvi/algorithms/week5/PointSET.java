import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Stack;

public class PointSET {

	private SET<Point2D> rbBST;

	// construct an empty set of points
	public PointSET() {
		this.rbBST = new SET<>();
	}

	// is the set empty?
	public boolean isEmpty() {
		return this.rbBST.isEmpty();
	}

	// number of points in the set
	public int size() {
		return this.rbBST.size();
	}

	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		if (!this.rbBST.contains(p)) {
			this.rbBST.add(p);
		}
	}

	// does the set contain point p?
	public boolean contains(Point2D p) {
		return this.rbBST.contains(p);
	}

	// draw all points to standard draw
	public void draw() {
		for (Point2D point2d : this.rbBST) {
			point2d.draw();
		}
	}

	// all points that are inside the rectangle
	public Iterable<Point2D> range(final RectHV rect) {
		if (rect == null) {
			throw new NullPointerException("rect is null");
		}
		final Stack<Point2D> points = new Stack<>();

		for (Point2D point2d : this.rbBST) {
			if (rect.contains(point2d)) {
				points.push(point2d);
			}
		}

		return points;
	}

	// a nearest neighbor in the set to point p; null if the set is empty
	public Point2D nearest(Point2D p) {
		Point2D nearest = null;

		for (Point2D neighbor : this.rbBST) {
			
			// first neighbor is the nearest at first iteration
			if (nearest == null) {
				nearest = neighbor;
				
			} else {
				if (p.distanceSquaredTo(neighbor) < p.distanceSquaredTo(nearest)) {
					nearest = neighbor;
				}
			}
		}

		return nearest;
	}

	// unit testing of the methods (optional)
	public static void main(String[] args) {
		
		
	}

}