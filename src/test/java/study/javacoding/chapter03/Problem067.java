package study.javacoding.chapter03;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem067 {

    public final class DateTimes {
        private DateTimes() {
            throw new AssertionError("Cannot be instantiated");
        }

        public static List<String> localTimeToAllTimeZones7() {
            List<String> result = new ArrayList<>();
            String[] zoneIds = TimeZone.getAvailableIDs();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd'T'HH:mm:ss a Z");
            SimpleDateFormat zoneFormatter = new SimpleDateFormat("yyyy-MMM-dd'T'HH:mm:ss a Z");

            Date date = new Date();
            for (String zoneId : zoneIds) {
                zoneFormatter.setTimeZone(TimeZone.getTimeZone(zoneId));
                result.add(formatter.format(date) + " in " + zoneId + " is " + zoneFormatter.format(date));
            }

            return result;
        }

        public static List<String> localTimeToAllTimeZones8() {
            List<String> result = new ArrayList<>();
            Set<String> zoneIds = ZoneId.getAvailableZoneIds();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd'T'HH:mm:ss a Z");
            ZonedDateTime zlt = ZonedDateTime.now();

            zoneIds.forEach((zoneId) -> {
                result.add(zlt.format(formatter) + " in " + zoneId + " is "
                        + zlt.withZoneSameInstant(ZoneId.of(zoneId)).format(formatter));
            });

            return result;
        }
    }

    @Test
    void 자바8_이전_Date_사용해서_모든_표준_시간대_로컬_날짜와_시간_구하기() {
        List<String> datetimes7 = DateTimes.localTimeToAllTimeZones7();
        datetimes7.forEach(log::info);
    }

    @Test
    void 자바8_이후_ZonedDateTime_사용해서_모든_표준_시간대_로컬_날짜와_시간_구하기() {
        List<String> datetimes8 = DateTimes.localTimeToAllTimeZones8();
        datetimes8.forEach(log::info);
    }
}
