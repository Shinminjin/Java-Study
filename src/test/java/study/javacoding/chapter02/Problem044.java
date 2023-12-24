package study.javacoding.chapter02;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

// 인덱스가 0부터 길이까지 범위에 속하는지 검사
@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem044 {

    public class Function {
        private static final int X_UPPER_BOUND = 11;
        private static final int Y_UPPER_BOUND = 16;
        private final int x;

        public Function(int x) {
            // x < 0 || x >= X_UPPER_BOUND : IndexOutOfBoundsException
            this.x = Objects.checkIndex(x, X_UPPER_BOUND);
        }

        public int xMinusY(int y) {
            // y < 0 || y >= x : IndexOutOfBoundsException
            Objects.checkIndex(y, x);

            return x - y;
        }

        public static int oneMinusY(int y) {
            // y < 0 || y >= Y_UPPER_BOUND : IndexOutOfBoundsException
            Objects.checkIndex(y, Y_UPPER_BOUND);

            return 1 - y;
        }
    }

    @Test
    void 생성시_x가_범위에_속하는지_확인() {
        // given : x가 0 <= x < X_UPPER_BOUND(11) 속하지 않을 경우
        // when
        IndexOutOfBoundsException e = Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> {new Function(11);}
        );

        // then
        assertThat(e.getMessage()).isEqualTo("Index 11 out of bounds for length 11");
    }

    @Test
    void oneMinusY_메소드에서_y가_범위에_속하는지_확인() {
        // 1. 범위에 속하지 않을 경우
        // given : y < 0 || y >= Y_UPPER_BOUND(16)
        // when
        IndexOutOfBoundsException e = Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> { Function.oneMinusY(16); }
        );

        // then
        assertThat(e.getMessage()).isEqualTo("Index 16 out of bounds for length 16");

        //------------------------------------------------------------------

        // 2. 범위에 속할 경우
        // given : 0 <= y < Y_UPPER_BOUND(16)
        // then
        assertThat(Function.oneMinusY(15)).isEqualTo(-14);
    }

    @Test
    void xMinusY_메소드에서_y가_범위에_속하는지_확인() {
        // 1. 범위에 속하지 않을 경우
        // given: y < 0 || y >= x
        Function f = new Function(4);

        // when
        IndexOutOfBoundsException e = Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> { f.xMinusY(4); }
        );

        // then
        assertThat(e.getMessage()).isEqualTo("Index 4 out of bounds for length 4");

        //------------------------------------------------------------------

        // 2. 범위에 속할 경우
        // given : 0 <= y < x
        // then
        assertThat(f.xMinusY(3)).isEqualTo(1);
    }
}
