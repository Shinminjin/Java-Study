package study.javacoding.chapter03;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem063 {

    private String periodToYMD(Period period) {
        if (period == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(period.getYears())
                .append("y:")
                .append(period.getMonths())
                .append("m:")
                .append(period.getDays())
                .append("d");

        return sb.toString();
    }


    @Test
    void 날짜_기반_값을_사용한_기간() {
        Period fromDays = Period.ofDays(120);
        log.info("Period from days: {}", fromDays);

        Period periodFromUnits = Period.of(2000, 11, 24);
        log.info("Period from units: {}", periodFromUnits);

        LocalDate localDate = LocalDate.now();
        Period periodFromLocalDate = Period.of(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
        log.info("Period from LocalDate: {}", periodFromLocalDate);

        Period periodFromString = Period.parse("P2019Y2M25D");
        log.info("Period from String: {}", periodFromString);

        LocalDate startLocalDate = LocalDate.of(2018, 3, 12);
        LocalDate endLocalDate = LocalDate.of(2019, 7, 20);
        Period periodBetween = Period.between(startLocalDate, endLocalDate);
        log.info("Between {} and {} there are {} years(s)", startLocalDate, endLocalDate, periodBetween.getYears());
        log.info("Between {} and {} there are {} month(s)", startLocalDate, endLocalDate, periodBetween.getMonths());
        log.info("Between {} and {} there are {} days(s)", startLocalDate, endLocalDate, periodBetween.getDays());

        log.info("Expressed as y:m:d: {}", periodToYMD(periodBetween));
        log.info("{} is after {} ? {}", startLocalDate, endLocalDate, periodBetween.isNegative());

        Period periodBetweenPlus1Year = periodBetween.plusYears(1L);
        log.info("{} has {} year, after adding one year it has {}", periodBetween, periodBetween.getYears(), periodBetweenPlus1Year.getYears());

        Period p1 = Period.ofDays(5);
        Period p2 = Period.ofDays(20);
        Period p1p2 = p1.plus(p2);
        log.info("{} + {} = {}", p1, p2, p1p2);
        assertThat(p1p2).isEqualTo(Period.parse("P25D"));
    }
}
