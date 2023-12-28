package study.javacoding.chapter03;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem073 {

    @Test
    void 자바8_이전_날짜_범위_순회() {
        log.info("Before JDK 8");

        Calendar calendar = Calendar.getInstance();

        calendar.set(2024, 1, 1);
        Date startDate = calendar.getTime();

        calendar.set(2024, 1, 21);
        Date endDate = calendar.getTime();

        Date day = startDate;
        // 1일부터 20일까지
        while (day.before(endDate)) {
            log.info("day: {}", day);

            calendar.setTime(day);
            calendar.add(Calendar.DATE, 1);
            day = calendar.getTime();
        }
    }

    @Test
    void 자바8_이후_날짜_범위_순회() {
        log.info("Starting with JDK 8");
        LocalDate startLocalDate = LocalDate.of(2024, 1, 1);
        LocalDate endLocalDate = LocalDate.of(2024, 1, 21);

        // 1일부터 20일까지
        for (LocalDate date = startLocalDate; date.isBefore(endLocalDate); date = date.plusDays(1)) {
            log.info("date: {}", date);
        }

        log.info("Starting with JDK 9");
        startLocalDate.datesUntil(endLocalDate).forEach(date -> log.info("date: {}", date));
    }
}
