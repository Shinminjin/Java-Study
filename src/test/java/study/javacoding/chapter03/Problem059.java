package study.javacoding.chapter03;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

// 날짜와 시간 포매팅
@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem059 {

    @Test
    void 자바8_이전_날짜와_시간_포매팅() {
        log.info("Before JDK 8");

        // yyyy-MM-dd
        Date date = new Date();

        SimpleDateFormat formatterD1 = new SimpleDateFormat("yyyy-MM-dd");
        String d1 = formatterD1.format(date);
        log.info(d1);

        // yyyy-MM-dd HH:mm:ss
        SimpleDateFormat formatterD2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d2 = formatterD2.format(date);
        log.info(d2);

        // E MMM yyyy HH:mm:ss.SSSZ
        SimpleDateFormat formatterD3 = new SimpleDateFormat("E MMM yyyy HH:mm:ss.SSSZ");
        String d3 = formatterD3.format(date);
        log.info(d3);
    }

    @Test
    void 자바8_이후_날짜와_시간_포매팅() {
        log.info("Starting with JDK 8");

        // yyyy-MM-dd
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatterLocalDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String ld1 = formatterLocalDate.format(localDate);

        // or shortly
        String ld2 = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        log.info(ld1);
        log.info(ld2);

        // HH:mm:ss
        LocalTime localTime = LocalTime.now();
        DateTimeFormatter formatterLocalTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        String lt1 = formatterLocalTime.format(localTime);

        // or shortly
        String lt2 = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        log.info(lt1);
        log.info(lt2);

        // yyyy-MM-dd HH:mm:ss
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatterLocalDateTime =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String ldt1 = formatterLocalDateTime.format(localDateTime);

        // or shortly
        String ldt2 = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        log.info(ldt1);
        log.info(ldt2);

        // E MMM yyyy HH:mm:ss.SSSZ
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        DateTimeFormatter formatterZonedDateTime =
                DateTimeFormatter.ofPattern("E MMM yyyy HH:mm:ss.SSSZ");
        String zdt1 = formatterZonedDateTime.format(zonedDateTime);

        // or shortly
        String zdt2 = ZonedDateTime.now()
                .format(DateTimeFormatter.ofPattern("E MMM yyyy HH:mm:ss.SSSZ"));

        log.info(zdt1);
        log.info(zdt2);

        // E MMM yyyy HH:mm:ss.SSSZ
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        DateTimeFormatter formatterOffsetDateTime =
                DateTimeFormatter.ofPattern("E MMM yyyy HH:mm:ss.SSSZ");
        String odt1 = formatterOffsetDateTime.format(offsetDateTime);

        // or shortly
        String odt2 = OffsetDateTime.now()
                .format(DateTimeFormatter.ofPattern("E MMM yyyy HH:mm:ss.SSSZ"));

        log.info(odt1);
        log.info(odt2);

        // HH:mm:ss,Z
        OffsetTime offsetTime = OffsetTime.now();
        DateTimeFormatter formatterOffsetTime =
                DateTimeFormatter.ofPattern("HH:mm:ss,Z");
        String ot1 = formatterOffsetTime.format(offsetTime);

        // or shortly
        String ot2 = OffsetTime.now()
                .format(DateTimeFormatter.ofPattern("HH:mm:ss,Z"));

        log.info(ot1);
        log.info(ot2);
    }
}
