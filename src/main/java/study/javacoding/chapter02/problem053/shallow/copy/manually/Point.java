package study.javacoding.chapter02.problem053.shallow.copy.manually;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Point {

    private double x;
    private double y;

    public Point() {
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point clonePoint() {

        Point point = new Point();
        point.setX(this.x);
        point.setY(this.y);

        return point;
    }
}
