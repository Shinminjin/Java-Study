package study.javacoding.chapter02;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

// 부분 범위가 0부터 길이까지 범위에 속하는지 검사
@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem045 {

    public class Function {
        private static final int N_UPPER_BOUND = 101;
        private final int n;

        public Function(int n) {
            // n < 0 || n >= N_UPPER_BOUND : IndexOutOfBoundsException
            this.n = Objects.checkIndex(n, N_UPPER_BOUND);
        }

        public int yMinusX(int x, int y) {
            // x < 0 || x > y || y >= n : IndexOutOfBoundsException
            Objects.checkFromToIndex(x, y, n);

            return y - x;
        }
    }

    @Test
    void yMinusX_메소드에서_x_y_가_부분범위에_속하는지_확인() {
        Function f = new Function(50);

        // 1. 범위에 속하지 않을 경우
        // given : x < 0 || x > y || y >= n
        // when
        IndexOutOfBoundsException e = Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> { f.yMinusX(30, 20); }
        );

        // then
        assertThat(e.getMessage()).isEqualTo("Range [30, 20) out of bounds for length 50");

        // 2. 범위에 속할 경우
        // given : 0 <= x <= y < n
        // then
        assertThat(f.yMinusX(10, 10)).isEqualTo(0);
    }
}
