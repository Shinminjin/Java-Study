package study.javacoding.chapter02.problem053.shallow.copy.clone;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Point implements Cloneable {

    private double x;
    private double y;

    public Point() {
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Point clone() throws CloneNotSupportedException {
        return (Point) super.clone();
    }
}
