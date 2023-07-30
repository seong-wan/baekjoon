import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        ArrayList<Point> points = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            points.add(new Point(x, y));
        }

        // 정렬
        Collections.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                // x좌표가 증가하는 순으로 정렬하고, x좌표가 같으면 y좌표가 증가하는 순서로 정렬
                if (p1.x == p2.x) {
                    return Integer.compare(p1.y, p2.y);
                }
                return Integer.compare(p1.x, p2.x);
            }
        });

        // 출력
        for (Point point : points) {
            System.out.println(point.x + " " + point.y);
        }
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
