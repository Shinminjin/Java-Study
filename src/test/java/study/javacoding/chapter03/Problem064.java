package study.javacoding.chapter03;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;

@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem064 {

    @Test
    void 자바8_이전_날짜와_시간_단위_구하기_Calender() {
        log.info("Before JDK 8");

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int yearC = calendar.get(Calendar.YEAR);
        int monthC = calendar.get(Calendar.MONTH);
        int weekC = calendar.get(Calendar.WEEK_OF_MONTH);
        int dayC = calendar.get(Calendar.DATE);
        int hourC = calendar.get(Calendar.HOUR_OF_DAY);
        int minuteC = calendar.get(Calendar.MINUTE);
        int secondC = calendar.get(Calendar.SECOND);
        int milliC = calendar.get(Calendar.MILLISECOND);

        log.info("Date: {}", date);
        log.info("Year: {} Month: {} Week: {} Day: {} Hour: {} Minute: {} Second: {} Millis: {}",
                yearC, monthC, weekC, dayC, hourC, minuteC, secondC, milliC);
    }

    @Test
    void 자바8_이후_날짜와_시간_단위_구하기_LocalDateTime() {
        log.info("Starting with JDK 8");

        LocalDateTime ldt = LocalDateTime.now();
        int yearLDT = ldt.getYear();
        int monthLDT = ldt.getMonthValue();
        int dayLDT = ldt.getDayOfMonth();
        int hourLDT = ldt.getHour();
        int minuteLDT = ldt.getMinute();
        int secondLDT = ldt.getSecond();
        int nanoLDT = ldt.getNano();

        log.info("LocalDateTime: {}", ldt);
        log.info("Year: {} Month: {} Day: {} Hour: {} Minute: {} Second: {} Nano: {}",
                yearLDT, monthLDT, dayLDT, hourLDT, minuteLDT, secondLDT, nanoLDT);

        int yearLDT2 = ldt.get(ChronoField.YEAR);
        int monthLDT2 = ldt.get(ChronoField.MONTH_OF_YEAR);
        int dayLDT2 = ldt.get(ChronoField.DAY_OF_MONTH);
        int hourLDT2 = ldt.get(ChronoField.HOUR_OF_DAY);
        int minuteLDT2 = ldt.get(ChronoField.MINUTE_OF_HOUR);
        int secondLDT2 = ldt.get(ChronoField.SECOND_OF_MINUTE);
        int nanoLDT2 = ldt.get(ChronoField.NANO_OF_SECOND);

        log.info("Year: {} Month: {} Day: {} Hour: {} Minute: {} Second: {} Nano: {}",
                yearLDT2, monthLDT2, dayLDT2, hourLDT2, minuteLDT2, secondLDT2, nanoLDT2);
    }
}
