package study.javacoding.chapter02;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem042 {

    public class Car {
        private final String name;
        private final Color color;

        public Car(String name, Color color) {
            this.name = MyObjects.requireNonNullElseThrow(
                    name,
                    new UnsupportedOperationException("Name cannot be set as null")
            );
            this.color = MyObjects.requireNonNullElseThrow(
                    color,
                    new UnsupportedOperationException("Color cannot be set as null")
            );
        }

        public void assignDriver(String license, Point location) {
            MyObjects.requireNonNullElseThrowIAE(license, "Licence cannot be null");
            MyObjects.requireNonNullElseThrowIAE(location, () -> "Location cannot be null");
        }
    }

    public final class MyObjects {

        private MyObjects() {
            throw new AssertionError("Cannot be instantiated");
        }

        public static <T> T requireNonNullElseThrowIAE(T obj, String message) {
            if (obj == null) {
                throw new IllegalArgumentException(message);
            }

            return obj;
        }

        public static <T> T requireNonNullElseThrowIAE(T obj,Supplier<String> messageSupplier) {
            if (obj == null) {
                throw new IllegalArgumentException(messageSupplier == null
                        ? null : messageSupplier.get());
            }

            return obj;
        }

        public static <T, X extends Throwable> T requireNonNullElseThrow(T obj, X exception) throws X {
            if (obj == null) {
                throw exception;
            }

            return obj;
        }

        public static <T, X extends Throwable> T requireNotNullElseThrow(T obj, Supplier<? extends X> exceptionSupplier) throws X {
            if (obj != null) {
                return obj;
            } else {
                throw exceptionSupplier.get();
            }
        }
    }

    @Test
    void null_참조검사_수행_후_명시된_예외_던지기_확인() {
        // when
        UnsupportedOperationException constructorError = Assertions.assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    Car car = new Car("Mazda", null);
                }
        );

        // then
        assertThat(constructorError.getMessage())
                .isEqualTo("Color cannot be set as null");

        // when
        IllegalArgumentException methodError = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Car car = new Car("Mazda", Color.BLACK);
                    car.assignDriver(null, new Point(1, 1));
                }
        );

        // then
        assertThat(methodError.getMessage())
                .isEqualTo("Licence cannot be null");
    }
}
