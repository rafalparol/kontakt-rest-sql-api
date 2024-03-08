package inc.temp.right.always.restsqlapi.util;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class DateAndTimeConversionManager {
    public Timestamp fromStringDateToTimestamp(String str) {
        return Timestamp.valueOf(str + " 00:00:00");
    }
}
