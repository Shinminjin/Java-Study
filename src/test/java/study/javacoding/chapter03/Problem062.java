package study.javacoding.chapter03;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

// Instant 클래스로 기계 시간 구하기
@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem062 {

    @Test
    void 문자열을_Instant로_변환() {
        // get an Instant
        Instant timestamp = Instant.now();
        log.info("Timestamp: {}", timestamp);

        // String to Instant
        Instant timestampFromString = Instant.parse("2023-12-26T02:10:10.280799700Z");
        log.info("Timestamp from string: {}", timestampFromString);
    }

    @Test
    void 시간에서_Instant_더하거나_빼기() {
        // Plus two hours
        Instant twoHourLater = Instant.now().plus(2, ChronoUnit.HOURS);
        log.info("Two hours later: {}", twoHourLater);

        // Minus 10 minutes
        Instant tenMinutesEarlier = Instant.now().minus(10, ChronoUnit.MINUTES);
        log.info("Ten minutes earlier: {}", tenMinutesEarlier);
    }

    @Test
    void Instant_객체_비교() {

        Instant timestamp1 = Instant.now();
        Instant timestamp2 = timestamp1.plusSeconds(10);
        // check if one Instant is after another Instant
        assertThat(timestamp2.isAfter(timestamp1)).isEqualTo(true);
        // check if one Instant is before another Instant
        assertThat(timestamp1.isBefore(timestamp2)).isEqualTo(true);

        // difference between two Instants
        long difference = timestamp1.until(timestamp2, ChronoUnit.SECONDS);
        log.info("Between {} and {} there are {} seconds", timestamp1, timestamp2, difference);
    }

    @Test
    void Instant와_LocalDateTime_ZonedDateTime_OffsetDateTime_간_반환() {

        // Instant 와 LocalDateTime 간 변환
        // LocalDateTime 에는 표준 시간대 개념이 없으니 제로 오프셋인 UTC+0 사용
        // Instant -> LocalDateTime
        LocalDateTime ldt = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);
        log.info("Instant to LocalDateTime: {}", ldt);
        // LocalDateTime -> Instant
        Instant instantLDT = LocalDateTime.now().toInstant(ZoneOffset.UTC);
        log.info("LocalDateTime to Instant: {}", instantLDT);

        // Instant 와 ZonedDateTime 간 변환
        // Instant -> ZonedDateTime
        ZonedDateTime zdt = Instant.now().atZone(ZoneId.of("Europe/Paris"));
        log.info("Instant to ZonedDateTime: {} (offset: {})", zdt, zdt.getOffset());
        // ZonedDateTime -> Instant
        Instant instantZDT = LocalDateTime.now().atZone(ZoneId.of("Europe/Paris")).toInstant();
        log.info("ZonedDateTime to Instant: {}", instantZDT);

        // Instant 와 OffsetDateTime 간 변환
        // Instant -> OffsetDateTime
        OffsetDateTime odt = Instant.now().atOffset(ZoneOffset.of("+02:00"));
        log.info("Instant to OffsetDateTime: {} (offset: {})", odt, odt.getOffset());
        // OffsetDateTime -> Instant
        Instant instantODT = LocalDateTime.now().atOffset(ZoneOffset.of("+02:00")).toInstant();
        log.info("OffsetDateTime to Instant: {}", instantODT);
    }
}
