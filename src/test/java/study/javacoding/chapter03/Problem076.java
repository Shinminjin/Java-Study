package study.javacoding.chapter03;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem076 {

    @Test
    void 자바8_이전_두_날짜_간_차이_구하기() {
        log.info("Before JDK 8");
        Calendar c1 = Calendar.getInstance();
        c1.set(2023, 0, 1);
        Calendar c2 = Calendar.getInstance();
        c2.set(2024, 2, 1);

        log.info("Date/Calendar case: {} <-> {}", c1.getTime(), c2.getTime());
        long inMs = Math.abs(c1.getTimeInMillis() - c2.getTimeInMillis());
        long inDays = Math.abs(TimeUnit.DAYS.convert(inMs, TimeUnit.MILLISECONDS));

        log.info("Difference in milliseconds is: {}", inMs);
        log.info("Difference in days is: {}", inDays);
    }

    @Test
    void 자바8_이후_두_날짜_간_차이_구하기() {
        log.info("Starting with JDK 8");
        LocalDate ld1 = LocalDate.of(2023, 1, 1);
        LocalDate ld2 = LocalDate.of(2024, 2, 1);
        log.info("LocalDate case: {} <-> {}", ld1, ld2);

        long betweenInDays = Math.abs(ChronoUnit.DAYS.between(ld1, ld2));
        long betweenInMonths = Math.abs(ChronoUnit.MONTHS.between(ld1, ld2));
        long betweenInYears = Math.abs(ChronoUnit.YEARS.between(ld1, ld2));
        long untilInDays = Math.abs(ld1.until(ld2, ChronoUnit.DAYS));
        long untilInMonths = Math.abs(ld1.until(ld2, ChronoUnit.MONTHS));
        long untilInYears = Math.abs(ld1.until(ld2, ChronoUnit.YEARS));

        Period period = ld1.until(ld2);
        log.info("Difference as Period: {}y{}m{}d",
                period.getYears(),period.getMonths(), period.getDays());
        log.info("Difference in days is via between(): {}", betweenInDays);
        log.info("Difference in months is via between(): {}", betweenInMonths);
        log.info("Difference in years is via between(): {}", betweenInYears);
        log.info("Difference in days is via until(): {}", untilInDays);
        log.info("Difference in months is via until(): {}", untilInMonths);
        log.info("Difference in years is via until(): {}", untilInYears);

        LocalDateTime ldt1 = LocalDateTime.of(2023, 12, 27, 22, 15, 15);
        LocalDateTime ldt2 = LocalDateTime.of(2023, 12, 27, 23, 15, 15);

        log.info("LocalDateTime cse: {} <-> {}", ldt1, ldt2);
        long betweenInMinutesWithoutZone = Math.abs(ChronoUnit.MINUTES.between(ldt1, ldt2));
        long untilInMinutesWithoutZone = Math.abs(ldt1.until(ldt2, ChronoUnit.HOURS));
        log.info("Difference in minutes without zone: {}", betweenInMinutesWithoutZone);
        log.info("Difference in hours without zone: {}", untilInMinutesWithoutZone);

        log.info("ZonedDateTime case:");
        ZonedDateTime zdt1 = ldt1.atZone(ZoneId.of("Europe/Bucharest"));
        ZonedDateTime zdt2 = zdt1.withZoneSameInstant(ZoneId.of("Australia/Perth")).plusHours(1);
        ZonedDateTime zdt3 = ldt2.atZone(ZoneId.of("Australia/Perth"));

        long betweenInMinutesWithZone12 = Math.abs(ChronoUnit.MINUTES.between(zdt1, zdt2));
        long untilInHoursWithZone12 = Math.abs(zdt1.until(zdt2, ChronoUnit.HOURS));
        long betweenInMinutesWithZone13 = Math.abs(ChronoUnit.MINUTES.between(zdt1, zdt3));
        long untilInHoursWithZone13 = Math.abs(zdt1.until(zdt3, ChronoUnit.HOURS));

        log.info("Europe/Bucharest: {} <-> Australia/Perth: {}", zdt1, zdt2);
        log.info("Difference in minutes with zone (same instant): {}", betweenInMinutesWithZone12);
        log.info("Difference in hours with zone (same instant): {}", untilInHoursWithZone12);

        log.info("Europe/Bucharest: {} <-> Australia/Perth: {}", zdt1, zdt3);
        log.info("Difference in minutes with zone (not same instant): {}", betweenInMinutesWithZone13);
        log.info("Difference in hours with zone: {}", untilInHoursWithZone13);
    }
}
