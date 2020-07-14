package bearmaps;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    public static void test1(){
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(4, 4);

        KDTree kd = new KDTree(List.of(p1, p2, p3, p4, p5, p6 ,p7));
        NaivePointSet ns = new NaivePointSet(List.of(p1, p2, p3, p4, p5, p6 ,p7));
        Point ret = kd.nearest(0, 7);
        Point ret1 = ns.nearest(0, 7);
        System.out.println("TEST 1:");
        System.out.println("result by KDTree: Point (" + ret.getX() + ", " +ret.getY() + ")");
        System.out.println("result by NaivePointSet: Point (" + ret1.getX() + ", " +ret1.getY() + ")");
        System.out.println("-----------------");
    }
    public static void test2(){
        Point p1 = new Point(4.5, 3);
        Point p2 = new Point(0.0, 2);
        Point p3 = new Point(4, 2.6);
        Point p4 = new Point(4.3, 5);
        Point p5 = new Point(9.7, 3);
        Point p6 = new Point(1, 8);
        Point p7 = new Point(2, 6);

        KDTree kd = new KDTree(List.of(p1, p2, p3, p4, p5, p6 ,p7));
        NaivePointSet ns = new NaivePointSet(List.of(p1, p2, p3, p4, p5, p6 ,p7));
        Point ret = kd.nearest(3, 4);
        Point ret1 = ns.nearest(3, 4);
        System.out.println("TEST 2:");
        System.out.println("result by KDTree: Point (" + ret.getX() + ", " +ret.getY() + ")");
        System.out.println("result by NaivePointSet: Point (" + ret1.getX() + ", " +ret1.getY() + ")");
        System.out.println("-----------------");
    }
    public static void main(String[] args) {
        test1();
        test2();
    }
}
