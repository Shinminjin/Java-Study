package study.javacoding.chapter02;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem041 {

    public record OldCar(String name, Color color) {
        public OldCar {
            if (name == null) {
                throw new NullPointerException("Car name cannot be null");
            }

            if (color == null) {
                throw new NullPointerException("Car color cannot be null");
            }

        }

        public void assignDriver(String license, Point location) {

            if (license == null) {
                throw new NullPointerException("Driver license cannot be null");
            }

            if (location == null) {
                throw new NullPointerException("Start location cannot be null");
            }
        }
    }

    @Test
    void OldCar_레코드에서_NullPointerException_던지기() {
        // 1.
        // given : 생성자 파라미터 중 name 이 null 일 경우
        // when
        NullPointerException e1 = Assertions.assertThrows(
                NullPointerException.class,
                () -> {
                    new OldCar(null, Color.BLACK);
                }
        );

        // then
        assertThat(e1.getMessage())
                .isEqualTo("Car name cannot be null");


        // 2.
        // given: 메소드 파라미터 중 license 가 null 일 경우
        // when
        NullPointerException e2 = Assertions.assertThrows(
                NullPointerException.class,
                () -> {
                    OldCar oldCar = new OldCar("Mazda", Color.BLACK);
                    oldCar.assignDriver(null, new Point(1, 1));
                }
        );

        // then
        assertThat(e2.getMessage())
                .isEqualTo("Driver license cannot be null");
    }

    public record NewCar(String name, Color color) {
        /**
         * Objects.requireNonNull()
         * <p>
         * 명시된 참조가 null 이면 제공받은 메시지로 NullPointerException 던지고,
         * null 이 아니면 검사한 참조를 반환함.
         */
        public NewCar(String name, Color color) {
            this.name = Objects.requireNonNull(name, "Car name cannot be null");
            this.color = Objects.requireNonNull(color, "Car color cannot be null");
        }

        public void assignDriver(String license, Point location) {
            Objects.requireNonNull(license, "Driver license cannot be null");
            Objects.requireNonNull(location, "Start location cannot be null");
        }
    }

    @Test
    void NewCar_레코드에서_NullPointerException_던지기() {
        // 1.
        // given : 생성자 파라미터 중 color 가 null 일 경우
        // when
        NullPointerException e1 = Assertions.assertThrows(
                NullPointerException.class,
                () -> {
                    new NewCar("Mazda", null);
                }
        );

        // then
        assertThat(e1.getMessage())
                .isEqualTo("Car color cannot be null");


        // 2.
        // given: 메소드 파라미터 중 license 가 null 일 경우
        // when
        NullPointerException e2 = Assertions.assertThrows(
                NullPointerException.class,
                () -> {
                    NewCar newCar = new NewCar("Mazda", Color.BLACK);
                    newCar.assignDriver(null, new Point(1, 1));
                }
        );

        // then
        assertThat(e2.getMessage())
                .isEqualTo("Driver license cannot be null");
    }
}
