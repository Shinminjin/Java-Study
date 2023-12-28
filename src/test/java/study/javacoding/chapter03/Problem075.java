package study.javacoding.chapter03;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.temporal.ChronoField;

@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem075 {

    @Test
    void 어떤_날의_시작과_끝_시간() {
        LocalDate localDate = LocalDate.of(2023, 12, 27);
        log.info("LocalDate start of the day");

        LocalDateTime ldDayStart1 = localDate.atStartOfDay();
        ZonedDateTime ldDayStartZone1 = localDate.atStartOfDay(ZoneId.of("Asia/Seoul"));
        log.info("Start of the day without time zone via atStartOfDay(): {}", ldDayStart1);
        log.info("Start of the day with time zone, Asia/Seoul via atStartOfDay(): {}", ldDayStartZone1);

        LocalDateTime ldDayStart2 = LocalDateTime.of(localDate, LocalTime.MIN);
        ZonedDateTime ldDayStartZone2 = LocalDateTime.of(localDate, LocalTime.MIN).atZone(ZoneId.of("Asia/Seoul"));
        log.info("Start of the day without time zone via of(): {}", ldDayStart1);
        log.info("Start of the day with time zone, Asia/Seoul via of(): {}", ldDayStartZone1);

        log.info("LocalDate end of the day");
        LocalDateTime ldDayEnd1 = localDate.atTime(LocalTime.MAX);
        LocalDateTime ldDayEndZone1 = localDate.atTime(LocalTime.MAX);
        log.info("End of the day without time zone via atTime(): {}", ldDayEnd1);
        log.info("End of the day with time zone, Asia/Seoul via atTime(): {}", ldDayEnd1);

        LocalDateTime ldDayEnd2 = LocalTime.MAX.atDate(localDate);
        ZonedDateTime ldDayEndZone2 = LocalTime.MAX.atDate(localDate)
                .atZone(ZoneId.of("Asia/Seoul"));
        log.info("End of the day without time zone via atDate(): {}", ldDayEnd2);
        log.info("End of the day with time zone, Asia/Seoul via atDate(): {}", ldDayEndZone2);

        log.info("LocalDateTime start of the day:");
        LocalDateTime localDateTime = LocalDateTime.of(2023, 12, 27, 18, 0, 0);
        LocalDateTime ldtDayStart = localDateTime.with(ChronoField.NANO_OF_DAY, LocalTime.MIN.toNanoOfDay());
        //LocalDateTime ldtDayStart = localDateTime.with(ChronoField.HOUR_OF_DAY, 0);
        ZonedDateTime ldtDayStartZone = localDateTime
                .with(ChronoField.NANO_OF_DAY, LocalTime.MIN.toNanoOfDay())
                .atZone(ZoneId.of("Asia/Seoul"));
        log.info("Start of the day without time zone via with(): {}", ldtDayStart);
        log.info("Start of the day with time zone, Asia/Seoul via with(): {}", ldtDayStartZone);

        LocalDateTime ldtDayEnd = localDateTime.with(ChronoField.NANO_OF_DAY, LocalTime.MAX.toNanoOfDay());
        // LocalDateTime ldtDayEnd = localDateTime.with(ChronoField.NANO_OF_DAY, 86399999999999L);
        ZonedDateTime ldtDayEndZone = localDateTime
                .with(ChronoField.NANO_OF_DAY, LocalTime.MAX.toNanoOfDay())
                .atZone(ZoneId.of("Australia/Perth"));
        log.info("End of the day without time zone via with(): {}", ldtDayEnd);
        log.info("End of the day with time zone, Australia/Perth via with(): {}", ldtDayEndZone);

        log.info("UTC start and end of the day:");
        ZonedDateTime zdt = ZonedDateTime.now(ZoneOffset.UTC);
        ZonedDateTime dayStartZdt = zdt.toLocalDate().atStartOfDay(zdt.getZone());
        ZonedDateTime dayEndZdt = zdt.toLocalDate().atTime(LocalTime.MAX).atZone(zdt.getZone());

        log.info("UTC time: {}", zdt);
        log.info("Start day with UTC: {}", dayStartZdt);
        log.info("End day with UTC: {}", dayEndZdt);
    }
}
