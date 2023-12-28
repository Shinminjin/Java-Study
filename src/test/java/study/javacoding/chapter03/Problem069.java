package study.javacoding.chapter03;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem069 {

    @Test
    void 유닉스_타임스탬프를_날짜와_시간으로_변환() {
        long unixTimestamp = 1573768800;

        Date date1 = new Date(unixTimestamp * 1000L);
        Date date2 = new Date(TimeUnit.MILLISECONDS.convert(unixTimestamp, TimeUnit.SECONDS));

        log.info("{} as date-time in default time zone is {}", unixTimestamp, date1);
        log.info("{} as date-time in default time zone is {}", unixTimestamp, date2);

        Instant instant = Instant.ofEpochSecond(unixTimestamp);
        Date date3 = Date.from(instant);
        LocalDateTime date4 = LocalDateTime.ofInstant(instant, ZoneId.of("Australia/Perth"));
        ZonedDateTime date5 = ZonedDateTime.ofInstant(instant, ZoneId.of("Europe/Bucharest"));

        log.info("{} as date-time in default time zone is {}", unixTimestamp, date3);
        log.info("{} as date-time in Australia/Perth time zone is {}", unixTimestamp, date4);
        log.info("{} as date-time in Europe/Bucharest time zone is {}",
                unixTimestamp,
                date5.format(DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm:ss Z VV")));
        log.info("{} as instant (UTC time) is {}", unixTimestamp, instant);
    }
}
