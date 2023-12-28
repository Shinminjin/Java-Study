package study.javacoding.chapter03;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem071 {

    private String formatOffset(int offset) {

        if (offset == 0) {
            return "+00:00";
        }

        long offsetInHours = TimeUnit.MILLISECONDS.toHours(offset);
        long offsetInMinutesFromHours = TimeUnit.HOURS.toMinutes(offsetInHours);
        long offsetInMinutes = TimeUnit.MILLISECONDS.toMinutes(offset);

        offsetInMinutes = Math.abs(offsetInMinutesFromHours - offsetInMinutes);

        return String.format("%+03d:%02d", offsetInHours, offsetInMinutes);
    }

    @Test
    void 자바8_이전_존오프셋_정의_추출() {
        log.info("Before JDK 8");

        TimeZone timeZoneAS = TimeZone.getTimeZone("Asia/Seoul");
        int offsetFromTimeZone = timeZoneAS.getRawOffset();
        String userFriendlyOffsetTimeZone = formatOffset(offsetFromTimeZone);
        log.info("Offset from TimeZone (Asia/Seoul): {}", userFriendlyOffsetTimeZone);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, 12, 27);
        timeZoneAS.useDaylightTime();

        int offsetFromDate = timeZoneAS.getOffset(calendar.getTime().getTime());
        String userFriendlyOffsetDate = formatOffset(offsetFromDate);
        log.info("Offset from Calendar (Asia/Seoul): {}", userFriendlyOffsetDate);
    }

    @Test
    void 자바8_이후_존오프셋_정의_추출() {
        log.info("Starting with JDK 8");

        ZoneOffset zoneOffsetUTC = ZoneOffset.UTC;
        log.info("ZoneOffsetUTC: {}", zoneOffsetUTC);

        // 1. java.time.ZoneId 사용
        ZoneId defaultZoneId = ZoneOffset.systemDefault();
        log.info("Default zone id: {}", defaultZoneId);

        //----------------------------------------------------------------

        // 2. java.time.ZoneOffset(ZoneId의 확장) 사용
        LocalDateTime ldt = LocalDateTime.of(2023, 12, 27, 0, 0);
        ZoneId zoneId = ZoneId.of("Asia/Seoul");
        ZoneOffset zoneOffset = zoneId.getRules().getOffset(ldt);
        log.info("ZoneOffset from LocalDate (Asia/Seoul): {}", zoneOffset);

        ZoneOffset zoneOffsetFromString = ZoneOffset.of("+09:00");
        log.info("ZoneOffset from String: {}", zoneOffsetFromString);

        OffsetTime offsetTime = OffsetTime.now(zoneOffsetFromString);
        log.info("OffsetTime from ZoneOffset of current date: {}", offsetTime);

        OffsetDateTime offsetDateTime = OffsetDateTime.now(zoneOffsetFromString);
        log.info("OffsetDateTime from ZoneOffset of current date: {}", offsetDateTime);

        ZoneOffset zoneOffsetFromHours = ZoneOffset.ofHours(9);
        log.info("ZoneOffset from hours: {}", zoneOffsetFromHours);

        ZoneOffset zoneOffsetFromOdt = offsetDateTime.getOffset();
        log.info("ZoneOffset from offsetDateTime: {}", zoneOffsetFromOdt);
    }
}
