import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {

	private Node root = null;
	private int size = 0;

	private static class Node {
		private Point2D p; // the point
		private RectHV rect; // the axis-aligned rectangle corresponding to this node
		private Node lb; // the left/bottom subtree
		private Node rt; // the right/top subtree

		public Node(final Point2D p, final double xmin, final double ymin, final double xmax, final double ymax) {
			this.p = p;
			this.rect = new RectHV(xmin, ymin, xmax, ymax);
		}
	}

	// construct an empty set of points
	public KdTree() {
	}

	// is the set empty?
	public boolean isEmpty() {
		return this.root == null;
	}

	// number of points in the set
	public int size() {
		return this.size;
	}

	// add the point to the set (if it is not already in the set)
	public void insert(final Point2D newPoint) {

		// root node is allways vertical and his rectangle is the entire area
		if (this.root == null) {
			this.root = new Node(newPoint, 0d, 0d, 1d, 1d);
			this.size++;

		} else {
			put(this.root, newPoint, true);
		}
	}

	private void put(final Node node, final Point2D newPoint, final Boolean vertical) {

		// point already in the tree
		if (node.p.equals(newPoint)) {
			return;
		}

		if (vertical) {

			// left subtree
			if (newPoint.x() <= node.p.x()) {
				if (node.lb == null) {
					node.lb = new Node(newPoint, node.rect.xmin(), node.rect.ymin(), node.p.x(), node.rect.ymax());
					this.size++;

				} else {
					put(node.lb, newPoint, !vertical);
				}

				// right subtree
			} else {
				if (node.rt == null) {
					node.rt = new Node(newPoint, node.p.x(), node.rect.ymin(), node.rect.xmax(), node.rect.ymax());
					this.size++;

				} else {
					put(node.rt, newPoint, !vertical);
				}
			}

			// horizontal
		} else {

			// left subtree
			if (newPoint.y() <= node.p.y()) {
				if (node.lb == null) {
					node.lb = new Node(newPoint, node.rect.xmin(), node.rect.ymin(), node.rect.xmax(), node.p.y());
					this.size++;

				} else {
					put(node.lb, newPoint, !vertical);
				}

				// right subtree
			} else {
				if (node.rt == null) {
					node.rt = new Node(newPoint, node.rect.xmin(), node.p.y(), node.rect.xmax(), node.rect.ymax());
					this.size++;

				} else {
					put(node.rt, newPoint, !vertical);
				}
			}
		}

	}

	// does the set contain point p?
	public boolean contains(final Point2D p) {
		return get(p) != null;
	}

	private Node get(final Point2D p) {
		return get(this.root, p, true);
	}

	private Node get(final Node node, final Point2D point, final Boolean vertical) {

		if (node == null) {
			return null;
		}

		if (node.p.equals(point)) {
			return node;
		}

		if (vertical) {
			if (point.x() <= node.p.x()) {
				return get(node.lb, point, !vertical);
			} else {
				return get(node.rt, point, !vertical);
			}

		} else {
			if (point.y() <= node.p.y()) {
				return get(node.lb, point, !vertical);
			} else {
				return get(node.rt, point, !vertical);
			}
		}
	}

	// draw all points to standard draw
	public void draw() {
		draw(this.root, true);
	}

	private void draw(Node node, boolean vertical) {

		if (node != null) {
			paintNode(node.p);

			if (vertical) {
				drawVericalLine(node);

			} else {
				drawHorizontalLine(node);
			}

			draw(node.lb, !vertical);
			draw(node.rt, !vertical);
		}
	}

	private void paintNode(final Point2D point) {
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(.01);
		point.draw();
	}

	private void drawVericalLine(Node node) {
		final Point2D point1 = new Point2D(node.p.x(), node.rect.ymin());
		final Point2D point2 = new Point2D(node.p.x(), node.rect.ymax());
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.setPenRadius();
		point1.drawTo(point2);
	}

	private void drawHorizontalLine(Node node) {
		final Point2D point1 = new Point2D(node.rect.xmin(), node.p.y());
		final Point2D point2 = new Point2D(node.rect.xmax(), node.p.y());
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.setPenRadius();
		point1.drawTo(point2);
	}

	// all points that are inside the rectangle
	public Iterable<Point2D> range(final RectHV rect) {
		final Stack<Point2D> points = new Stack<>();
		return range(this.root, points, rect);
	}

	private Iterable<Point2D> range(final Node node, final Stack<Point2D> points, final RectHV rectangle) {

		if (node != null) {
			if (rectangle.contains(node.p)) {
				points.push(node.p);
			}

			if (node.lb != null && rectangle.intersects(node.lb.rect)) {
				range(node.lb, points, rectangle);
			}

			if (node.rt != null && rectangle.intersects(node.rt.rect)) {
				range(node.rt, points, rectangle);
			}
		}

		return points;
	}

	// a nearest neighbor in the set to point p; null if the set is empty
	public Point2D nearest(final Point2D point) {
		return nearest(this.root, point, null, true);
	}

	private Point2D nearest(final Node node, final Point2D point, final Point2D oldNearestPoint, final boolean vertical) {

		Point2D nearestPoint = oldNearestPoint;

		if (node != null) {
			// first point evaluated is nearest since another is evaluated
			if (nearestPoint == null) {
				nearestPoint = node.p;
				
			} else {
				if (point.distanceSquaredTo(node.p) < point.distanceSquaredTo(nearestPoint)) {
					nearestPoint = node.p;
				}
			}

			if (vertical) { 
				final Point2D verticalRefPoint = new Point2D(node.p.x(), point.y());
				
				if (point.x() < node.p.x()) {
					nearestPoint = nearest(node.lb, point, nearestPoint, !vertical);

					if (point.distanceSquaredTo(nearestPoint) > point.distanceSquaredTo(verticalRefPoint)) {
						nearestPoint = nearest(node.rt, point, nearestPoint, !vertical);
					}
				} else { 
					nearestPoint = nearest(node.rt, point, nearestPoint, !vertical);

					if (point.distanceSquaredTo(nearestPoint) > point.distanceSquaredTo(verticalRefPoint)) {
						nearestPoint = nearest(node.lb, point, nearestPoint, !vertical);
					}
				}
			} else { 
				final Point2D hoizontalRefPoint = new Point2D(point.x(), node.p.y());

				if (point.y() < node.p.y()) {
					nearestPoint = nearest(node.lb, point, nearestPoint, !vertical);

					if (point.distanceSquaredTo(nearestPoint) > point.distanceSquaredTo(hoizontalRefPoint)) {
						nearestPoint = nearest(node.rt, point, nearestPoint, !vertical);
					}
				} else {
					nearestPoint = nearest(node.rt, point, nearestPoint, !vertical);

					if (point.distanceSquaredTo(nearestPoint) > point.distanceSquaredTo(hoizontalRefPoint)) {
						nearestPoint = nearest(node.lb, point, nearestPoint, !vertical);
					}
				}
			}
		}

		return nearestPoint;
	}

	// unit testing of the methods (optional)
	public static void main(String[] args) {
		
	}
}