package study.javacoding.chapter03;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem061 {

    @Test
    void LocalDate와_LocalTime으로_LocalDateTime생성() {
        LocalDate localDate = LocalDate.now();
        String localDateAsString = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        log.info("LocalDate: {}", localDateAsString);

        LocalTime localTime = LocalTime.now();
        String localTimeAsString = localTime.format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
        log.info("LocalTime: {}", localTimeAsString);

        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        String localDateTimeAsString = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a"));
        log.info("LocalDateTime: {}", localDateTimeAsString);
    }
}
