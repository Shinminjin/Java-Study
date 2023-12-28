package study.javacoding.chapter02.problem053.shallow.copy.constructor;

import lombok.Getter;
import lombok.Setter;
import study.javacoding.chapter02.problem053.shallow.Radius;

@Getter
@Setter
public class Point {

    private double x;
    private double y;
    private Radius radius;

    public Point() {
    }

    public Point(double x, double y, Radius radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public Point(Point another) {
        this.x = another.x;
        this.y = another.y;
        this.radius = another.radius;
    }
}
