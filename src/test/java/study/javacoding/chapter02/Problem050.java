package study.javacoding.chapter02;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

// 불변 클래스로 가변 객체 전달
@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem050 {

    // 가변객체 Radius
    @Getter
    @Setter
    public class Radius {
        private int start;
        private int end;
    }

    @Getter
    @AllArgsConstructor
    public final class Point1 {
        private final double x;
        private final double y;
        private final Radius radius;
    }

    @Test
    void 가변객체_전달시_불변클래스_불변성_파괴확인1() {
        Radius r = new Radius();
        r.setStart(0);
        r.setEnd(120);

        Point1 p = new Point1(1.23, 4.12, r);
        assertThat(p.getRadius().getStart()).isEqualTo(0);

        r.setStart(5);
        assertThat(p.getRadius().getStart()).isEqualTo(5);
        // 서로 다른 결과를 반환
        // p의 상태가 변했으므로 Point 는 불변이 아님
    }

    @Getter
    public final class Point2 {
        private final double x;
        private final double y;
        private final Radius radius;

        public Point2(double x, double y, Radius radius) {
            this.x = x;
            this.y = y;

            Radius clone = new Radius();
            clone.setStart(radius.getStart());
            clone.setEnd(radius.getEnd());
            this.radius = clone;
        }
    }

    @Test
    void 가변객체_전달시_불변클래스_불변성_파괴확인2() {
        Radius r = new Radius();
        r.setStart(0);
        r.setEnd(120);

        // radius 필드는 r을 복제한 것이므로
        // r.setStart(5)를 호출해도 radius 필드에 영향을 미치지 않음
        Point2 p = new Point2(1.23, 4.12, r);
        assertThat(p.getRadius().getStart()).isEqualTo(0);

        r.setStart(5);
        assertThat(p.getRadius().getStart()).isEqualTo(0);

        //------------------------------------------------------------------

        // 해결해야할 문제
        assertThat(p.getRadius().getStart()).isEqualTo(0);

        // 불변이 깨짐
        p.getRadius().setStart(5);
        assertThat(p.getRadius().getStart()).isEqualTo(5);
    }

    // 해법 : getRadius() 메서드에서 radius 필드의 복제본을 반환하도록 수정
    public final class Point {
        private final double x;
        private final double y;
        private final Radius radius;

        public Point(double x, double y, Radius radius) {
            this.x = x;
            this.y = y;

            Radius clone = new Radius();
            clone.setStart(radius.getStart());
            clone.setEnd(radius.getEnd());
            this.radius = clone;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        // getRadius() 메서드에서 radius 필드의 복제본을 반환하도록 수정
        public Radius getRadius() {
            Radius clone = new Radius();
            clone.setStart(this.radius.getStart());
            clone.setEnd(this.radius.getEnd());

            return clone;
        }
    }
    @Test
    void 가변객체_전달시_불변클래스_불변성_유지확인() {
        Radius r = new Radius();
        r.setStart(0);
        r.setEnd(120);

        Point p = new Point(1.23, 4.12, r);
        assertThat(p.getRadius().getStart()).isEqualTo(0);

        // 불변유지
        r.setStart(5);
        assertThat(p.getRadius().getStart()).isEqualTo(0);

        //------------------------------------------------------------------

        assertThat(p.getRadius().getStart()).isEqualTo(0);

        // 불변유지
        p.getRadius().setStart(5);
        assertThat(p.getRadius().getStart()).isEqualTo(0);
    }
}
