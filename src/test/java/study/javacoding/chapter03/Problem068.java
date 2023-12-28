package study.javacoding.chapter03;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;

@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem068 {

    @Test
    void 항공편_날짜와_시간_표시() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a, MMM dd, yyyy");
        DateTimeFormatter zoneFormatter = DateTimeFormatter.ofPattern("hh:mm a, MMM dd, yyyy Z VV");

        LocalDateTime ldt = LocalDateTime.of(2023, Month.DECEMBER, 26, 16, 37);
        log.info("Perth LocalDateTime: {} | Scheduled Flight Time: 15 hours and 30 minutes", ldt.format(formatter));

        ZonedDateTime auPerthDepart = ldt.atZone(ZoneId.of("Australia/Perth"));
        ZonedDateTime euBucharestDepart = auPerthDepart.withZoneSameInstant(ZoneId.of("Europe/Bucharest"));

        // 15시간 30분짜리 항공편
        ZonedDateTime auPerthArrive = auPerthDepart.plusHours(15).plusMinutes(30);
        ZonedDateTime euBucharestArrive = auPerthArrive.withZoneSameInstant(ZoneId.of("Europe/Bucharest"));

        OffsetDateTime utcAtDepart = auPerthDepart.withZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime();
        OffsetDateTime utcAtArrive = auPerthArrive.withZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime();

        log.info("UTC time at depart is: {}", utcAtDepart.format(formatter));
        log.info("UTC time at arrive is: {}", utcAtArrive.format(formatter));
        log.info("At depart, in Perth is: {}", auPerthDepart.format(zoneFormatter));
        log.info("At arrive, in Perth is: {}", auPerthArrive.format(zoneFormatter));
        log.info("At depart, in Bucharest is: {}", euBucharestDepart.format(zoneFormatter));
        log.info("At arrive, in Bucharest is: {}", euBucharestArrive.format(zoneFormatter));
    }
}
