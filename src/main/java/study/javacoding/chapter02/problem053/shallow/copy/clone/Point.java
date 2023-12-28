package study.javacoding.chapter02.problem053.shallow.copy.clone;

import lombok.Getter;
import lombok.Setter;
import study.javacoding.chapter02.problem053.shallow.Radius;

@Getter
@Setter
public class Point implements Cloneable {

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

    @Override
    public Point clone() throws CloneNotSupportedException {
        return (Point) super.clone();
    }
}
