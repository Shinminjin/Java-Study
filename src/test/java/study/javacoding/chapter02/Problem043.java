package study.javacoding.chapter02;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem043 {

    @Getter
    public class Car {
        private final String name;
        private final Color color;

        /**
         * Objects.requireNonNullElse()
         * Objects.requireNonNullElseGet()
         * <p>
         * 널을 검사할 참조와
         * 검사한 참조가 null 일 경우, 반환할 null 이 아닌 기본참조
         * 두 인자를 받는다.
         */
        public Car(String name, Color color) {
            this.name = Objects.requireNonNullElse(name, "No name");
            this.color = Objects.requireNonNullElseGet(color, () -> new Color(0, 0, 0));
        }
    }

    @Test
    void null_검사_수행_후_null_아닌_기본_참조_반환_확인() {
        // given : name 이 null 일 경우
        Car car1 = new Car(null, Color.BLUE);

        // then : 기본 참조(No name) 반환
        assertThat(car1.getName()).isEqualTo("No name");

        //------------------------------------------------------------------

        // given : color 가 null 일 경우
        Car car2 = new Car("Mazda", null);

        // then : 기본 참조(Color(0,0,0)) 반환
        assertThat(car2.getColor()).isEqualTo(new Color(0, 0, 0));
    }
}
