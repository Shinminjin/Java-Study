package study.javacoding.chapter02.problem053.shallow.copy.constructor;

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

    public Point(Point another) {
        this.x = another.x;
        this.y = another.y;
    }
}
