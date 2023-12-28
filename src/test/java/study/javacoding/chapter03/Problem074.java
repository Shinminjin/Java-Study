package study.javacoding.chapter03;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem074 {

    @Test
    void Java8_이전_나이계산() {
        log.info("Before JDK 8");
        // Calendar 사용
        Calendar startDate = Calendar.getInstance();
        startDate.set(1997, 8, 8);
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(new Date());

        int yearsc = endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR);
        if (yearsc > 0) {
            if (startDate.get(Calendar.MONTH) > endDate.get(Calendar.MONTH)
                    || (startDate.get(Calendar.MONTH) == endDate.get(Calendar.MONTH)
                    && startDate.get(Calendar.DAY_OF_MONTH) > endDate.get(Calendar.DAY_OF_MONTH))) {
                yearsc--;
            }
        }

        log.info("{}y", yearsc);
    }

    @Test
    void 자바8_이후_나이계산() {
        log.info("Starting with JDK 8");

        LocalDate startLocalDate = LocalDate.of(1997, 8, 8);
        LocalDate endLocalDate = LocalDate.now();
        long years = ChronoUnit.YEARS.between(startLocalDate, endLocalDate);
        log.info("{}y", years);

        // Period 사용
        Period periodBetween = Period.between(startLocalDate, endLocalDate);
        log.info("{}y {}m {}d",
                periodBetween.getYears(),
                periodBetween.getMonths(),
                periodBetween.getDays()
        );
    }
}
