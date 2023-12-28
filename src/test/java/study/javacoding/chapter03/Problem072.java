package study.javacoding.chapter03;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import study.javacoding.chapter02.problem072.DateConverters;

import java.time.*;
import java.util.Date;

@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem072 {

    @Test
    void Date와_Temporal간_변환() {
        log.info("------------------------");
        log.info("Convert Date to Temporal");
        log.info("------------------------");

        log.info("Convert Date -> Instant:");
        Date date = new Date();
        log.info("Instant from Date (UTC): {}", DateConverters.dateToInstant(date));

        log.info("Convert Date -> LocalDate:");
        log.info("LocalDate: {}", DateConverters.dateToLocalDate(date));

        log.info("Convert Date -> LocalDateTime:");
        log.info("LocalDateTime: {}", DateConverters.dateToLocalDateTime(date));

        log.info("Convert Date -> ZonedDateTime:");
        log.info("ZonedDateTime: {}", DateConverters.dateToZonedDateTime(date));

        log.info("Convert Date -> OffsetDateTime:");
        log.info("OffsetDateTime: {}", DateConverters.dateToOffsetDateTime(date));

        log.info("Convert Date -> LocalTime:");
        log.info("Local time: {}", DateConverters.dateToLocalTime(date));

        log.info("Convert Date -> OffsetTime:");
        log.info("Offset time: {}", DateConverters.dateToOffsetTime(date));

        log.info("------------------------");
        log.info("Convert Temporal to Date");
        log.info("------------------------");

        log.info("Convert Instant -> Date:");
        Instant instant = Instant.now();
        log.info("Date from Instant (your default system time zone): {}"
                , DateConverters.instantToDate(instant));

        log.info("Convert LocalDate -> Date:");
        LocalDate localDate = LocalDate.now();
        log.info("Date: {}", DateConverters.localDateToDate(localDate));

        log.info("Convert LocalDateTime -> Date:");
        LocalDateTime localDateTime = LocalDateTime.now();
        log.info("Date: {}", DateConverters.localDateTimeToDate(localDateTime));

        log.info("Convert ZonedDateTime -> Date:");
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        log.info("Date: {}", DateConverters.zonedDateTimeToDate(zonedDateTime));

        log.info("Convert OffsetDateTime -> Date:");
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        log.info("Date: {}", DateConverters.offsetDateTimeToDate(offsetDateTime));

        log.info("Convert LocalTime -> Date:");
        LocalTime localTime = LocalTime.now();
        log.info("Date: {}", DateConverters.localTimeToDate(localTime));

        log.info("Convert OffsetTime -> Date:");
        OffsetTime offsetTime = OffsetTime.now();
        log.info("Date: {}", DateConverters.offsetTimeToDate(offsetTime));
    }
}
