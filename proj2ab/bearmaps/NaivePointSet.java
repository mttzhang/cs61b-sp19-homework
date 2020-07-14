package bearmaps;

import edu.princeton.cs.algs4.In;

import java.util.List;

public class NaivePointSet implements PointSet{
    List<Point> points;
    public NaivePointSet(List<Point> points) {
        this.points = points;
    }
    @Override
    public Point nearest(double x, double y) {
        Point point = new Point(x, y);
        double min = Integer.MAX_VALUE;
        Point nearestP = new Point(0, 0);
        for (Point p: points) {
            double dis = Point.distance(point, p);
            if (dis < min) {
                min = dis;
                nearestP = new Point(p.getX(), p.getY());
            }
        }
        return nearestP;
    }

    public static void main(String[] args) {
        Point p1 = new Point(1.1, 2.2);
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0);
        System.out.println(ret.getX());
        System.out.println(ret.getY());
    }
}
