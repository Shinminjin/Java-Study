package study.javacoding.chapter03;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem066 {

    public final class DateTimes {
        public enum OffsetType {
            GMT, UTC
        }

        private DateTimes() {
            throw new AssertionError("Cannot be instantiated");
        }

        public static List<String> fetchTimeZones8(OffsetType type) {
            List<String> timezones = new ArrayList<>();
            Set<String> zoneIds = ZoneId.getAvailableZoneIds();

            LocalDateTime now = LocalDateTime.now();
            zoneIds.forEach((zoneId) -> {
                timezones.add("(" + type + now.atZone(ZoneId.of(zoneId))
                        .getOffset().getId().replace("Z", "+00:00") + ") " + zoneId);
            });

            return timezones;
        }

        public static List<String> fetchTimeZones7(OffsetType type) {
            List<String> timezones = new ArrayList<>();
            String[] zoneIds = TimeZone.getAvailableIDs();

            long timestamp = new Date().getTime();
            for (String zoneId : zoneIds) {
                TimeZone curTimeZone = TimeZone.getTimeZone(zoneId);
                curTimeZone.useDaylightTime();
                String offset = formatOffset(curTimeZone.getOffset(timestamp));

                timezones.add("(" + type + offset + ") " + zoneId);
            }
            return timezones;
        }

        public static String formatOffset(int offset) {
            if (offset == 0) {
                return "+00:00";
            }

            long offsetInHours = TimeUnit.MILLISECONDS.toHours(offset);
            long offsetInMinutesFromHours = TimeUnit.HOURS.toMinutes(offsetInHours);
            long offsetInMinutes = TimeUnit.MILLISECONDS.toMinutes(offset);

            offsetInMinutes = Math.abs(offsetInMinutesFromHours - offsetInMinutes);
            return String.format("%+03d:%02d", offsetInHours, offsetInMinutes);
        }
    }

    @Test
    void 자바8_이전_TimeZone_사용해서_모든_표준_시간대_구하기() {
        List<String> timezones7 = DateTimes.fetchTimeZones7(DateTimes.OffsetType.UTC);
        Collections.sort(timezones7);
        timezones7.forEach(log::info);
    }

    @Test
    void 자바8_이후_ZoneId_사용해서_모든_표준_시간대_구하기() {
        List<String> timezones8 = DateTimes.fetchTimeZones8(DateTimes.OffsetType.UTC);
        Collections.sort(timezones8);
        timezones8.forEach(log::info);
    }
}
