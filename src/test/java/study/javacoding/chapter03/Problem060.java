package study.javacoding.chapter03;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem060 {

    @Test
    void Java8_이전_SimpleDateFormat으로_날짜와_시간_포매팅() {
        // JDK 8 이전 버전일 경우, SimpleDateFormat 으로 포맷 패턴 사용
        log.info("Before JDK 8");

        Date date = new Date();

        // yyyy-MM-dd
        SimpleDateFormat formatterD1 = new SimpleDateFormat("yyyy-MM-dd");
        String d1 = formatterD1.format(date);
        log.info("yyyy-MM-dd: {}", d1);

        // yyyy-MM-dd HH:mm:ss
        SimpleDateFormat formatterD2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d2 = formatterD2.format(date);
        log.info("yyyy-MM-dd HH:mm:ss: {}", d2);

        // E MMM yyyy HH:mm:ss:SSSZ
        // E: 요일, S: 나노초, Z:시간대 오프셋
        SimpleDateFormat formatterD3 = new SimpleDateFormat("E MMM yyyy HH:mm:ss:SSSZ");
        String d3 = formatterD3.format(date);
        log.info("E MMM yyyy HH:mm:ss.SSSZ: {}", d3);
    }

    // DateTimeFormatter
    @Test
    void Java8_이후_DateTimeFormatter으로_날짜와_시간_포매팅() {
        // JDK 8부터는 DateTimeFormatter 으로 포맷 패턴 사용
        log.info("Starting with JDK 8");

        // yyyy-MM-dd
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatterLocalDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String ld1 = formatterLocalDate.format(localDate);

        // or shortly
        String ld2 = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        log.info("yyyy-MM-dd(ld1): {}", ld1);
        log.info("yyyy-MM-dd(ld2): {}", ld2);

        // HH:mm:ss
        LocalTime localTime = LocalTime.now();
        DateTimeFormatter formatterLocalTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        String lt1 = formatterLocalTime.format(localTime);

        // of shortly
        String lt2 = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        log.info("HH:mm:ss(lt1): {}", lt1);
        log.info("HH:mm:ss(lt2): {}", lt2);

        // yyyy-MM-dd HH:mm:ss
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatterLocalDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String ldt1 = formatterLocalDateTime.format(localDateTime);

        // or shortly
        String ldt2 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        log.info("yyyy-MM-dd HH:mm:ss(ldt1): {}", ldt1);
        log.info("yyyy-MM-dd HH:mm:ss(ldt2): {}", ldt2);

        // E MMM yyyy HH:mm:ss.SSSZ - ZonedDateTime
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        DateTimeFormatter formatterZonedDateTime = DateTimeFormatter.ofPattern("E MMM yyyy HH:mm:ss.SSSZ");
        String zdt1 = formatterZonedDateTime.format(zonedDateTime);

        // or shortly
        String zdt2 = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("E MMM yyyy HH:mm:ss.SSSZ"));
        log.info("E MMM yyyy HH:mm:ss.SSSZ(zdt1): {}", zdt1);
        log.info("E MMM yyyy HH:mm:ss.SSSZ(zdt2): {}", zdt2);

        // E MMM yyyy HH:mm:ss.SSSZ - OffsetDateTime
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        DateTimeFormatter formatterOffsetDateTime = DateTimeFormatter.ofPattern("E MMM yyyy HH:mm:ss.SSSZ");
        String odt1 = formatterOffsetDateTime.format(offsetDateTime);

        // or shortly
        String odt2 = OffsetDateTime.now().format(DateTimeFormatter.ofPattern("E MMM yyyy HH:mm:ss.SSSZ"));
        log.info("E MMM yyyy HH:mm:ss.SSSZ(odt1): {}", odt1);
        log.info("E MMM yyyy HH:mm:ss.SSSZ(odt2): {}", odt2);

        // HH:mm:ss,Z
        OffsetTime offsetTime = OffsetTime.now();
        DateTimeFormatter formatterOffsetTime = DateTimeFormatter.ofPattern("HH:mm:ss,Z");
        String ot1 = formatterOffsetTime.format(offsetTime);

        // or shortly
        String ot2 = OffsetTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss,Z"));
        log.info("E MMM yyyy HH:mm:ss.SSSZ(ot1): {}", ot1);
        log.info("E MMM yyyy HH:mm:ss.SSSZ(ot2): {}", ot2);
    }
}
