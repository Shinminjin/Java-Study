package study.javacoding.chapter03;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.*;

// 어떤 달의 첫째 날과 마지막 날 찾기
@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem070 {

    public final class DateTimes {
        private DateTimes() {
            throw new AssertionError("Cannot be instantiated");
        }

        public static LocalDate getDayAfterDays(LocalDate startDate, int days) {

            if (startDate == null) {
                throw new IllegalArgumentException("Start date cannot be done");
            }

            Period period = Period.ofDays(days);
            TemporalAdjuster ta = p -> p.plus(period);
            LocalDate endDate = startDate.with(ta);

            return endDate;
        }
    }

    public class NextSaturdayAdjuster implements TemporalAdjuster {

        @Override
        public Temporal adjustInto(Temporal temporal) {
            DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));

            if (dayOfWeek == DayOfWeek.SATURDAY) {
                return temporal;
            }

            if (dayOfWeek == DayOfWeek.SUNDAY) {
                return temporal.plus(6, ChronoUnit.DAYS);
            }

            return temporal.plus(6 - dayOfWeek.getValue(), ChronoUnit.DAYS);
        }
    }

    static TemporalAdjuster NEXT_SATURDAY = TemporalAdjusters.ofDateAdjuster(today -> {

        DayOfWeek dayOfWeek = today.getDayOfWeek();

        if (dayOfWeek == DayOfWeek.SATURDAY) {
            return today;
        }

        if (dayOfWeek == DayOfWeek.SUNDAY) {
            return today.plusDays(6);
        }

        return today.plusDays(6 - dayOfWeek.getValue());
    });

    @Test
    void 어떤_달의_첫째_날과_마지막_날_찾기() {
        LocalDate date = LocalDate.of(2023, Month.DECEMBER, 26);
        LocalDate firstDayOfFeb = date.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfFeb = date.with(TemporalAdjusters.lastDayOfMonth());

        log.info("First day of Dec, 2023: {}", firstDayOfFeb);
        log.info("Last day of Dec, 2023: {}", lastDayOfFeb);

        LocalDate datePlus21Days = DateTimes.getDayAfterDays(date, 21);
        log.info("21 days after 26 Dec 2023 is : {}", datePlus21Days);

        LocalDate nextSaturday1 = date.with(NEXT_SATURDAY);
        log.info("Next Saturday is (1): {}", nextSaturday1);

        NextSaturdayAdjuster nsa = new NextSaturdayAdjuster();
        LocalDate nextSaturday2 = date.with(nsa);
        log.info("Next Saturday is (2): {}", nextSaturday2);
    }
}
