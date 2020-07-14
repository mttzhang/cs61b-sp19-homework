package bearmaps;

import java.util.List;

public class KDTree implements PointSet {
    private Node root;
    private static final boolean VERTICAL = true;
    private static final boolean HORIZONTAL = false;

    private class Node {
        private Point p;
        private boolean orientation;
        //downChild
        private Node leftChild;
        //UpperChild
        private Node rightChild;

        Node(Point point, boolean orientation) {
            this.p = point;
            this.orientation = orientation;
        }
    }

    public KDTree(List<Point> points) {
        for (Point p: points) {
            root = addNode(root, p, HORIZONTAL);
        }
    }

    private Node addNode(Node root, Point p, boolean orientation) {
        if (root == null) {
            return new Node(p, orientation);
        }
        if (p.equals(root.p)) {
            return root;
        }
        int cmp = comparePoints(root.p, p, orientation);
        if (cmp > 0) {
            root.leftChild = addNode(root.leftChild, p, !orientation);
        } else {
            root.rightChild = addNode(root.rightChild, p, !orientation);
        }
        return root;

    }

    private int comparePoints(Point p1, Point p2, boolean orientation) {
        if (orientation == HORIZONTAL) {
            return Double.compare(p1.getX(), p2.getX());
        } else {
            return Double.compare(p1.getY(), p2.getY());
        }
    }

    private double dxdyDistanceSquare(Node n, Point best) {
        if (n.orientation == HORIZONTAL) {
            return Math.pow((n.p.getX() - best.getX()), 2);
        } else {
            return Math.pow((n.p.getY() - best.getY()), 2);
        }
    }

    @Override
    public Point nearest(double x, double y) {
        return nearestHelper(new Point(x, y), null, root);
    }

    private Point nearestHelper(Point goal, Point best, Node n) {
        if (best == null) {
            best = n.p;
        }
        if (n == null) {
            return best;
        }
        double bestDis2 = Point.distance(goal, best);
        double dis2 = Point.distance(goal, n.p);
        if (dis2 < bestDis2) {
            best = n.p;
        }
        int cmp = comparePoints(goal, n.p, n.orientation);

        //goal bigger, goodSide = R
        if (cmp > 0) {
            best = nearestHelper(goal, best, n.rightChild);
            if (Point.distance(goal, best) > dxdyDistanceSquare(n, goal)) {
                best = nearestHelper(goal, best, n.leftChild);
            }
        } else {
            best = nearestHelper(goal, best, n.leftChild);
            if (Point.distance(goal, best) > dxdyDistanceSquare(n, goal)) {
                best = nearestHelper(goal, best, n.rightChild);
            }
        }
        return best;
    }
}
