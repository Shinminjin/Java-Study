package study.javacoding.chapter03;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

// 문자열을 날짜와 시간으로 변환
@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Problem058 {

    @Test
    void 자바8_이전_문자열을_날짜와_시간으로_변환_default() throws ParseException {
        log.info("Before Java 8 (default formatting styles)");

        DateFormat defaultDateFormatUS
                = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.US);
        Date usDate = defaultDateFormatUS.parse("01/15/95, 5:23 AM");
        String usDateAsString = usDate.toString();
        String usDateAsFormattedString = defaultDateFormatUS.format(usDate);
        log.info("SHORT format of date & time in US locale : {}", usDateAsString);
        log.info("SHORT format of date & time in US locale (formatted) : {}", usDateAsFormattedString);

        DateFormat defaultDateFormatDE
                = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.GERMAN);
        Date deDate = defaultDateFormatDE.parse("15.01.95, 5:23 AM");
        String deDateAsString = deDate.toString();
        String deDateAsFormattedString = defaultDateFormatDE.format(deDate);
        log.info("SHORT format of date & time in German locale : {}", deDateAsString);
        log.info("SHORT format of date & time in German locale (formatted) : {}", deDateAsFormattedString);
    }

    @Test
    void 자바8_이전_문자열을_날짜와_시간으로_변환_custom() throws ParseException {
        log.info("Before Java 8 (custom formatting styles)");

        // convert String to Date
        DateFormat simpleDateFormatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss zzz");

        // simpleDateFormatter.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
        Date dateSimpleFormatted = simpleDateFormatter.parse("1-Jun-2020 08:22:34 GMT");

        // convert Date to String
        String dateAsDefaultString = dateSimpleFormatted.toString();
        String dateAsFormattedString = simpleDateFormatter.format(dateSimpleFormatted);
        log.info("Default format : {}", dateAsDefaultString);
        log.info("Explicit format : {}", dateAsFormattedString);
    }

    @Test
    void 자바8_이후_포매터없이_문자열을_날짜와_시간으로_변환() {
        log.info("Java 8, convert without formatter");

        // convert String to LocalDate
        LocalDate localDate = LocalDate.parse("2020-06-01");
        // convert LocalDate to String
        String localDateAsDefaultString = localDate.toString();
        log.info("LocalDate: {} ( year: {}, month: {}, day: {} )",
                localDateAsDefaultString,
                localDate.getYear(), localDate.getMonthValue(), localDate.getMonthValue());

        // convert String to LocalTime
        LocalTime localTime = LocalTime.parse("12:23:44");
        // convert LocalTime to String
        String localTimeAsDefaultString = localTime.toString();
        log.info("LocalTime: {} ( hour: {}, minute: {}, second: {} )",
                localTimeAsDefaultString,
                localTime.getHour(), localTime.getMinute(), localTime.getSecond());

        // convert String to LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.parse("2020-06-01T11:20:15");
        // convert LocalDateTime to String
        String localDateTimeAsDefaultString = localDateTime.toString();
        log.info("LocalDateTime: {} ( year: {}, month: {}, day: {}, hour: {}, minute: {}, second: {} )",
                localDateTimeAsDefaultString,
                localDateTime.getYear(), localDateTime.getMonthValue(), localDateTime.getDayOfMonth(),
                localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond());

        // convert String to OffsetDateTime
        OffsetDateTime offsetDateTime = OffsetDateTime.parse("2007-12-03T10:15:30+01:00");
        // convert OffsetDateTime to String
        String offsetDateTimeAsDefaultString = offsetDateTime.toString();
        log.info("OffsetDateTime: {} ( year: {}, month: {}, day: {}, hour: {}, minute: {}, second: {}, offset: {} )",
                offsetDateTimeAsDefaultString,
                offsetDateTime.getYear(), offsetDateTime.getMonthValue(), offsetDateTime.getDayOfMonth(),
                offsetDateTime.getHour(), offsetDateTime.getMinute(), offsetDateTime.getSecond(),
                offsetDateTime.getOffset());

        // convert String to OffsetTime
        OffsetTime offsetTime = OffsetTime.parse("10:15:30+01:00");
        // convert OffsetTime to String
        String offsetTimeAsDefaultString = offsetTime.toString();
        log.info("OffsetTime: {} ( hour: {}, minute: {}, second: {}, offset: {} )",
                offsetTimeAsDefaultString,
                offsetTime.getHour(), offsetTime.getMinute(), offsetTime.getSecond(),
                offsetTime.getOffset());

        // convert String to ZonedDateTime
        ZonedDateTime zonedDateTime = ZonedDateTime.parse("2020-06-01T10:15:30+09:00[Asia/Tokyo]");
        // convert ZonedDateTime to String
        String zonedDateTimeAsDefaultString = zonedDateTime.toString();
        log.info("ZonedDateTime: {} ( year: {}, month: {}, day: {}, hour: {}, minute: {}, second: {}, offset: {}, zone: {} )",
                zonedDateTimeAsDefaultString,
                zonedDateTime.getYear(), zonedDateTime.getMonthValue(), zonedDateTime.getDayOfMonth(),
                zonedDateTime.getHour(), zonedDateTime.getMinute(), zonedDateTime.getSecond(),
                zonedDateTime.getOffset(), zonedDateTime.getZone());
    }

    @Test
    void 자바8_이후_포매터로_문자열을_날짜와_시간으로_변환() {
        log.info("Java 8, convert with formatter");

        // LocalDate
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        // convert String to LocalDate
        LocalDate localDateFormatted = LocalDate.parse("01.06.2020", dateFormatter);
        // convert LocalDate to String
        String localDateAsFormattedString = dateFormatter.format(localDateFormatted);
        log.info("Date: {} ( year: {}, month: {}, day: {} )",
                localDateAsFormattedString,
                localDateFormatted.getYear(), localDateFormatted.getMonthValue(), localDateFormatted.getDayOfMonth());

        // LocalTime
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH|mm|ss");
        // convert String to LocalTime
        LocalTime localTimeFormatted = LocalTime.parse("12|23|44", timeFormatter);
        // convert LocalTime to String
        String localTimeAsFormattedString = timeFormatter.format(localTimeFormatted);
        log.info("Time: {} ( hour: {}, minute: {}, second: {} )",
                localTimeAsFormattedString,
                localTimeFormatted.getHour(), localTimeFormatted.getMinute(), localTimeFormatted.getSecond());

        // LocalDateTime
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm:ss");
        // convert String to LocalDateTime
        LocalDateTime localDateTimeFormatted = LocalDateTime.parse("01.06.2020, 11:20:15", dateTimeFormatter);
        // convert LocalDateTime to String
        String localDateTimeAsFormattedString = dateTimeFormatter.format(localDateTimeFormatted);
        log.info("DateTime: {} ( year: {}, month: {}, day: {}, hour: {}, minute: {}, second: {} )",
                localDateTimeAsFormattedString,
                localDateTimeFormatted.getYear(), localDateFormatted.getMonthValue(), localDateFormatted.getDayOfMonth(),
                localDateTimeFormatted.getHour(), localDateTimeFormatted.getMinute(), localDateTimeFormatted.getSecond());

        // OffsetDateTime
        DateTimeFormatter offsetDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd, HH:mm:ss, XXXXX");
        // convert String to OffsetDateTime
        OffsetDateTime offsetDateTimeFormatted = OffsetDateTime.parse("2007.12.03, 10:15:30, +01:00", offsetDateTimeFormatter);
        // convert OffsetDateTime to String
        String offsetDateTimeAsFormattedString = offsetDateTimeFormatter.format(offsetDateTimeFormatted);
        log.info("OffsetDateTime: {} ( year: {}, month: {}, day: {}, hour: {}, minute: {}, second: {}, offset: {} )",
                offsetDateTimeAsFormattedString,
                offsetDateTimeFormatted.getYear(), offsetDateTimeFormatted.getMonthValue(), offsetDateTimeFormatted.getDayOfMonth(),
                offsetDateTimeFormatted.getHour(), offsetDateTimeFormatted.getMinute(), offsetDateTimeFormatted.getSecond(),
                offsetDateTimeFormatted.getOffset());

        // OffsetTime
        DateTimeFormatter offsetTimeFormatter = DateTimeFormatter.ofPattern("HH mm ss XXXXX");
        // convert String to OffsetTime
        OffsetTime offsetTimeFormatted = OffsetTime.parse("10 15 30 +01:00", offsetTimeFormatter);
        // convert OffsetTime to String
        String offsetTimeAsFormattedString = offsetTimeFormatter.format(offsetTimeFormatted);
        log.info("OffsetTime: {} ( hour: {}, minute: {}, second: {}, offset: {} )",
                offsetTimeAsFormattedString,
                offsetTimeFormatted.getHour(), offsetTimeFormatted.getMinute(), offsetTimeFormatted.getSecond(),
                offsetTimeFormatted.getOffset());

        // ZonedDateTime
        DateTimeFormatter zonedDateTimeFormatter
                = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm:ssXXXXX '['VV']'");
        // convert String to ZonedDateTime
        ZonedDateTime zonedDateTimeFormatted
                = ZonedDateTime.parse("01.06.2020, 11:20:15+09:00 [Asia/Tokyo]", zonedDateTimeFormatter);
        // convert ZonedDateTime to String
        String zonedDateTimeAsFormattedString = zonedDateTimeFormatted.format(zonedDateTimeFormatter);
        log.info("ZonedDateTime: {} ( year: {}, month: {}, day: {}, hour: {}, minute: {}, second: {}, offset: {}, zone: {} )",
                zonedDateTimeAsFormattedString,
                zonedDateTimeFormatted.getYear(), zonedDateTimeFormatted.getMonthValue(), zonedDateTimeFormatted.getDayOfMonth(),
                zonedDateTimeFormatted.getHour(), zonedDateTimeFormatted.getMinute(), zonedDateTimeFormatted.getSecond(),
                zonedDateTimeFormatted.getOffset(), zonedDateTimeFormatted.getZone());
    }
}
