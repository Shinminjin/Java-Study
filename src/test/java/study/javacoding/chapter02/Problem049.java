package study.javacoding.chapter02;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

// 불변 클래스 작성
@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem049 {

    // 확장을 막기 위해 클래스를 final 로 표시
    // 다른 클래스에서 이 클래스를 확장할 수 없으니, 메서드를 오버라이딩 할 수 없음
    public final class Point {

        // 모든 필드를 private 과 final 로 선언
        // 다른 클래스에 노출되지 않고, 클래스 생성자에서 딱 한번 초기화
        private final double x;
        private final double y;

        // 클래스는 매개변수로 필드를 초기화 하는 public 생성자를 포함해야 함
        // (혹은 private 생성자와 인스턴스를 생성하는 팩터리 메서드)
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        // 클래스는 필드의 게터를 제공해야 함
        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        // 클래스는 세터를 노출하지 않아야 함
    }

    @Test
    void 불변객체_생성_후_값_확인() {
        Point p = new Point(1.2, 33.4);

        // then
        assertThat(p.getX()).isEqualTo(1.2);
        assertThat(p.getY()).isEqualTo(33.4);
    }
}
