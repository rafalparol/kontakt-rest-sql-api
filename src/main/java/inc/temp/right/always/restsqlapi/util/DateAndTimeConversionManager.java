package inc.temp.right.always.restsqlapi.util;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
public class DateAndTimeConversionManager {
    public Timestamp fromLocalDateToTimestamp(String str) {
        String pattern = "yyyy-MM-dd";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate localDate = LocalDate.parse(str, dateTimeFormatter);
        return Timestamp.from(Instant.ofEpochSecond(localDate.atTime(0,0,0,0).atZone(ZoneId.of("UTC")).toEpochSecond()));
    }
}
