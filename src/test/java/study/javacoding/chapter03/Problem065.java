package study.javacoding.chapter03;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem065 {

    public final class DateTimes {
        private DateTimes() {
            // AssertionError: 의도한 것과 다른 결과가 발생하는 문제를 식별하는 데 도움
            throw new AssertionError("Cannot be instantiated");
        }

        public static Date addOrSubtractYears(Date date, int years) {

            if (date == null) {
                throw new IllegalArgumentException("Date cannot be null");
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.YEAR, years);

            return calendar.getTime();
        }

        public static Date addOrSubtractHours(Date date, int hours) {

            if (date == null) {
                throw new IllegalArgumentException("Date cannot be null");
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.HOUR_OF_DAY, hours);
            return calendar.getTime();
        }
    }

    @Test
    void Date_사용해서_날짜와_시간_더하기_빼기() {
        log.info("Working with Date");

        Date date = new Date();
        Date dateAfterAddingYears = DateTimes.addOrSubtractYears(date, 2);
        Date dateAfterSubtractingYears = DateTimes.addOrSubtractYears(date, -2);
        log.info("Before adding 2 years: {}", date);
        log.info("After adding 2 years: {}", dateAfterAddingYears);
        log.info("After subtracting 2 years: {}", dateAfterSubtractingYears);

        Date dateAfterAddingHours = DateTimes.addOrSubtractHours(date, 5);
        Date dateAfterSubtractingHours = DateTimes.addOrSubtractHours(date, -5);
        log.info("Before adding 5 hours: {}", date);
        log.info("After adding 5 hours: {}", dateAfterAddingHours);
        log.info("After subtracting 5 hours: {}", dateAfterSubtractingHours);
    }

    @Test
    void LocalDateTime_사용해서_날짜와_시간_더하기_빼기() {
        log.info("Working with LocalDateTime (similar for LocalDate, ZonedDateTime, OffsetDateTime)");

        LocalDateTime ldt = LocalDateTime.now();
        LocalDateTime ldtAfterAddingMinutes = ldt.plusMinutes(10);
        LocalDateTime ldtAfterSubtractingMinutes = ldt.minusMinutes(10);

        log.info("Before adding 10 minutes: {}", ldt);
        log.info("After adding 10 minutes: {}", ldtAfterAddingMinutes);
        log.info("After subtracting 10 minutes: {}", ldtAfterSubtractingMinutes);
    }

    @Test
    void Instant_사용해서_날짜와_시간_더하기_빼기() {
        log.info("Working with Instant");

        Instant timestamp = Instant.now();
        Instant timestampAfterAddingHours = timestamp.plus(5, ChronoUnit.HOURS);
        Instant timestampAfterSubtractingHours = timestamp.minus(5, ChronoUnit.HOURS);

        log.info("Before adding 5 hours: {}", timestamp);
        log.info("After adding 5 hours: {}", timestampAfterAddingHours);
        log.info("After subtracting 5 hours: {}", timestampAfterSubtractingHours);
    }
}
